# College Management System

A robust and scalable **College Management System** built in Java. This console-based application demonstrates core Java concepts such as object-oriented programming (OOP), CRUD operations, file handling for data persistence, efficient searching and sorting algorithms, and validation techniques. It provides separate interfaces for student and faculty administration, along with secure student login functionality.


## Features

- **Student Administration:**
  - Add, update, delete, and view student records.
  - Validates student IDs by checking for duplicates and ensuring data correctness.
  - Utilizes **binary search** for quick record lookup and updating.
  - Implements **bubble sort** to maintain records in ascending order by student ID.
  - Automatically generates email and password credentials based on student details.
  - Persists data using CSV file handling.

- **Faculty Administration:**
  - CRUD operations for managing faculty records.
  - Faculty-specific details include subject, years of experience, and qualification.
  - Supports sorting and searching of faculty records.

- **Student Interface:**
  - Secure student login using the email and password assigned during registration.
  - Upon login, students can view their personal details and academic scorecards.

- **Data Persistence:**
  - Uses CSV file handling to store and retrieve records.
  - Loads records on startup and saves updates on exit.

- **Modular & Clean Code:**
  - Organized with a modular architecture separating student management, faculty management, and utility functions.
  - Clean, maintainable code following best practices and OOP principles. <br>

## Project Overview

This project began as a basic CRUD application for managing student records and has evolved into a comprehensive college management system. The system is designed to be easily customizable and scalable to meet various institutional needs. It emphasizes data integrity, efficient record management, and seamless module integration while providing a practical demonstration of core Java concepts.

## Technologies Used

- **Java SE:** Core Java language features, including OOP, exception handling, and file I/O.
- **File Handling (CSV):** For data persistence.
- **Algorithms:** Binary search for record lookup and bubble sort for maintaining sorted order.
- **IntelliJ IDEA:** Primary development environment.
- **Git & GitHub:** Version control and project hosting.

## Installation

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/college-management-system.git
   cd college-management-system

2. **Compile the Code:**
  - Open the project in IntelliJ IDEA (or your preferred Java IDE).
  - Build the project using the IDE's built-in build tools.
  - Run the Application:

3. **Student Administration:**
  - Run StudentAdminInterface.java
  - Faculty Administration: Run FacultyAdminInterface.java
  - Student Login: Run StudentInterface.java
  - Alternatively, run MainApp.java to navigate between interfaces.

## Usage

1. **Student Administration:**
- Launch the StudentAdminInterface to manage student records.
- Records are validated to prevent duplicate or invalid IDs.
- Student records are automatically sorted by ID and saved to a CSV file for persistence.

2. **Faculty Administration:**
- Use the FacultyAdminInterface for faculty record management.
- Perform operations such as adding, updating, deleting, sorting, and searching for faculty.

3. **Student Login:**
- Students log in using their registered email and password.
- Upon successful login, the student's scorecard and details are displayed.

## Algorithms & Implementation

1. **Binary Search:**
- Used for efficient searching and updating of records in sorted arrays.

2. **Bubble Sort:**
- Ensures that student records remain in ascending order by student ID.

3. **ID Duplication Check:**
- Validates that no duplicate or invalid IDs are added to the system.

