package com.example.demo.repository;

import com.example.demo.entity.CourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    Optional<CourseRegistration> findByOwnerUsername(String ownerUsername);
    boolean existsByOwnerUsername(String ownerUsername);
}