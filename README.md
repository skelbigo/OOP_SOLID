# OOP, SOLID and Design Patterns — Learning Repository

This repository is my Java learning portfolio focused on object-oriented programming, SOLID principles, data validation, unit testing, and common design patterns.

Each folder is a separate practice project. The goal of this repository to learn how to design small, clean, testable, and extensible modules.

## What I practiced

- OOP: classes, interfaces, inheritance, polymorphism, encapsulation.
- SOLID: single responsibility, dependency on abstractions, open/closed design.
- Design Patterns: Factory, Abstract Factory, Strategy, Decorator, Observer, Iterator.
- Defensive programming: validation of `null`, blank strings, invalid arguments, and broken states.
- Immutable/read-only collections: `final` fields, `Collections.unmodifiableList`, `Collections.unmodifiableMap`.
- Unit testing: JUnit tests for happy paths, invalid inputs, and edge cases.

## Tech Stack

- Java
- JUnit 5
- Maven

## Project List

| # | Project name | Technical assignment / implementation goal | Main topics |
|---:|---|---|---|
| 1 | `BankAccountSystem` | Implement a bank account domain model with validation for owner, id, balance, and operations. Add deposit/withdraw logic and protect the object from invalid state. | OOP, encapsulation, domain model, validation |
| 2 | `OnlineStoreShoppingCart` | Implement an online shopping cart: products, adding/removing items, total price, item count, duplicate products, and protection of the internal product list. | OOP, immutable model, collections |
| 3 | `StudentsAndGrades` | Implement a student model with grades: adding grades, grade range validation, average score, pass/fail status, and read-only access to grades. | OOP, validation, unit tests |
| 4 | `DoubleIterator` | Implement an iterator that returns each array element twice. Example: `1, 2, 3` → `1, 1, 2, 2, 3, 3`. | Iterator |
| 5 | `RepeatingIterator` | Implement a generic iterator that returns each array element `N` times. Validate `N > 0`, empty arrays, and iteration end behavior. | Iterator, validation |
| 6 | `TableIterator` | Implement an iterator for grid/table traversal. Example: columns `A, B, C` and rows `1, 2` → `A1, A2, B1, B2, C1, C2`. | Iterator |
| 7 | `EvenIndexList` | Implement a list decorator that exposes only elements located at even indexes of the original list. The decorator should work as a live view. | Decorator, collections |
| 8 | `ReadOnlyCollections` | Implement a read-only list decorator: reading is allowed, while `add`, `remove`, `set`, and `iterator.remove` must be blocked. | Decorator, immutability |
| 9 | `AccessLoggingWrapper` | Implement a list decorator that logs access to list methods such as `get()` or `size()` without changing the original list behavior. | Decorator, logging |
| 10 | `ECommerceDeliveryStrategy` | Implement delivery cost calculation for an order. Different delivery methods should be represented as separate strategies: courier, express, pickup, etc. | Strategy |
| 11 | `ProductCatalogSorter` | Implement a product catalog with different sorting strategies: by price, rating, popularity, and name. The original product list should not be mutated. | Strategy, Comparator |
| 12 | `GlobalTaxCalculator` | Implement a global tax calculator for different regions/countries. The tax formula should be selected through a strategy instead of `if/switch` logic in client code. | Strategy, SOLID |
| 13 | `SmartDealerStrategies` | Implement card dealing strategies for different games: poker, Texas Hold’em, bridge/fool/custom. Each game has its own dealing strategy, extra stacks, and remaining cards. | Strategy, collections |
| 14 | `PizzeriaSimpleFactory` | Implement a pizza factory. Based on pizza type, the factory creates a concrete pizza object: Margherita, Pepperoni, Four Cheese, Vegan, etc. | Simple Factory |
| 15 | `NotificationFactory` | Implement a message factory for Email, SMS, Telegram, and Push notifications. Each message type has its own recipient validation and sending format. | Factory, Template Method |
| 16 | `InteriorStyleFactory` | Implement Abstract Factory for furniture families: Modern, Victorian, Minimalistic. Each factory creates a compatible set of Chair, Table, and Sofa. | Abstract Factory |
| 17 | `StoryTaleFactory` | Implement Abstract Factory for story generation. Different factories create related story elements: Hero, Villain, Conflict, and Ending. | Abstract Factory |
| 18 | `YouTubeObserverPattern` | Implement a YouTube channel and subscribers. Users subscribe to a channel, receive notifications about new videos, can unsubscribe, and duplicate subscriptions are prevented. | Observer |
| 19 | `SmartStoreNotifier` | Implement a store notification system with category-based subscriptions. Subscribers receive notifications only for the categories they subscribed to. | Observer, category-based events |
| 20 | `GitWebhookObserver` | Implement a Git repository webhook observer. Webhooks subscribe to commit or merge events for specific branches. Commit events notify commit subscribers, while merge events notify target branch subscribers. | Observer |
| 21 | `Dish` | Implement a food ordering pipeline: a factory creates base dishes, decorators add options, a strategy defines delivery, and an order generates receipt and total price. | Factory, Decorator, Strategy |
| 22 | `SmartCourseAcademy` | Implement a course academy platform: a factory creates courses, students subscribe to courses, receive lessons/deadlines/grades, and a strategy defines evaluation logic. | Factory, Observer, Strategy |
| 23 | `MiniTrelloBoard` | Implement a mini Trello board: tasks are stored in TODO/IN_PROGRESS/DONE columns, an iterator traverses all columns, a strategy sorts tasks, and an observer reacts to DONE tasks. | Iterator, Strategy, Observer |
| 24 | `SmartHotelReservation` | Implement a hotel booking system: a factory creates rooms, a strategy calculates booking price, and an observer sends booking confirmation, cancellation, and check-in reminders. | Factory, Strategy, Observer |
| 25 | `untitled` | Implement an RPG engine: a factory creates heroes, a strategy defines attack behavior, decorators apply buffs, and an observer logs combat events, damage, victory, and level-ups. | Factory, Strategy, Decorator, Observer |
| 26 | `LibraryBookManagement` | Implement a library management system: books, users, add/search/borrow/return operations, data validation, and protection of internal collections. | OOP, collections, validation |

## Summary

This repository demonstrates my progress in learning Java, OOP, SOLID principles, and design patterns. I gradually moved from simple models to more integrated systems where multiple patterns work together in one project.
