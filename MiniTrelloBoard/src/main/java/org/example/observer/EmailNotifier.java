package org.example.observer;

import org.example.models.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmailNotifier implements TaskObserver {
    private final String email;
    private final List<String> sentEmails = new ArrayList<>();

    public EmailNotifier(String email) {
        if (email == null || email.strip().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        this.email = email.strip();
    }

    public List<String> getSentEmails() {
        return Collections.unmodifiableList(sentEmails);
    }

    @Override
    public void onTaskDone(Task task) {
        String message = String.format("Sending email to %s: Task '%s' is DONE!", email, task.getName());
        sentEmails.add(message);
    }
}
