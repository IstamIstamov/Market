package com.example.simpleProject.controller;

import com.example.simpleProject.dto.CategoryDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/create")
    public ResponseDto<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto){
        return categoryService.create(categoryDto);
    }

    @GetMapping("/get")
    public ResponseDto<CategoryDto> get(@RequestParam Integer categoryId){
        return categoryService.get(categoryId);
    }

    @PutMapping("/update")
    public ResponseDto<CategoryDto> update(@RequestBody CategoryDto categoryDto, @RequestParam Integer categoryId){
        return categoryService.update(categoryDto, categoryId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<CategoryDto> delete(@RequestParam Integer categoryId){
        return categoryService.delete(categoryId);
    }

}
