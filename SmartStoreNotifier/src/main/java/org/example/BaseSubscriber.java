package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseSubscriber implements Subscriber {
    protected final String contactInfo;
    private final List<String> notifications;

    public BaseSubscriber(String contactInfo) {
        if (contactInfo == null || contactInfo.strip().isBlank()) {
            throw new IllegalArgumentException("Contact info cannot be null or blank");
        }
        this.contactInfo = contactInfo.strip();
        validateContactInfo(this.contactInfo);
        this.notifications = new ArrayList<>();
    }

    protected abstract void validateContactInfo(String contactInfo);

    public String getContactInfo() {
        return contactInfo;
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    @Override
    public void update(String category, String message) {
        if (category == null || category.strip().isBlank()) {
            throw new IllegalArgumentException("Category cannot be null or blank");
        }
        if (message == null || message.strip().isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        notifications.add(formatNotifications(category.strip(), message.strip()));
    }

    protected abstract String formatNotifications(String category, String message);
}
