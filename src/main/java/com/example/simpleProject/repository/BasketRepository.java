package com.example.simpleProject.repository;

import com.example.simpleProject.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    Optional<Basket> findByBasketIdAndDeleteAtIsNull(Integer basketId);
}
