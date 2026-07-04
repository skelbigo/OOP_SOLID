package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WebHook implements GitObserver {
    private final String hookName;
    private final List<String> receivedEvents;

    public WebHook(String hookName) {
        if (hookName == null || hookName.strip().isBlank()) {
            throw new IllegalArgumentException("Webhook name cannot be null or blank");
        }
        this.hookName = hookName.strip();
        this.receivedEvents = new ArrayList<>();
    }

    public String getHookName() {
        return hookName;
    }

    public List<String> getReceivedEvents() {
        return Collections.unmodifiableList(receivedEvents);
    }

    @Override
    public void update(EventType eventType, String branch, String details) {
        if (eventType == null || branch == null || details == null) {
            throw new IllegalArgumentException("Event data cannot be null");
        }

        String eventRecord = String.format("[%s] Event: %s on branch '%s' - %s", hookName, eventType, branch, details);
        receivedEvents.add(eventRecord);
    }
}
