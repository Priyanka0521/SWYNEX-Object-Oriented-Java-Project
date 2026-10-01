package com.swynex.studentmanagement.controller;

import com.swynex.studentmanagement.model.Student;
import com.swynex.studentmanagement.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for admin functionality.
 * Handles student management operations for admin users.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final StudentService studentService;

    @Autowired
    public AdminController(StudentService studentService) {
        this.studentService = studentService;
    }

    // Check if user is admin
    private boolean isAdmin(HttpSession session) {
        String role = (String) session.getAttribute("role");
        return "ADMIN".equals(role);
    }

    // Admin dashboard
    @GetMapping("/dashboard")
    public String adminDashboard(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        // Add dashboard statistics
        model.addAttribute("totalStudents", studentService.getTotalStudentCount());
        model.addAttribute("averagePercentage", String.format("%.2f", studentService.getAveragePercentage()));
        model.addAttribute("passedStudents", studentService.getPassedStudentCount());
        model.addAttribute("failedStudents", studentService.getFailedStudentCount());
        model.addAttribute("students", studentService.getAllStudents());

        return "admin-dashboard";
    }

    // Show add student form
    @GetMapping("/student/add")
    public String showAddStudentForm(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        model.addAttribute("student", new Student());
        return "add-student";
    }

    // Process add student
    @PostMapping("/student/add")
    public String addStudent(@Valid @ModelAttribute Student student,
                           BindingResult bindingResult,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "add-student";
        }

        try {
            studentService.createStudent(student);
            redirectAttributes.addFlashAttribute("success", "Student added successfully!");
            return "redirect:/admin/dashboard";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/student/add";
        }
    }

    // View student details
    @GetMapping("/student/view/{id}")
    public String viewStudent(@PathVariable Long id,
                             HttpSession session,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        try {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "view-student";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }

    // Show edit student form
    @GetMapping("/student/edit/{id}")
    public String showEditStudentForm(@PathVariable Long id,
                                     HttpSession session,
                                     Model model,
                                     RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        try {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
            return "edit-student";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }

    // Process edit student
    @PostMapping("/student/edit/{id}")
    public String editStudent(@PathVariable Long id,
                             @Valid @ModelAttribute Student student,
                             BindingResult bindingResult,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            return "edit-student";
        }

        try {
            studentService.updateStudent(id, student);
            redirectAttributes.addFlashAttribute("success", "Student updated successfully!");
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/student/edit/" + id;
        }
    }

    // Delete student
    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable Long id,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        try {
            studentService.deleteStudent(id);
            redirectAttributes.addFlashAttribute("success", "Student deleted successfully!");
            return "redirect:/admin/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }

    // Search students
    @GetMapping("/student/search")
    public String searchStudents(@RequestParam(required = false) String query,
                                 @RequestParam(required = false) String searchType,
                                 HttpSession session,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {
        if (!isAdmin(session)) {
            redirectAttributes.addFlashAttribute("error", "Access denied. Admin only.");
            return "redirect:/login";
        }

        if (query == null || query.isEmpty()) {
            return "redirect:/admin/dashboard";
        }

        try {
            if ("name".equals(searchType)) {
                model.addAttribute("students", studentService.searchByName(query));
            } else if ("course".equals(searchType)) {
                model.addAttribute("students", studentService.searchByCourse(query));
            } else {
                model.addAttribute("students", studentService.searchByName(query));
            }

            model.addAttribute("searchQuery", query);
            model.addAttribute("searchType", searchType);
            model.addAttribute("totalStudents", studentService.getTotalStudentCount());
            model.addAttribute("averagePercentage", String.format("%.2f", studentService.getAveragePercentage()));
            model.addAttribute("passedStudents", studentService.getPassedStudentCount());
            model.addAttribute("failedStudents", studentService.getFailedStudentCount());

            return "admin-dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/admin/dashboard";
        }
    }
}
