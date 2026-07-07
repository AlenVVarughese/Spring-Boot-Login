package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "course_registrations")
public class CourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String email;
    private String phone;
    private String course;
    private String address;

    @Column(name = "owner_username")
    private String ownerUsername; // links registration to the logged-in student

    private boolean mailSent = false;

    public CourseRegistration() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getOwnerUsername() { return ownerUsername; }
    public void setOwnerUsername(String ownerUsername) { this.ownerUsername = ownerUsername; }

    public boolean isMailSent() { return mailSent; }
    public void setMailSent(boolean mailSent) { this.mailSent = mailSent; }
}