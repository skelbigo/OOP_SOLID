package org.example;

public class Book {
    private long id;
    private String title;
    private String description;
    private Author author;
    private boolean isAvailable;

    public Book(long id, String title, String description, Author author, boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.isAvailable = true;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
