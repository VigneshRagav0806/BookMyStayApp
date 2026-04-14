Project Overview
This module extends the Hotel Booking Management System by introducing booking history tracking and reporting
Focuses on storing confirmed reservations and enabling administrative analysis
Demonstrates practical use of Core Java and data structures without external databases
Objective
Maintain a record of all confirmed bookings
Provide operational visibility into past transactions
Enable report generation without modifying stored data
Actors
Admin: Reviews booking history and generates reports
Booking History: Stores confirmed reservations
Booking Report Service: Processes stored data to generate reports
Workflow
A booking is successfully confirmed
The reservation is added to booking history
Booking history maintains records in insertion order
Admin requests booking information or reports
System retrieves and displays stored reservations
Key Concepts Used
List Data Structure (ArrayList) used to store reservations
Ordered storage to maintain chronological sequence of bookings
Separation of concerns between data storage and reporting logic
Historical tracking to maintain an audit trail
Persistence mindset by treating in-memory data as long-term information
Features
Store confirmed reservations in booking history
Retrieve all stored reservations
Generate detailed booking reports
Generate summary reports including total bookings and revenue
Ensure reporting does not modify stored data
System Components
Reservation class: Represents individual booking details
BookingHistory class: Manages storage of reservations
BookingReportService class: Generates reports from booking data
Sample Output
Displays customer name, room type, number of nights, and total cost
Summary report shows total bookings and total revenue
Key Benefits
Provides a complete and traceable booking audit trail
Simplifies reporting and administrative analysis
Supports customer issue resolution through historical data
Improves system transparency and reliability
Limitations of Previous Use Case
No storage of completed bookings
No ability to review past transactions
No reporting functionality
Future Enhancements
Integration with a database such as MySQL
Export reports to formats like PDF or CSV
Add filters based on date, room type, or customer
Implement user authentication for admin access
Develop a graphical user interface
How to Run
Compile the program using: javac UseCase8BookingHistoryReport.java
Run the program using: java UseCase8BookingHistoryReport
Technologies Used
Core Java
Java Collections Framework (ArrayList)