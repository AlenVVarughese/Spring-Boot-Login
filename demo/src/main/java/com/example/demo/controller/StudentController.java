package com.example.demo.controller;

import com.example.demo.entity.CourseRegistration;
import com.example.demo.service.CourseRegistrationService;
import com.example.demo.service.CourseService;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private CourseRegistrationService registrationService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/course-form")
    public String showForm(Model model, Authentication auth) {
        String username = auth.getName();
        CourseRegistration existing = registrationService.getByOwner(username);

        if (existing != null) {
            return "redirect:/student/my-registration";
        }

        model.addAttribute("registration", new CourseRegistration());
        model.addAttribute("courses", courseService.getAll());
        return "course-form";
    }

    @PostMapping("/course-form")
    public String submitForm(@ModelAttribute CourseRegistration registration,
                              Authentication auth, Model model) {
        String username = auth.getName();

        if (registrationService.hasRegistered(username)) {
            model.addAttribute("error", "You have already submitted your registration.");
            model.addAttribute("courses", courseService.getAll());
            return "course-form";
        }

        registration.setOwnerUsername(username);
        registration.setMailSent(false);
        CourseRegistration saved = registrationService.save(registration);

        boolean sent = emailService.sendEnrollmentMail(
                saved.getEmail(), saved.getStudentName(), saved.getCourse());
        saved.setMailSent(sent);
        registrationService.save(saved);

        return "redirect:/student/my-registration";
    }

    @GetMapping("/my-registration")
    public String myRegistration(Model model, Authentication auth) {
        CourseRegistration reg = registrationService.getByOwner(auth.getName());
        model.addAttribute("registration", reg);
        return "my-registration";
    }
}