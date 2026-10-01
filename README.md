# SWYNEX Student Management System

A comprehensive web-based Student Marks Management System built with Java and Spring Boot, demonstrating Object-Oriented Programming (OOP) concepts including Encapsulation, Inheritance, Abstraction, and Polymorphism.

## Project Description

This project is a full-stack web application that allows administrators to manage student records and marks, while enabling students to view their own academic performance. The system features role-based authentication, automatic grade calculation, and a modern responsive user interface.

## Objective

The primary objective of this project is to demonstrate practical implementation of OOP concepts in a real-world application while providing a functional student management system for educational institutions.

## Features

### Admin Features
- Secure login with role-based authentication
- Add new students with personal information and marks
- View complete student details and marks
- Edit student information and marks
- Delete student records
- Search students by name or course
- View dashboard statistics (total students, average percentage, pass/fail counts)
- Logout functionality

### Student Features
- Secure login with username and password
- View personal information
- View subject-wise marks with individual grades
- View total marks, percentage, and overall grade
- Logout functionality

### System Features
- Automatic calculation of total marks, percentage, and grades
- Grade calculation based on predefined criteria:
  - 90-100: A+
  - 80-89: A
  - 70-79: B
  - 60-69: C
  - 50-59: D
  - Below 50: F
- Input validation for all fields
- Marks validation (0-100 range)
- Session-based authentication
- Responsive design for mobile and desktop

## Technologies Used

### Backend
- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Spring MVC** - Web framework
- **Spring Data JPA** - Data access layer
- **H2 Database** - In-memory database for development
- **Maven** - Build tool and dependency management
- **Jakarta Validation** - Input validation

### Frontend
- **Thymeleaf** - Server-side template engine
- **HTML5** - Markup language
- **CSS3** - Styling with modern design
- **JavaScript** - Client-side scripting (minimal)

### Development Tools
- **VS Code** - Integrated Development Environment

## Project Architecture

The project follows a clean, layered architecture with clear separation of concerns:

```
src/main/java/com/swynex/studentmanagement/
├── StudentManagementApplication.java    # Main application entry point
├── config/
│   └── DataInitializer.java             # Database initialization
├── controller/
│   ├── LoginController.java             # Login/logout handling
│   ├── AdminController.java              # Admin operations
│   └── StudentController.java           # Student operations
├── model/
│   ├── Person.java                      # Abstract base class
│   ├── Student.java                     # Student entity
│   ├── GraduateStudent.java             # Graduate student entity
│   ├── Marks.java                       # Marks embedded class
│   └── User.java                        # User authentication model
├── repository/
│   ├── StudentRepository.java           # Student data access
│   └── GraduateStudentRepository.java   # Graduate student data access
├── service/
│   ├── StudentService.java              # Business logic
│   └── LoginService.java               # Authentication logic
└── exception/
    ├── ResourceNotFoundException.java   # Custom exception
    └── GlobalExceptionHandler.java     # Global error handling

src/main/resources/
├── application.properties               # Application configuration
├── templates/
│   ├── login.html                       # Login page
│   ├── admin-dashboard.html             # Admin dashboard
│   ├── add-student.html                 # Add student form
│   ├── edit-student.html                # Edit student form
│   ├── view-student.html                # View student details
│   ├── student-dashboard.html           # Student dashboard
│   └── error.html                       # Error page
└── static/
    └── css/
        └── style.css                     # Application styles
```

## OOP Concepts Demonstrated

### 1. Encapsulation

Encapsulation is demonstrated by using private fields with public getters and setters to control access to class data.

**Classes demonstrating Encapsulation:**
- `Person.java` - Private fields (id, name, email) with getters/setters
- `Student.java` - Private fields (studentId, username, password, course, year, marks) with getters/setters
- `Marks.java` - Private fields (javaMarks, pythonMarks, databaseMarks, webDevMarks) with validation in setters
- `User.java` - Private fields (username, password, role) with getters/setters

**Example from Marks.java:**
```java
private double javaMarks;

public void setJavaMarks(double javaMarks) {
    if (javaMarks < 0 || javaMarks > 100) {
        throw new IllegalArgumentException("Java marks must be between 0 and 100");
    }
    this.javaMarks = javaMarks;
}
```

### 2. Inheritance

Inheritance is demonstrated by creating a class hierarchy where child classes inherit properties and behaviors from parent classes.

**Inheritance Structure:**
```
Person (Abstract Base Class)
    ↓
Student (Concrete Class)
    ↓
GraduateStudent (Concrete Class)
```

**Classes demonstrating Inheritance:**
- `Person.java` - Abstract base class with common attributes (id, name, email)
- `Student.java` - Extends Person, adds student-specific attributes (studentId, username, password, course, year, marks)
- `GraduateStudent.java` - Extends Student, adds graduate-specific attributes (specialization, researchTopic)

**Example from Student.java:**
```java
public class Student extends Person {
    private String studentId;
    private String username;
    // Inherits id, name, email from Person
}
```

### 3. Abstraction

Abstraction is demonstrated by using abstract classes and methods to hide implementation details while exposing essential functionality.

**Classes demonstrating Abstraction:**
- `Person.java` - Abstract class with abstract methods `getRole()` and `displayDetails()`

**Example from Person.java:**
```java
@MappedSuperclass
public abstract class Person {
    // Common attributes and methods
    
    public abstract String getRole();
    public abstract String displayDetails();
}
```

Subclasses (Student, GraduateStudent) must implement these abstract methods, providing their own implementations while the abstract class defines the contract.

### 4. Polymorphism

Polymorphism is demonstrated through method overriding and runtime polymorphism, where different classes provide their own implementations of methods defined in the parent class.

**Classes demonstrating Polymorphism:**
- `Student.java` - Overrides `getRole()` and `displayDetails()` from Person
- `GraduateStudent.java` - Overrides `getRole()` and `displayDetails()` from Student

**Example from Student.java:**
```java
@Override
public String getRole() {
    return "STUDENT";
}

@Override
public String displayDetails() {
    return String.format("Student[ID=%s, Name=%s, Course=%s, Year=%d]", 
            studentId, getName(), course, year);
}
```

**Example from GraduateStudent.java:**
```java
@Override
public String getRole() {
    return "GRADUATE_STUDENT";
}

@Override
public String displayDetails() {
    return String.format("GraduateStudent[ID=%s, Name=%s, Course=%s, Year=%d, Specialization=%s]", 
            getStudentId(), getName(), getCourse(), getYear(), specialization);
}
```

Runtime polymorphism is demonstrated when a Person reference can point to Student or GraduateStudent objects, and the appropriate method implementation is called at runtime.

## Database Information

### Database Configuration
- **Database Type:** H2 (In-memory)
- **Connection URL:** jdbc:h2:mem:studentdb
- **Username:** sa
- **Password:** (empty)
- **Hibernate DDL:** update (auto-creates/updates tables)

### Tables Created
1. **students** - Stores student information and marks
   - id (Primary Key, Auto-generated)
   - student_id (Unique)
   - name
   - email
   - username (Unique)
   - password
   - course
   - year
   - marks_java
   - marks_python
   - marks_database
   - marks_web_dev

2. **graduate_students** - Stores graduate student information
   - Inherits all fields from students table
   - specialization
   - research_topic

### H2 Console
The H2 console is available at: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:studentdb`
- Username: `sa`
- Password: (leave empty)

## How to Run the Project

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Maven 3.6 or higher
- VS Code or any Java IDE

### Steps to Run

1. **Clone or download the project**

2. **Navigate to the project directory**
   ```bash
   cd SWYNEX-Object-Oriented-Java-Project
   ```

3. **Build the project using Maven**
   ```bash
   mvn clean install
   ```

4. **Run the application**
   ```bash
   mvn spring-boot:run
   ```
   
   Or run the main class `StudentManagementApplication.java` from your IDE.

5. **Access the application**
   - Open your web browser
   - Navigate to: `http://localhost:8081`

### Alternative: Using VS Code

1. Open the project in VS Code
2. Ensure the Extension Pack for Java is installed
3. Open `StudentManagementApplication.java`
4. Click the "Run" button above the main method
5. The application will start on `http://localhost:8081`

## Default Login Credentials

### Admin Account
- **Username:** admin
- **Password:** admin123

### Student Account
- **Username:** student101
- **Password:** student123

*Note: A default student account is automatically created when the application starts for testing purposes.*

## Screenshots

### Login Page
[Placeholder for login page screenshot]

### Admin Dashboard
[Placeholder for admin dashboard screenshot]

### Add Student Form
[Placeholder for add student form screenshot]

### Student Dashboard
[Placeholder for student dashboard screenshot]

## Application Flow

1. **User opens the application** → Redirected to login page
2. **User enters credentials** → System validates username and password
3. **If credentials are valid:**
   - **Admin user** → Redirected to Admin Dashboard
   - **Student user** → Redirected to Student Dashboard
4. **Admin can:**
   - View all students
   - Add new students
   - Edit student details
   - Delete students
   - Search students
   - View statistics
5. **Student can:**
   - View personal information
   - View subject-wise marks
   - View total, percentage, and grade
6. **User clicks logout** → Session cleared, redirected to login page

## Future Enhancements

- Add Spring Security for enhanced authentication
- Implement password encryption (BCrypt)
- Add email notifications for students
- Export student data to PDF/Excel
- Add attendance management
- Implement parent portal
- Add course management
- Implement batch operations
- Add data visualization charts
- Support for multiple subjects per semester
- Add teacher portal for subject-wise marks entry
- Implement audit logs for tracking changes
- Add backup and restore functionality
- Deploy to cloud platform (AWS/Azure/Heroku)

## Error Handling

The application includes comprehensive error handling:

- **ResourceNotFoundException** - Thrown when a requested resource is not found
- **IllegalArgumentException** - Thrown for invalid input (duplicate username, invalid marks range)
- **GlobalExceptionHandler** - Catches exceptions globally and displays user-friendly error messages
- **Validation** - Jakarta Validation annotations ensure data integrity

## Validation Rules

- **Student ID:** Required, unique
- **Name:** Required
- **Email:** Required, valid email format
- **Username:** Required, unique
- **Password:** Required, cannot be empty
- **Course:** Required
- **Year:** Required (1-4)
- **Marks:** Required, must be between 0 and 100

## Security Considerations

- Session-based authentication
- Role-based access control (Admin/Student)
- Admin pages protected from student access
- Student pages protected from unauthenticated access
- Logout clears session data
- Input validation to prevent injection attacks
- **Note:** This is an educational project. For production use, implement:
  - Password encryption
  - HTTPS
  - CSRF protection
  - SQL injection prevention
  - Proper authentication framework (Spring Security)

## License

This project is created for educational purposes as part of a Java OOP assignment.

## Contact

For questions or feedback about this project, please contact the development team.

---

**Project Name:** SWYNEX Student Management System  
**Version:** 1.0.0  
**Last Updated:** September 2026
