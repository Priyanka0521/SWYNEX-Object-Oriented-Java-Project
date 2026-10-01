package com.swynex.studentmanagement.controller;

import com.swynex.studentmanagement.model.Student;
import com.swynex.studentmanagement.model.User;
import com.swynex.studentmanagement.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for login and logout functionality.
 */
@Controller
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Display login page
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Test endpoint
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Application is working!";
    }

    // Process login
    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              HttpSession session,
                              RedirectAttributes redirectAttributes) {
        try {
            User user = loginService.authenticate(username, password);

            if (user == null) {
                redirectAttributes.addFlashAttribute("error", "Invalid username or password");
                return "redirect:/login";
            }

            // Store user in session
            session.setAttribute("user", user);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("role", user.getRole());

            // Redirect based on role
            if (user.isAdmin()) {
                return "redirect:/admin/dashboard";
            } else if (user.isStudent()) {
                return "redirect:/student/dashboard";
            }

            redirectAttributes.addFlashAttribute("error", "Invalid role");
            return "redirect:/login";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Login failed: " + e.getMessage());
            return "redirect:/login";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message", "You have been logged out successfully");
        return "redirect:/login";
    }
}
