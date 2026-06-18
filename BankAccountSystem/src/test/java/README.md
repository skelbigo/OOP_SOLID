# Bank Account System

A robust Java-based application that simulates a simple banking system. This project is designed to demonstrate advanced Object-Oriented Programming (OOP) principles, clean architecture, and strict state management.

## Technologies Used
* **Java** (UUID, Collections, Exceptions)
* **Maven** (Build automation and dependency management)
* **JUnit 5** (Comprehensive unit testing)

## Key Architectural Concepts

### 1. Rich Domain Model
Unlike the "Anemic Domain Model" anti-pattern, the `Account` class in this system is fully responsible for its own business rules. It does not blindly expose its data. Instead, it handles `deposit` and `withdraw` operations internally, ensuring that the balance is updated correctly and an immutable `Transaction` object is created and recorded automatically.

### 2. Strict Encapsulation
* **No Setters:** The balance cannot be changed directly from the outside (there is no `setBalance` method). All state changes happen through controlled business methods.
* **Immutable Collections:** The transaction history is exposed via `Collections.unmodifiableList()`. This prevents external code from adding, modifying, or deleting transactions directly, protecting the audit trail.
* **Final Fields:** Fields that must remain constant after object creation (like account ID, owner name, transaction ID) are declared as `final`.

### 3. Fail-Fast Validation
The system throws precise exceptions (e.g., `IllegalArgumentException`, `NullPointerException`) immediately at the constructor level or method entry points if invalid data is provided (e.g., negative amounts, empty names, null objects).

### 4. DRY Principle (Don't Repeat Yourself)
The `AccountService` acts as a clean coordinator. Repetitive logic, such as checking if an account exists before an operation, is extracted into a private helper method (`findAccountOrThrow`).

## Unique Identifiers
Every financial transaction is assigned a universally unique identifier (`UUID`) upon creation to ensure a reliable and traceable transaction history.

## How to Run Tests
The system is fully covered by unit tests, including positive paths, negative paths, and encapsulation breach attempts. To run the tests, use the following Maven command:
`mvn clean test`