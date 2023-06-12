package com.example.simpleProject.repository;

import com.example.simpleProject.model.Reports;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReportsRepository extends JpaRepository<Reports, Integer> {
    Optional<Reports> findByReportsIdAndDeleteAtIsNull(Integer reportsId);
}
