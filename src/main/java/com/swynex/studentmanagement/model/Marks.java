package com.swynex.studentmanagement.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Embedded class for student marks.
 * Demonstrates Encapsulation with validation logic.
 */
@Embeddable
public class Marks {

    @Column(name = "java_marks")
    private double javaMarks;

    @Column(name = "python_marks")
    private double pythonMarks;

    @Column(name = "database_marks")
    private double databaseMarks;

    @Column(name = "web_dev_marks")
    private double webDevMarks;

    // Constructor
    public Marks() {
    }

    public Marks(double javaMarks, double pythonMarks, double databaseMarks, double webDevMarks) {
        setJavaMarks(javaMarks);
        setPythonMarks(pythonMarks);
        setDatabaseMarks(databaseMarks);
        setWebDevMarks(webDevMarks);
    }

    // Getters and Setters with validation (Encapsulation)
    public double getJavaMarks() {
        return javaMarks;
    }

    public void setJavaMarks(double javaMarks) {
        if (javaMarks < 0 || javaMarks > 100) {
            throw new IllegalArgumentException("Java marks must be between 0 and 100");
        }
        this.javaMarks = javaMarks;
    }

    public double getPythonMarks() {
        return pythonMarks;
    }

    public void setPythonMarks(double pythonMarks) {
        if (pythonMarks < 0 || pythonMarks > 100) {
            throw new IllegalArgumentException("Python marks must be between 0 and 100");
        }
        this.pythonMarks = pythonMarks;
    }

    public double getDatabaseMarks() {
        return databaseMarks;
    }

    public void setDatabaseMarks(double databaseMarks) {
        if (databaseMarks < 0 || databaseMarks > 100) {
            throw new IllegalArgumentException("Database marks must be between 0 and 100");
        }
        this.databaseMarks = databaseMarks;
    }

    public double getWebDevMarks() {
        return webDevMarks;
    }

    public void setWebDevMarks(double webDevMarks) {
        if (webDevMarks < 0 || webDevMarks > 100) {
            throw new IllegalArgumentException("Web Development marks must be between 0 and 100");
        }
        this.webDevMarks = webDevMarks;
    }

    // Calculate total marks
    public double getTotalMarks() {
        return javaMarks + pythonMarks + databaseMarks + webDevMarks;
    }

    // Calculate percentage
    public double getPercentage() {
        return (getTotalMarks() / 400) * 100;
    }

    // Calculate grade based on percentage
    public String getGrade() {
        double percentage = getPercentage();
        if (percentage >= 90) return "A+";
        if (percentage >= 80) return "A";
        if (percentage >= 70) return "B";
        if (percentage >= 60) return "C";
        if (percentage >= 50) return "D";
        return "F";
    }

    // Get grade for individual subject
    public String getSubjectGrade(double marks) {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }
}
