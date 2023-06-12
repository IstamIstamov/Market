package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.LoanerDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class LoanerValidation {
    public List<ErrorDto> validate(LoanerDto loanerDto) {
        List<ErrorDto> errors = new ArrayList<>();
        return errors;
    }
}
