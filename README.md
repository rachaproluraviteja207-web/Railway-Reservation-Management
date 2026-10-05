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
