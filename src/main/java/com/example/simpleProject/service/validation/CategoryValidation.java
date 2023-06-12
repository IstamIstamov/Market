package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.CategoryDto;
import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.service.ReportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryValidation {
    private final ReportsService reportsService;
    public List<ErrorDto> validate(CategoryDto categoryDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (reportsService.get(categoryDto.getReportsId()).getData() == null){
            errors.add(new ErrorDto("reportsId", "Reports is not found"));
        }
        return errors;
    }
}
