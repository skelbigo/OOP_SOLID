package org.example.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client {
    private final String name;
    private final String email;
    private final List<String> inbox = new ArrayList<>();

    public Client(String name, String email) {
        if (name == null || name.strip().isBlank()) throw new IllegalArgumentException("Name cannot be blank");
        if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+$")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.name = name.strip();
        this.email = email.strip();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<String> getInbox() {
        return Collections.unmodifiableList(inbox);
    }

    public void receiveMessage(String message) {
        if (message == null || message.strip().isBlank()) {
            throw new IllegalArgumentException("Message cannot be null or blank");
        }
        inbox.add(message);
    }
}
