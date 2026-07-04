package org.example;

import java.util.*;

public class Store implements Subject {
    private final Map<String, Set<Subscriber>> categorySubscribers = new HashMap<>();

    @Override
    public void subscribe(String category, Subscriber subscriber) {
        validateInputs(category, subscriber);
        categorySubscribers.computeIfAbsent(normalize(category), k -> new LinkedHashSet<>()).add(subscriber);
    }

    @Override
    public void unsubscribe(String category, Subscriber subscriber) {
        validateInputs(category, subscriber);
        Set<Subscriber> subscribers = categorySubscribers.get(normalize(category));
        if (subscribers != null) {
            subscribers.remove(subscriber);
            if (subscribers.isEmpty()) {
                categorySubscribers.remove(normalize(category));
            }
        }
    }

    private void validateInputs(String category, Subscriber subscriber) {
        if (category == null || category.strip().isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or blank");
        }
        if (subscriber == null) {
            throw new IllegalArgumentException("Subscriber cannot be null");
        }
    }

    public void announceDiscount(String category, String discountMessage) {
        if (category == null || category.strip().isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or blank");
        }
        if (discountMessage == null || discountMessage.strip().isBlank()) {
            throw new IllegalArgumentException("Discount message cannot be null or blank");
        }

        notifySubscribers(normalize(category), discountMessage.strip());
    }

    private void notifySubscribers(String category, String message) {
        Set<Subscriber> subscribers = categorySubscribers.getOrDefault(category, Collections.emptySet());
        for (Subscriber subscriber : subscribers) {
            subscriber.update(category, message);
        }
    }

    private String normalize(String input) {
        return input.strip().toLowerCase();
    }
}
