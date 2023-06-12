package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ReportsDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ReportsValidation {
    public List<ErrorDto> validate(ReportsDto reportsDto) {
        List<ErrorDto> errors = new ArrayList<>();
        return errors;
    }
}
