# Book My Stay – Data Persistence & System Recovery (Use Case 12)

## Overview

This module enhances the Book My Stay Hotel Booking Management System by introducing data persistence and recovery mechanisms. It ensures that critical system data such as booking history and room inventory is preserved across application restarts.

The implementation demonstrates how in-memory data can be serialized into a file and later restored, enabling the system to resume operations from its last known valid state.

---

## Objective

* Persist booking and inventory data to a file
* Restore system state during application startup
* Ensure continuity of system operations across restarts
* Handle missing or corrupted data safely

---

## Features

* File-based persistence using serialization
* Automatic state restoration on system startup
* Inventory snapshot storage and recovery
* Booking history preservation
* Graceful handling of missing or corrupted files
* Seamless continuation of system operations

---

## Use Case 12: Data Persistence & System Recovery

### Goal

Ensure that system state survives application shutdown and restart.

### Actors

* System – Initiates save and load operations
* Persistence Service – Handles file storage and retrieval

### Workflow

* System prepares for shutdown
* Current state (inventory + bookings) is serialized
* Data is written to a file
* On restart, persisted data is loaded
* System restores inventory and booking state
* Application resumes normal operation

---

## Key Concepts Implemented

### Stateful Applications

Maintains data across multiple executions instead of resetting on restart.

### Persistence

Stores application data in a durable medium (file system).

### Serialization

Converts objects into a byte stream for storage.

### Deserialization

Reconstructs objects from stored data back into memory.

### Inventory Snapshot

Captures system state at a specific moment for accurate recovery.

### Failure Tolerance

Handles missing or corrupted data without crashing the system.

### Preparation for Database Systems

Introduces persistence concepts before transitioning to databases.

---

## Technologies Used

* Java (Core Java)
* Java I/O Streams
* Serialization (`Serializable` interface)
* Object Streams (`ObjectInputStream`, `ObjectOutputStream`)
* Java Collections Framework (HashMap)

---

## Project Structure

* `UseCase12DataPersistenceRecovery.java` – Main program file
* `RoomInventory` – Manages inventory state
* `BookingService` – Handles booking operations
* `Booking` – Represents booking data
* `SystemState` – Wrapper class for persistence
* `PersistenceService` – Handles saving and loading data

---

## How to Run

### Compile the program

```id="r1"
javac UseCase12DataPersistenceRecovery.java
```

### Run the program

```id="r2"
java UseCase12DataPersistenceRecovery
```

Note: Java is case-sensitive. Ensure file and class names match exactly.

---

## Example Scenarios

* First run starts with default inventory and empty bookings
* After saving, restarting restores previous bookings and inventory
* Missing file → system starts fresh without crashing
* Corrupted file → system falls back to safe defaults

---

## Benefits

* Prevents data loss across restarts
* Reflects real-world system behavior
* Improves reliability and user trust
* Introduces durability concepts essential for production systems

---

## Limitations

* File-based storage (not suitable for large-scale systems)
* No concurrency handling in persistence
* Data format is not human-readable (binary serialization)

---

## Future Enhancements

* Replace file storage with database (MySQL, PostgreSQL)
* Use JSON/XML for readable persistence
* Add backup and recovery mechanisms
* Implement versioning for stored data
* Introduce concurrency-safe persistence

---

## Conclusion

This use case demonstrates the importance of persistence in real-world applications. By enabling data storage and recovery, the system evolves from a temporary in-memory model to a more durable and production-ready design.
