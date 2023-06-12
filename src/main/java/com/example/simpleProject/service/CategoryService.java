package com.example.simpleProject.service;

import com.example.simpleProject.dto.CategoryDto;
import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.Category;
import com.example.simpleProject.repository.CategoryRepository;
import com.example.simpleProject.service.mapper.CategoryMapper;
import com.example.simpleProject.service.validation.CategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final CategoryValidation categoryValidation;

    public ResponseDto<CategoryDto> create(CategoryDto categoryDto) {
        List<ErrorDto> errors = categoryValidation.validate(categoryDto);
        if (!errors.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(categoryDto)
                    .errors(errors)
                    .build();
        }
        try {
            Category category = categoryMapper.toEntity(categoryDto);
            category.setCreateAt(LocalDateTime.now());
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(categoryMapper.toDto(category))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("Category while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<CategoryDto> get(Integer categoryId) {
        try{
            Optional<Category> optional = categoryRepository.findByCategoryIdAndDeleteAtIsNull(categoryId);
            if (optional.isEmpty()){
                return ResponseDto.<CategoryDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Category is not found")
                        .build();
            }
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(categoryMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<CategoryDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<CategoryDto> update(CategoryDto categoryDto, Integer categoryId) {
        List<ErrorDto> errors = categoryValidation.validate(categoryDto);
        if (!errors.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(categoryDto)
                    .errors(errors)
                    .build();
        }
        Optional<Category> optional = categoryRepository.findByCategoryIdAndDeleteAtIsNull(categoryId);
        if (optional.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Card is not found")
                    .build();
        }
        try {
            Category category = optional.get();
            category.setUpdateAt(LocalDateTime.now());
            this.categoryMapper.updateCategoryFromDto(categoryDto, category);
            this.categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.categoryMapper.toDto(category))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("Category while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<CategoryDto> delete(Integer categoryId) {
        Optional<Category> optional = categoryRepository.findByCategoryIdAndDeleteAtIsNull(categoryId);
        if (optional.isEmpty()){
            return ResponseDto.<CategoryDto>builder()
                    .code(-1)
                    .message("Category is not found")
                    .build();
        }
        try {
            Category category = optional.get();
            category.setDeleteAt(LocalDateTime.now());
            categoryRepository.save(category);
            return ResponseDto.<CategoryDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(categoryMapper.toDto(category)).build();
        }catch (Exception e){
            return ResponseDto.<CategoryDto>builder()
                    .code(-3)
                    .message(String.format("Category while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
