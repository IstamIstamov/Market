package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ProductBaseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductBaseValidation {
    public List<ErrorDto> validate(ProductBaseDto productBaseDto) {
        List<ErrorDto> errors = new ArrayList<>();
        return errors;
    }
}
