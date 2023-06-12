package com.example.simpleProject.repository;

import com.example.simpleProject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategoryIdAndDeleteAtIsNull(Integer categoryId);
}
