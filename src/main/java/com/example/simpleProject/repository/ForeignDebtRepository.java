package com.example.simpleProject.repository;

import com.example.simpleProject.model.ForeignDebt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ForeignDebtRepository extends JpaRepository<ForeignDebt, Integer> {
    Optional<ForeignDebt> findByForeignIdAndDeleteAtIsNull(Integer foreignId);
}
