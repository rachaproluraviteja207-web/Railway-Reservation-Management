# Railway Reservation and Passenger Management System

A Java CLI-based application designed to manage railway reservations, passengers, users, trains, and bookings using FileDB.

## 📌 Project Overview

The Railway Reservation and Passenger Management System is a console-based Java application developed to simulate railway reservation and passenger management operations.

The application provides separate functionalities for users and agents, including registration, login, passenger management, train management, booking, ticket cancellation, and booking information.

## 🚀 Features

### User Features
- User Registration
- User Login
- View Profile
- Passenger Management
- Search Trains
- Book Train Tickets
- View Booking Details
- Cancel Tickets

### Agent Features
- Add and Manage Trains
- View Train Details
- Manage Bookings
- View Passenger and Reservation Information

### Reservation Features
- Seat Type Selection
- Seat Number Management
- Normal and Tatkal Booking
- Fare Calculation
- PNR Generation
- Ticket Cancellation

## 🛠️ Technologies Used

- Java
- Java Collections Framework
- File Handling
- FileDB
- Java I/O
- Eclipse IDE
- Command Line Interface (CLI)

## 📂 Data Storage

The application uses FileDB/text files for storing application data:

- `users.txt`
- `passengers.txt`
- `trains.txt`
- `booking.txt`

## 🏗️ Project Structure

```text
Railway-Reservation-and-Passenger-Management-System
│
├── RailwayRegistration
│   └── src
│       └── registration
│           ├── RailwayRegistrationSystem.java
│           ├── Passenger.java
│           ├── User.java
│           ├── Train.java
│           └── Booking.java
│
├── Output2.pdf
└── README.md
```
## 🖥️ Project Output

The project execution output and screenshots are available below:

📄 [View Project Output](./Output2.pdf)

## ▶️ How to Run

1. Clone or download this repository.
2. Open the project in Eclipse IDE.
3. Make sure Java/JDK is installed.
4. Open the main class:
   `RailwayRegistrationSystem.java`
5. Run the application.
6. Use the console menu to interact with the system.

## 🎯 Learning Outcomes

Through this project, I gained practical experience in:

- Core Java programming
- Object-Oriented Programming
- Java Collections
- File Handling
- Console-based application development
- Data management using text files
- Building a modular Java application

## 🔮 Future Enhancements

- Database integration using MySQL/Oracle
- GUI-based interface
- Online payment integration
- Email/SMS ticket notifications
- Admin dashboard
- REST API integration

