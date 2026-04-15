# Book My Stay – Concurrent Booking Simulation (Thread Safety) (Use Case 11)

## Overview

This module extends the Book My Stay Hotel Booking Management System by introducing concurrent booking simulation using multithreading. It demonstrates how multiple users interacting with shared system resources can lead to inconsistencies if not handled properly, and how synchronization ensures correctness.

The focus of this use case is on maintaining data integrity and preventing race conditions when multiple booking requests are processed simultaneously.

---

## Objective

* Simulate multiple users booking rooms concurrently
* Demonstrate issues caused by race conditions
* Ensure thread-safe access to shared resources
* Maintain consistent inventory under concurrent load

---

## Features

* Multi-threaded booking request simulation
* Shared booking queue for concurrent processing
* Thread-safe room allocation
* Synchronized access to inventory
* Prevention of double booking
* Consistent system behavior under concurrent execution

---

## Use Case 11: Concurrent Booking Simulation

### Goal

Ensure correct room allocation when multiple booking requests are processed simultaneously.

### Actors

* Multiple Guests – Submit booking requests at the same time
* Concurrent Booking Processor – Handles requests using multiple threads

### Workflow

* Multiple booking requests are generated concurrently
* Requests are placed in a shared queue
* Threads fetch requests in a controlled manner
* Inventory updates occur inside synchronized blocks
* System processes all requests without inconsistencies

---

## Key Concepts Implemented

### Race Conditions

Occurs when multiple threads modify shared data simultaneously, leading to unpredictable outcomes.

### Thread Safety

Ensures shared resources behave correctly under concurrent access.

### Shared Mutable State

Inventory and booking queue are shared among threads and require controlled access.

### Critical Sections

Sections of code that modify shared data are protected to allow only one thread at a time.

### Synchronized Access

Uses Java synchronization to prevent conflicting updates.

### Concurrency vs Parallelism

Focuses on correctness of overlapping operations rather than execution speed.

---

## Technologies Used

* Java (Core Java)
* Java Multithreading (Thread, Runnable)
* Java Collections Framework (Queue, HashMap)
* Synchronization (synchronized blocks/methods)

---

## Project Structure

* `UseCase11ConcurrentBookingSimulation.java` – Main program file
* `RoomInventory` – Shared inventory with synchronized updates
* `BookingRequest` – Represents a booking request
* `BookingProcessor` – Runnable class handling requests
* `SharedQueue` – Thread-safe request queue

---

## How to Run

### Compile the program

```id="c1"
javac UseCase11ConcurrentBookingSimulation.java
```

### Run the program

```id="c2"
java UseCase11ConcurrentBookingSimulation
```

Note: Java is case-sensitive. Ensure class and file names match exactly.

---

## Example Scenario

* Multiple threads attempt to book rooms at the same time
* Without synchronization → Overbooking may occur
* With synchronization → Inventory remains consistent and correct

---

## Benefits

* Prevents race conditions and data corruption
* Ensures correct booking under concurrent load
* Simulates real-world multi-user systems
* Builds foundation for scalable applications

---

## Limitations

* Console-based simulation (no GUI)
* No persistent storage
* Limited to basic thread synchronization (no advanced concurrency tools)

---

## Future Enhancements

* Use advanced concurrency utilities (Locks, Executors)
* Add database-level concurrency control
* Implement real-time booking system with REST APIs
* Introduce load testing and performance metrics
* Build web-based multi-user interface

---

## Conclusion

This use case highlights the importance of thread safety in systems where multiple users interact simultaneously. By introducing synchronization and controlled access to shared resources, the system ensures correctness, reliability, and consistency under concurrent conditions.
