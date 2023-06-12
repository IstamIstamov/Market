package com.example.simpleProject.repository;

import com.example.simpleProject.model.ProductBase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductBaseRepository extends JpaRepository<ProductBase, Integer> {
    Optional<ProductBase> findByProductBaseId(Integer productBaseId);
}
