package com.swynex.studentmanagement.config;

import com.swynex.studentmanagement.model.Marks;
import com.swynex.studentmanagement.model.Student;
import com.swynex.studentmanagement.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Data initializer to create default student account on application startup.
 * Disabled to prevent startup issues. Students can be created manually via admin interface.
 */
//@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;

    public DataInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create default student if it doesn't exist
        if (!studentRepository.existsByUsername("student101")) {
            Student defaultStudent = new Student();
            defaultStudent.setStudentId("STU101");
            defaultStudent.setName("Default Student");
            defaultStudent.setUsername("student101");
            defaultStudent.setPassword("student123");
            defaultStudent.setEmail("student101@example.com");
            defaultStudent.setCourse("MSc Computer Science");
            defaultStudent.setYear(1);

            // Set default marks
            Marks marks = new Marks(75, 80, 85, 90);
            defaultStudent.setMarks(marks);

            studentRepository.save(defaultStudent);
            System.out.println("Default student account created: student101 / student123");
        }
    }
}
