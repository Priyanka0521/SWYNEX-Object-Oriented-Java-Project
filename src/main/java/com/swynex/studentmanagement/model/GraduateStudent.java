package com.swynex.studentmanagement.model;

import jakarta.persistence.*;

/**
 * GraduateStudent class demonstrating multi-level Inheritance and Polymorphism.
 * Extends Student and adds graduate-specific attributes.
 */
@Entity
public class GraduateStudent extends Student {

    // Encapsulation: private field with getter/setter
    private String specialization;
    private String researchTopic;

    // Constructor
    public GraduateStudent() {
        super();
    }

    public GraduateStudent(String studentId, String name, String username, String password,
                          String email, String course, Integer year, String specialization, 
                          String researchTopic) {
        super(studentId, name, username, password, email, course, year);
        this.specialization = specialization;
        this.researchTopic = researchTopic;
    }

    // Getters and Setters (Encapsulation)
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getResearchTopic() {
        return researchTopic;
    }

    public void setResearchTopic(String researchTopic) {
        this.researchTopic = researchTopic;
    }

    // Polymorphism: Override getRole method
    @Override
    public String getRole() {
        return "GRADUATE_STUDENT";
    }

    // Polymorphism: Override displayDetails method
    @Override
    public String displayDetails() {
        return String.format("GraduateStudent[ID=%s, Name=%s, Course=%s, Year=%d, Specialization=%s]", 
                getStudentId(), getName(), getCourse(), getYear(), specialization);
    }
}
