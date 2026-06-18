package org.example;

public class Author {
    private final long id;
    private final String surname;
    private final String name;
    private final String patronymic;

    public Author(long id, String surname, String name, String patronymic) {
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
    }
}
