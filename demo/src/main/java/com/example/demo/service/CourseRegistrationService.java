package com.example.demo.service;

import com.example.demo.entity.CourseRegistration;
import com.example.demo.repository.CourseRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseRegistrationService {

    @Autowired
    private CourseRegistrationRepository repository;

    public List<CourseRegistration> getAll() {
        return repository.findAll();
    }

    public CourseRegistration getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CourseRegistration getByOwner(String username) {
        return repository.findByOwnerUsername(username).orElse(null);
    }

    public boolean hasRegistered(String username) {
        return repository.existsByOwnerUsername(username);
    }

    public CourseRegistration save(CourseRegistration registration) {
        return repository.save(registration);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}