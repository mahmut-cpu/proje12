package com.example.iste.controller;

import com.example.iste.entity.User;
import com.example.iste.repository.UserRepository;
import com.example.iste.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    // Public page (no login required)
    @GetMapping("/public")
    public String publicPage() {
        return "public";
    }

    // Admin login page
    @GetMapping("/login-admin")
    public String adminPage() {
        return "login-admin";
    }

    // User login page
    @GetMapping("/login-user")
    public String loginUserPage() {
        return "login-user";
    }

    // Admin login process
    @PostMapping("/login-admin")
    public String loginAdmin(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             Model model) {
        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Kullanıcı adı veya şifre boş olamaz.");
            return "login-admin"; // Error handling for empty inputs
        }
        User admin = (User) userService.loadUserByUsername(email);

        if (admin == null || !passwordEncoder.matches(password, admin.getPassword())) {
            model.addAttribute("error", "Yanlış kullanıcı adı veya şifre.");
            return "login-admin"; // Incorrect login
        }

        // Successful login
        return "redirect:/admin-dashboard"; // Redirect to admin dashboard
    }

    // User login process
    @PostMapping("/login-user")
    public String loginUser(@RequestParam("email") String email,
                            @RequestParam("password") String password,
                            Model model) {
        if (email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "E-posta veya şifre boş olamaz.");
            return "login-user"; // Error handling for empty inputs
        }

        User user = (User) userService.loadUserByUsername(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            model.addAttribute("error", "Yanlış e-posta veya şifre.");
            return "login-user"; // Incorrect login
        }

        // Successful login
        return "redirect:/user-dashboard"; // Redirect to user dashboard
    }

    @Secured("ADMIN")
    @GetMapping("/admin-dashboard")
    public String adminDashboard() {
        return "admin-dashboard";
    }

    @Secured("USER")
    @GetMapping("/user-dashboard")
    public String userDashboard() {
        return "user-dashboard";
    }
}
