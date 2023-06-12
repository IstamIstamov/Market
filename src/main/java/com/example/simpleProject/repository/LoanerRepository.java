package com.example.simpleProject.repository;

import com.example.simpleProject.model.Loaner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanerRepository extends JpaRepository<Loaner, Integer> {
    Optional<Loaner> findByLoanerIdAndDeleteAtIsNull(Integer loanerId);
}
