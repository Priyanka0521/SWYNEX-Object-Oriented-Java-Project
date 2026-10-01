package com.swynex.studentmanagement.repository;

import com.swynex.studentmanagement.model.GraduateStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for GraduateStudent entity.
 */
@Repository
public interface GraduateStudentRepository extends JpaRepository<GraduateStudent, Long> {

    // Find graduate student by student ID
    Optional<GraduateStudent> findByStudentId(String studentId);

    // Find graduate student by username
    Optional<GraduateStudent> findByUsername(String username);

    // Search by specialization
    List<GraduateStudent> findBySpecialization(String specialization);
}
