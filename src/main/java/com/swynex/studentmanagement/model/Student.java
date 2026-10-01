package com.swynex.studentmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Student class demonstrating Inheritance and Polymorphism.
 * Extends Person and adds student-specific attributes.
 */
@Entity
public class Student extends Person {

    // Encapsulation: private fields with validation
    @NotBlank(message = "Student ID is required")
    @Column(unique = true)
    private String studentId;

    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotBlank(message = "Course is required")
    private String course;

    @NotNull(message = "Year is required")
    @Column(name = "academic_year")
    private Integer year;

    // Embedded Marks object
    @Embedded
    private Marks marks;

    // Constructor
    public Student() {
        this.marks = new Marks();
    }

    public Student(String studentId, String name, String username, String password, 
                   String email, String course, Integer year) {
        super(name, email);
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.course = course;
        this.year = year;
        this.marks = new Marks();
    }

    // Getters and Setters (Encapsulation)
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    // Polymorphism: Implementation of abstract method from Person
    @Override
    public String getRole() {
        return "STUDENT";
    }

    // Polymorphism: Override displayDetails method
    @Override
    public String displayDetails() {
        return String.format("Student[ID=%s, Name=%s, Course=%s, Year=%d]", 
                studentId, getName(), course, year);
    }
}
