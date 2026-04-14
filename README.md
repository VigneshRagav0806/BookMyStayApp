# 🏨 Book My Stay – Hotel Booking Management System

## 📌 Overview

* Console-based hotel booking system using Core Java
* Focuses on real-world application of data structures and OOP
* Emphasizes core logic over UI design
* Built incrementally through multiple use cases

---

## 🎯 Use Case 7: Add-On Service Selection

* Adds support for optional services to reservations
* Does not modify core booking or inventory logic

---

## 👥 Actors

* Guest – selects add-on services
* Add-On Service – represents optional feature
* Add-On Service Manager – manages services and cost

---

## 🔄 Flow

* Guest selects one or more services
* Services stored in a list
* Mapped to reservation ID using a Map
* Additional cost calculated
* Booking and inventory remain unchanged

---

## 🧠 Key Concepts

* Business Extensibility
* One-to-Many Relationship
* Map + List (`Map<String, List<AddOnService>>`)
* Composition over Inheritance
* Separation of Concerns
* Cost Aggregation

---

## ✅ Features

* Multiple services per reservation
* Efficient reservation-service mapping
* Independent cost calculation
* Easy addition of new services
* No impact on core booking logic

---

## 🚀 How to Run

* Compile: `javac UseCase7AddOnServiceSelection.java`
* Run: `java UseCase7AddOnServiceSelection`

---

## 📦 Example Services

* Breakfast
* Airport Pickup
* Extra Bed

---

## 🌟 Benefits

* Modular and scalable design
* Clean separation of logic
* Real-world system modeling
* Easy maintenance and extension

---

## ⚠️ Previous Limitation (Use Case 6)

* Bookings were static
* No support for add-on services

---

## 🔮 Future Enhancements

* Full booking system integration
* Bill generation
* Database/file storage
* GUI or web interface

---

## 📚 Technologies

* Core Java
* Java Collections Framework
* Object-Oriented Programming
