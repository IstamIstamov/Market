package com.example.simpleProject.repository;

import com.example.simpleProject.model.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeesRepository extends JpaRepository<Employees, Integer> {
    Optional<Employees> findByEmployeeIdAndDeleteAtIsNull(Integer employeesId);
}
