package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LibraryService {
    private final Map<Long, Book> books = new HashMap<>();
    private final Map<Long, Reader> readers = new HashMap<>();

    public void addBook(Book book) {
        Objects.requireNonNull(book, "Book cannot be null");
        if (books.putIfAbsent(book.getId(), book) != null) {
            throw new IllegalArgumentException("Book with id " + book.getId() + " exists!");
        }
    }

    public void addReader(Reader reader) {
        Objects.requireNonNull(reader, "Reader cannot be null");
        if (readers.putIfAbsent(reader.getId(), reader) != null) {
            throw new IllegalArgumentException("Reader with id " + reader.getId() + " exists");
        }
    }

    public void borrowBook(long readerId, long bookId) {
        if (!books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found");
        }
        if (!readers.containsKey(readerId)) {
            throw new IllegalArgumentException("Reader with ID " + readerId + " not found");
        }
        Book book = books.get(bookId);
        if (book.isAvailable()) {
            Reader reader = readers.get(readerId);
            reader.borrowBook(book);
            book.markAsBorrowed(false);
        } else {
            throw new IllegalArgumentException("Book is already borrowed");
        }
    }

    public void returnBook(long readerId, long bookId) {
        if (!books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book with ID " + bookId + " not found");
        }
        if (!readers.containsKey(readerId)) {
            throw new IllegalArgumentException("Reader with ID " + readerId + " not found");
        }
        Book book = books.get(bookId);
        Reader reader = readers.get(readerId);
        if (reader.hasBorrowedBook(book)) {
            reader.returnBook(book);
            book.markAsAvailable(true);
        } else {
            throw new IllegalArgumentException("Reader did not borrow this book");
        }
    }
}
