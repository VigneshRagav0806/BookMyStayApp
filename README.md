# Book My Stay – Hotel Booking Management System

## Overview

Book My Stay is a console-based Hotel Booking Management System developed using Core Java. The project demonstrates the practical use of fundamental data structures and object-oriented programming concepts in solving real-world problems such as booking management, inventory control, and error handling.

The system is designed incrementally through multiple use cases, each introducing a specific concept. The focus is on system behavior, correctness, and maintainability rather than graphical user interface design.

---

## Objectives

* Apply Core Java concepts in a real-world application
* Demonstrate the use of data structures for efficient system design
* Ensure fair request handling and prevent double booking
* Maintain consistency of room inventory
* Implement robust validation and error handling mechanisms

---

## Features

* Room booking management (Single, Double, Suite)
* Inventory tracking and updates
* Prevention of overbooking and invalid allocations
* Structured input validation
* Custom exception handling for booking errors
* Fail-fast system design to detect errors early
* Clear and informative error messages
* Continuous system operation without crashes

---

## Use Case 9: Error Handling & Validation

### Goal

Strengthen system reliability by validating inputs and preventing invalid state changes.

### Actors

* Guest – Provides booking details
* Invalid Booking Validator – Ensures correctness before processing

### Workflow

* Guest enters booking details
* System validates input and constraints
* Invalid inputs trigger immediate errors
* System displays meaningful failure messages
* Valid bookings are processed safely
* System continues running without interruption

---

## Key Concepts Implemented

* Input Validation
  Ensures only valid data enters the system

* Custom Exceptions
  Domain-specific exceptions improve clarity and debugging

* Fail-Fast Design
  Errors are detected early to avoid cascading failures

* State Protection
  Prevents invalid updates such as negative inventory

* Graceful Failure Handling
  System handles errors without crashing

* Correctness Over Happy Path
  Handles both valid and invalid scenarios effectively

---

## Technologies Used

* Java (Core Java)
* Java Collections Framework (HashMap)
* Object-Oriented Programming Principles

---

## Project Structure

* `UseCase9ErrorHandlingValidation.java` – Main program file
* `RoomInventory` – Manages room availability
* `BookingService` – Handles booking logic
* `InvalidBookingException` – Custom exception class

---

## How to Run

### Compile the program

```
javac UseCase9ErrorHandlingValidation.java
```

### Run the program

```
java UseCase9ErrorHandlingValidation
```

Note: Java is case-sensitive. Ensure file and class names match exactly.

---

## Example Scenarios

* Invalid room type results in an error message
* Booking more rooms than available is prevented
* Negative or zero booking values are rejected
* Valid bookings update inventory correctly

---

## Benefits

* Early detection of invalid inputs
* Prevention of data corruption
* Improved system stability
* Clear debugging and maintainability
* Real-world simulation of booking systems

---

## Limitations

* Console-based application (no graphical interface)
* No persistent storage (data resets on restart)
* Single-user system (no concurrency handling)

---

## Future Enhancements

* Add database integration for persistent storage
* Implement multi-user concurrency control
* Develop a web-based or GUI frontend
* Introduce booking history and reporting features
* Add authentication and user roles

---

## Conclusion

This project demonstrates how proper validation and error handling significantly improve system reliability. By focusing on correctness and fail-safe mechanisms, the system reflects real-world software engineering practices and prepares learners for building scalable and maintainable applications.
