package com.swynex.studentmanagement.model;

/**
 * User class for authentication and role management.
 * Demonstrates Encapsulation.
 */
public class User {

    // Encapsulation: private fields
    private String username;
    private String password;
    private String role; // ADMIN or STUDENT

    // Constructor
    public User() {
    }

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Check if user is admin
    public boolean isAdmin() {
        return "ADMIN".equals(role);
    }

    // Check if user is student
    public boolean isStudent() {
        return "STUDENT".equals(role);
    }
}
