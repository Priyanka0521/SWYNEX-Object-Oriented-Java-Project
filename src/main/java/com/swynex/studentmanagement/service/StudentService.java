package com.swynex.studentmanagement.service;

import com.swynex.studentmanagement.exception.ResourceNotFoundException;
import com.swynex.studentmanagement.model.Student;
import com.swynex.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for Student business logic.
 * Demonstrates separation of concerns and business rule implementation.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by ID
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    // Get student by student ID
    public Student getStudentByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with student ID: " + studentId));
    }

    // Get student by username
    public Student getStudentByUsername(String username) {
        return studentRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with username: " + username));
    }

    // Create new student
    public Student createStudent(Student student) {
        // Check if username already exists
        if (studentRepository.existsByUsername(student.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + student.getUsername());
        }
        // Check if student ID already exists
        if (studentRepository.existsByStudentId(student.getStudentId())) {
            throw new IllegalArgumentException("Student ID already exists: " + student.getStudentId());
        }
        return studentRepository.save(student);
    }

    // Update student
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);

        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        student.setYear(studentDetails.getYear());
        
        // Update username if changed and not already taken
        if (!student.getUsername().equals(studentDetails.getUsername())) {
            if (studentRepository.existsByUsername(studentDetails.getUsername())) {
                throw new IllegalArgumentException("Username already exists: " + studentDetails.getUsername());
            }
            student.setUsername(studentDetails.getUsername());
        }

        // Update password if provided
        if (studentDetails.getPassword() != null && !studentDetails.getPassword().isEmpty()) {
            student.setPassword(studentDetails.getPassword());
        }

        // Update marks if provided
        if (studentDetails.getMarks() != null) {
            student.setMarks(studentDetails.getMarks());
        }

        return studentRepository.save(student);
    }

    // Delete student
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }

    // Search students by name
    public List<Student> searchByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name);
    }

    // Search students by course
    public List<Student> searchByCourse(String course) {
        return studentRepository.findByCourse(course);
    }

    // Get total student count
    public long getTotalStudentCount() {
        return studentRepository.count();
    }

    // Calculate average percentage across all students
    public double getAveragePercentage() {
        List<Student> students = getAllStudents();
        if (students.isEmpty()) {
            return 0.0;
        }
        return students.stream()
                .filter(s -> s.getMarks() != null)
                .mapToDouble(s -> s.getMarks().getPercentage())
                .average()
                .orElse(0.0);
    }

    // Get count of passed students (percentage >= 50)
    public long getPassedStudentCount() {
        return getAllStudents().stream()
                .filter(s -> s.getMarks() != null)
                .filter(s -> s.getMarks().getPercentage() >= 50)
                .count();
    }

    // Get count of failed students (percentage < 50)
    public long getFailedStudentCount() {
        return getAllStudents().stream()
                .filter(s -> s.getMarks() != null)
                .filter(s -> s.getMarks().getPercentage() < 50)
                .count();
    }
}
