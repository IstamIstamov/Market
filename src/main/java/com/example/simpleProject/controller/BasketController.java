package com.example.simpleProject.controller;

import com.example.simpleProject.dto.BasketDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.BasketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("basket")
@RequiredArgsConstructor
public class BasketController {
    private final BasketService basketService;
    @PostMapping("/create")
    public ResponseDto<BasketDto> create(@Valid @RequestBody BasketDto basketDto){
        return basketService.create(basketDto);
    }

    @GetMapping("/get")
    public ResponseDto<BasketDto> get(@RequestParam Integer basketId){
        return basketService.get(basketId);
    }

    @PutMapping("/update")
    public ResponseDto<BasketDto> update(@RequestBody BasketDto basketDto, @RequestParam Integer basketId){
        return basketService.update(basketDto, basketId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<BasketDto> delete(@RequestParam Integer basketId){
        return basketService.delete(basketId);
    }
}
