package org.example;

public interface Subject {
    void subscribe(String category, Subscriber subscriber);
    void unsubscribe(String category, Subscriber subscriber);
}
