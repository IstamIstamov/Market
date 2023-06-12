package com.example.simpleProject.repository;

import com.example.simpleProject.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findByImageIdAndDeleteAtIsNull(Integer imageId);
}
