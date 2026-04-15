# Book My Stay – Booking Cancellation & Inventory Rollback (Use Case 10)

## Overview

This module extends the Book My Stay Hotel Booking Management System by introducing booking cancellation functionality with safe rollback mechanisms. It ensures that previously confirmed bookings can be reversed without compromising system consistency or inventory accuracy.

The implementation focuses on controlled state reversal, ensuring that all system changes made during booking are properly undone during cancellation.

---

## Objective

* Enable safe cancellation of confirmed bookings
* Maintain accurate inventory after cancellations
* Ensure consistent system state through rollback mechanisms
* Prevent invalid or duplicate cancellation requests

---

## Features

* Booking creation with inventory allocation
* Booking cancellation with rollback support
* Inventory restoration after cancellation
* Booking history tracking with status (Active/Cancelled)
* Stack-based rollback tracking (LIFO order)
* Validation of booking existence before cancellation
* Prevention of duplicate cancellations
* Stable and predictable system behavior

---

## Use Case 10: Booking Cancellation & Inventory Rollback

### Goal

Allow users to cancel bookings while safely reversing all related system changes.

### Actors

* Guest – Initiates booking cancellation
* Cancellation Service – Validates and processes rollback

### Workflow

* Guest requests cancellation using Booking ID
* System validates booking existence and status
* Booking ID is pushed into rollback stack
* Inventory is restored for the cancelled booking
* Booking status is updated to "Cancelled"
* System maintains consistent state after operation

---

## Key Concepts Implemented

### State Reversal

Reverts previously completed booking operations to maintain system consistency.

### Stack Data Structure

Uses `Stack<String>` to store cancelled booking IDs and track rollback history.

### LIFO Rollback Logic

Latest booking cancellations are handled first, mimicking real-world undo operations.

### Controlled Mutation

Ensures rollback steps are executed in a strict sequence to avoid partial updates.

### Inventory Restoration

Immediately updates room availability after cancellation.

### Validation

Prevents:

* Cancellation of non-existent bookings
* Duplicate cancellation of the same booking

---

## Technologies Used

* Java (Core Java)
* Java Collections Framework (HashMap, Stack)
* Object-Oriented Programming

---

## Project Structure

* `UseCase10BookingCancellation.java` – Main program file
* `RoomInventory` – Manages room availability and updates
* `BookingService` – Handles booking and cancellation logic
* `Booking` – Represents booking data
* `BookingException` – Custom exception class

---

## How to Run

### Compile the program

```
javac UseCase10BookingCancellation.java
```

### Run the program

```
java UseCase10BookingCancellation
```

Note: Java is case-sensitive. Ensure class and file names match exactly.

---

## Example Scenarios

* Cancelling a valid booking restores inventory correctly
* Cancelling a non-existent booking displays an error
* Attempting to cancel an already cancelled booking is rejected
* Rollback stack maintains recent cancellations in LIFO order

---

## Benefits

* Ensures safe recovery from booking operations
* Maintains consistent inventory state
* Provides predictable rollback behavior
* Enhances system reliability and correctness

---

## Limitations

* Console-based application (no GUI)
* No persistent storage (data resets on restart)
* Single-user system (no concurrency support)

---

## Future Enhancements

* Add undo functionality for cancellations
* Integrate database for persistent storage
* Implement multi-user concurrency control
* Develop web or GUI-based interface
* Add detailed booking reports and analytics

---

## Conclusion

This use case demonstrates how rollback mechanisms can be implemented using stacks and controlled state changes. By ensuring that cancellations are handled safely and consistently, the system reflects real-world requirements for reliable and maintainable booking systems.
