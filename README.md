Spring Boot Login & Course Registration System

A full-stack Spring Boot web application featuring role-based authentication (Admin/Student), course registration with MySQL persistence, email notifications, an analytics dashboard, and a modern animated UI.

Features:

Authentication & Security:
Session-based login/logout using Spring Security
Passwords encrypted with BCrypt
Role-based access control — Admin and Student (User) roles
Forgot password flow — students submit a reset request that only the admin can approve and fulfill

Student Portal:
Self-registration (create account)
One-time course registration form (name, email, phone, course, address)
View their own submitted registration (read-only after submission)
Automatic confirmation email sent upon successful course enrollment
Course list in the registration form is pulled dynamically from what the admin has added

Admin Portal:
Auto-seeded default admin account on first run
Full CRUD on student course registrations (view, edit, delete)
Add/remove available courses
View and resolve pending student password reset requests
Analytics dashboard with a live bar chart (Chart.js) showing number of students enrolled per course
View email delivery status (sent/failed) per student registration

UI/UX:
Animated flowing gradient background
3D mouse-tracking tilt effect on cards
Glassmorphism navbar
Glowing animated headings
Smooth entrance animations on page load

Tech Stack:
LayerTechnologyBackendJava 17+, Spring Boot 3.5SecuritySpring Security (session-based, BCrypt)DatabaseMySQL 8ORMSpring Data JPA / HibernateTemplatingThymeleafEmailSpring Boot Starter Mail (SMTP/Gmail)ChartsChart.jsBuild ToolMaven (Maven Wrapper included)

Project Structure:

demo/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/
│   │   │   ├── config/          # Security config, admin data seeder
│   │   │   ├── controller/      # Auth, Student, Admin, User, ForgotPassword controllers
│   │   │   ├── entity/          # User, Course, CourseRegistration, PasswordResetRequest
│   │   │   ├── repository/      # Spring Data JPA repositories
│   │   │   ├── service/         # Business logic (User, Course, Registration, Email)
│   │   │   └── DemoApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/style.css
│   │       │   └── js/animations.js
│   │       ├── templates/       # Thymeleaf HTML pages
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md

Prerequisites:
Java 17 or higher
Maven (or use the included mvnw / mvnw.cmd wrapper)
MySQL Server 8.x running locally
A Gmail account with an App Password (for email notifications)

Run the application:

./mvnw spring-boot:run

Open your browser at:
http://localhost:8080

Usage Guide:

As a Student:
Register a new account at /register
Log in at /login
Fill out the course registration form (one-time submission)
Receive a confirmation email for your enrolled course
If you forget your password, use the Forgot Password link — this notifies the admin, who will reset it for you


As an Admin:
Log in with the default admin credentials
View the Dashboard for enrollment analytics and charts
Manage Courses — add or remove available courses
Manage Registrations — edit or delete any student's submission
Resolve Password Reset Requests submitted by students

