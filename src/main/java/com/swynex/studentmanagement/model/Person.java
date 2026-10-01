package com.swynex.studentmanagement.model;

import jakarta.persistence.*;

/**
 * Abstract base class demonstrating Encapsulation and Abstraction.
 * This class contains common attributes and behaviors for all persons in the system.
 */
@MappedSuperclass
public abstract class Person {

    // Encapsulation: private fields with getters/setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String email;

    // Constructor
    public Person() {
    }

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters and Setters (Encapsulation)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Abstraction: abstract method to be implemented by subclasses
    public abstract String getRole();
    
    // Abstraction: abstract method for displaying details
    public abstract String displayDetails();
}
