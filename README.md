# The QuiZ App

The QuiZ App is an Android-based quiz application developed using Java. It fetches quiz questions dynamically from a MySQL database through a PHP-based API and provides a structured quiz experience with score evaluation.

---

## Overview

This application allows users to attempt quizzes in different categories. Currently, only the Maths quiz is active and fully functional. The app retrieves questions from a backend database and displays them in a user-friendly interface. At the end of the quiz, results are calculated and displayed.

---

## Features

- Dynamic question loading from database
- Category-based quiz interface
- Real-time score calculation
- Structured navigation (Home → Quiz → Result)
- Clean and responsive UI
- Retrofit-based API integration

---

## Tech Stack

- Frontend: Android (Java, XML)
- Backend API: PHP
- Database: MySQL
- Local Server: XAMPP
- Networking: Retrofit with Gson Converter

---

## Tools and Resources

- JSON to POJO Conversion: https://www.jsonschema2pojo.org/
- Localhost configuration: System network configuration (ipconfig) used to determine base URL

---

## Project Structure

```
com.example.quizapp

├── Retrofit/
│   ├── questionapi.java
│   └── retrofitinstance.java
│
├── repository/
│   └── quizrepository.java
│
├── modalquiz/
│   └── Question.java
│
├── ViewModel/
│   └── QuizViewModel.java
│
├── UI/
│   ├── MainActivity.java
│   ├── MainActivity_mathquiz.java
│   └── ResultsActivity.java
```

---

## Setup Instructions

1. Clone the repository:
   ```
   git clone https://github.com/your-username/quiz-app.git
   ```

2. Backend setup:
   - Install and run XAMPP
   - Place the PHP API file in:
     ```
     htdocs/quiz/myquizapi.php
     ```
   - Start Apache and MySQL

3. Database setup:
   - Create database: `my_quiz_db`
   - Create table: `maths_table`
   - Insert quiz data

4. Configure Base URL:
   - Use system network configuration to find your IPv4 address:
     ```
     ipconfig
     ```
   - Set base URL in the app:
     ```
     http://<your-ip-address>/quiz/
     ```
   - For emulator:
     ```
     http://10.0.2.2/quiz/
     ```

5. Run the application:
   - Open project in Android Studio
   - Build and run on emulator or device

---

##Ui Interface 
<img width="383" height="828" alt="Screenshot 2026-03-24 002301" src="https://github.com/user-attachments/assets/3dcb1be9-68aa-4eb8-a792-e308a46b1b6d" />
Right now only maths quiz is available in this Quiz App 1.0 version  


## Application Flow

- User opens the app and selects a quiz category
- Maths quiz is enabled and loads questions from the API
- User answers questions sequentially
- Score is calculated and displayed in the result screen
- User can return to the home screen

---

## Notes

- Ensure XAMPP server is running before launching the app
- API must return JSON in proper format matching the POJO model
- Internet permission must be enabled in AndroidManifest.xml

---

## License

This project is open-source and available for educational and development purposes.
