package com.example.demo.controller;

import com.example.demo.service.PasswordResetRequestService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForgotPasswordController {

    @Autowired
    private PasswordResetRequestService resetService;

    @Autowired
    private UserService userService;

    @GetMapping("/forgot-password")
    public String showForm() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String submitRequest(@RequestParam String username, Model model) {
        if (!userService.usernameExists(username)) {
            model.addAttribute("error", "No account found with that username.");
            return "forgot-password";
        }
        resetService.create(username);
        model.addAttribute("success", "Request sent to admin. You'll be notified once your password is reset.");
        return "forgot-password";
    }
}