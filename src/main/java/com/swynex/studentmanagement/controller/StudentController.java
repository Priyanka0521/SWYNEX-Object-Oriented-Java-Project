package com.swynex.studentmanagement.controller;

import com.swynex.studentmanagement.model.Student;
import com.swynex.studentmanagement.service.LoginService;
import com.swynex.studentmanagement.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for student functionality.
 * Handles student dashboard and marks viewing.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;
    private final LoginService loginService;

    @Autowired
    public StudentController(StudentService studentService, LoginService loginService) {
        this.studentService = studentService;
        this.loginService = loginService;
    }

    // Check if user is student
    private boolean isStudent(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "STUDENT".equals(role);
    }

    // Student dashboard
    @GetMapping("/dashboard")
    public String studentDashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (!isStudent(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Student only.");
            return "redirect:/login";
        }

        String username = (String) session.getAttribute("username");

        try {
            Student student = loginService.getStudentByUsername(username);
            if (student == null) {
                redirectAttributes.addFlashAttribute("error", "Student not found");
                return "redirect:/login";
            }

            model.addAttribute("student", student);
            return "student-dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }
}
