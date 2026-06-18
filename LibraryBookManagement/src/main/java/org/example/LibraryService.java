package org.example;

import java.util.HashMap;
import java.util.Map;

public class LibraryService {
    private Map<Long, Book> books = new HashMap<>();
    private Map<Long, Reader> readers = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public void addReader(Reader reader) {
        readers.put(reader.getId(), reader);
    }

    public void borrowBook(long readerId, long bookId) {
        if (!books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book with ID " + bookId + "not found");
        }
        if (!readers.containsKey(readerId)) {
            throw new IllegalArgumentException("Reader with ID " + readerId + "not found");
        }
        Book book = books.get(bookId);
        if (book.isAvailable()) {
            Reader reader = readers.get(readerId);
            reader.getBorrowedBooks().add(book);
            book.setAvailable(false);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void returnBook(long readerId, long bookId) {
        if (!books.containsKey(bookId)) {
            throw new IllegalArgumentException("Book with ID " + bookId + "not found");
        }
        if (!readers.containsKey(readerId)) {
            throw new IllegalArgumentException("Reader with ID " + readerId + "not found");
        }
        Book book = books.get(bookId);
        Reader reader = readers.get(readerId);
        if (reader.getBorrowedBooks().contains(book)) {
            reader.getBorrowedBooks().remove(book);
            book.setAvailable(true);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
