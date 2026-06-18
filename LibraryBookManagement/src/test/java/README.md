# Library Book Management System

A simple and robust Java-based application for managing library operations. This project demonstrates core Object-Oriented Programming (OOP) principles, specifically focusing on data encapsulation and unit testing.

## Technologies Used
* **Java** (Collections, Exceptions)
* **Maven** (Project management)
* **JUnit 5** (Unit testing for positive and negative scenarios)

## Key Concept: Encapsulation
In this project, **encapsulation** is strictly applied to protect the integrity of the data:
* All fields in the `Book`, `Author`, and `Reader` classes are marked as `private`.
* Direct access to change the book's availability status is restricted.
* To change a book's status from "available" to "borrowed", you cannot simply write `book.isAvailable = false`. You must use the specific `borrowBook` method in the `LibraryService`.
* The `LibraryService` acts as a guard: it checks if the book exists and is available *before* changing its status. This completely prevents logical bugs, such as issuing a book that is already taken by another reader.

## How to Run Tests
To verify the business logic, run the following Maven command in the terminal:
`mvn clean test`