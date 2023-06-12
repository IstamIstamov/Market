package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.EmployeesDto;
import com.example.simpleProject.dto.ErrorDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class EmployeesValidation {
    public List<ErrorDto> validate(EmployeesDto employeesDto) {
        List<ErrorDto> errors = new ArrayList<>();
        return errors;
    }
}
