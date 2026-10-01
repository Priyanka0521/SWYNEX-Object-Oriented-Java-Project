package com.swynex.studentmanagement.repository;

import com.swynex.studentmanagement.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Student entity.
 * Provides CRUD operations and custom queries.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find student by student ID
    Optional<Student> findByStudentId(String studentId);

    // Find student by username
    Optional<Student> findByUsername(String username);

    // Check if username exists
    boolean existsByUsername(String username);

    // Check if student ID exists
    boolean existsByStudentId(String studentId);

    // Search students by name (case-insensitive)
    List<Student> findByNameContainingIgnoreCase(String name);

    // Search students by course
    List<Student> findByCourse(String course);

    // Count total students
    long count();
}
