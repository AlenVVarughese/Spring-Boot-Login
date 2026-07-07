package com.example.demo.controller;

import com.example.demo.entity.Course;
import com.example.demo.entity.CourseRegistration;
import com.example.demo.entity.PasswordResetRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CourseRegistrationService;
import com.example.demo.service.CourseService;
import com.example.demo.service.PasswordResetRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CourseRegistrationService registrationService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PasswordResetRequestService resetService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ---------- Registrations CRUD ----------
    @GetMapping("/registrations")
    public String listRegistrations(Model model) {
        model.addAttribute("registrations", registrationService.getAll());
        return "admin-registrations";
    }

    @GetMapping("/registrations/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("registration", registrationService.getById(id));
        return "admin-edit-registration";
    }

    @PostMapping("/registrations/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute CourseRegistration updated) {
        CourseRegistration existing = registrationService.getById(id);
        existing.setStudentName(updated.getStudentName());
        existing.setEmail(updated.getEmail());
        existing.setPhone(updated.getPhone());
        existing.setCourse(updated.getCourse());
        existing.setAddress(updated.getAddress());
        registrationService.save(existing);
        return "redirect:/admin/registrations";
    }

    @GetMapping("/registrations/delete/{id}")
    public String delete(@PathVariable Long id) {
        registrationService.deleteById(id);
        return "redirect:/admin/registrations";
    }

    // ---------- Course management ----------
    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseService.getAll());
        model.addAttribute("course", new Course());
        return "admin-courses";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute Course course) {
        courseService.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/admin/courses";
    }

    // ---------- Password reset requests ----------
    @GetMapping("/reset-requests")
    public String listResetRequests(Model model) {
        model.addAttribute("requests", resetService.getPending());
        return "admin-reset-requests";
    }

    @PostMapping("/reset-requests/resolve/{id}")
    public String resolveRequest(@PathVariable Long id, @RequestParam String newPassword) {
        PasswordResetRequest request = resetService.getById(id);
        if (request != null) {
            User user = userRepository.findByUsername(request.getUsername()).orElse(null);
            if (user != null) {
                user.setPassword(passwordEncoder.encode(newPassword));
                userRepository.save(user);
            }
            request.setStatus("RESOLVED");
            resetService.save(request);
        }
        return "redirect:/admin/reset-requests";
    }

    // ---------- Dashboard / charts ----------
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<CourseRegistration> all = registrationService.getAll();
        Map<String, Long> courseCounts = new HashMap<>();
        for (CourseRegistration r : all) {
            courseCounts.merge(r.getCourse(), 1L, Long::sum);
        }
        model.addAttribute("courseCounts", courseCounts);
        model.addAttribute("totalStudents", all.size());
        model.addAttribute("totalCourses", courseService.getAll().size());
        return "admin-dashboard";
    }
}