package com.example.demo.service;

import com.example.demo.entity.PasswordResetRequest;
import com.example.demo.repository.PasswordResetRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordResetRequestService {

    @Autowired
    private PasswordResetRequestRepository repository;

    public PasswordResetRequest create(String username) {
        PasswordResetRequest request = new PasswordResetRequest();
        request.setUsername(username);
        request.setStatus("PENDING");
        return repository.save(request);
    }

    public List<PasswordResetRequest> getPending() {
        return repository.findByStatus("PENDING");
    }

    public PasswordResetRequest getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void save(PasswordResetRequest request) {
        repository.save(request);
    }
}