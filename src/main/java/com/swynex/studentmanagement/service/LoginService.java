package com.swynex.studentmanagement.service;

import com.swynex.studentmanagement.model.Student;
import com.swynex.studentmanagement.model.User;
import com.swynex.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for authentication and login logic.
 */
@Service
public class LoginService {

    private final StudentRepository studentRepository;

    // Default admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @Autowired
    public LoginService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Authenticate user
    public User authenticate(String username, String password) {
        // Check for admin login
        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            return new User(username, password, "ADMIN");
        }

        // Check for student login
        Student student = studentRepository.findByUsername(username)
                .orElse(null);

        if (student != null && student.getPassword().equals(password)) {
            return new User(username, password, "STUDENT");
        }

        return null;
    }

    // Get student by username
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username)
                .orElse(null);
    }
}
