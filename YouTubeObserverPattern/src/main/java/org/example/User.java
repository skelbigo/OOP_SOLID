package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User implements Subscriber {
    private final String name;
    private final List<String> notifications;

    public User(String name) {
        if (name == null || name.strip().isBlank()) {
            throw new IllegalArgumentException("User name cannot be null or blank");
        }
        this.name = name.strip();
        this.notifications = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<String> getNotifications() {
        return Collections.unmodifiableList(notifications);
    }

    @Override
    public void update(String channelName, String videoTitle) {
        String message = "New video from " + channelName + ": " + videoTitle;
        notifications.add(message);
    }
}
