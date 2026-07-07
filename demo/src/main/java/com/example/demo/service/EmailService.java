package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendEnrollmentMail(String toEmail, String studentName, String courseName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Course Enrollment Confirmation");
            message.setText(
                "Hi " + studentName + ",\n\n" +
                "You have successfully enrolled in: " + courseName + "\n\n" +
                "Thank you for registering with us.\n\n" +
                "Regards,\nAdmin Team"
            );
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println("Mail sending failed: " + e.getMessage());
            return false;
        }
    }
}