package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reader {
    private long id;
    private String surname;
    private String name;
    private String patronymic;
    private List<Book> borrowedBooks;

    public Reader(long id, String surname, String name, String patronymic) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.borrowedBooks = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setBorrowedBooks(List<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
