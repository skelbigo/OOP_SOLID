package org.example;

import java.util.Objects;

public class Book {
    private final long id;
    private final String title;
    private final String description;
    private final Author author;
    private boolean isAvailable;

    public Book(long id, String title, String description, Author author) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
        Objects.requireNonNull(author, "Author cannot be null.");

        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.isAvailable = true;
    }

    public long getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    void markAsBorrowed(boolean borrowed) {
        isAvailable = borrowed;
    }

    void markAsAvailable(boolean available) {
        isAvailable = available;
    }
}
