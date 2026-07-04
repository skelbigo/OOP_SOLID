package org.example;

public interface GitObserver {
    void update(EventType eventType, String branch, String details);
}
