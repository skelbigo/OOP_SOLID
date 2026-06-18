package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reader {
    private final long id;
    private final String surname;
    private final String name;
    private final String patronymic;
    private final List<Book> borrowedBooks;

    public Reader(long id, String surname, String name, String patronymic) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be positive.");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (patronymic == null || patronymic.trim().isEmpty()) {
            throw new IllegalArgumentException("Patronymic cannot be null or empty.");
        }
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.borrowedBooks = new ArrayList<>();
    }

    public List<Book> getBorrowedBooks() {
        return Collections.unmodifiableList(borrowedBooks);
    }

    void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    public long getId() {
        return id;
    }

    boolean hasBorrowedBook(Book book) {
        return borrowedBooks.contains(book);
    }
}
