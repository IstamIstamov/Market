package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ForeignDebtDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ForeignDebtValidation {
    public List<ErrorDto> validate(ForeignDebtDto foreignDebtDto) {
        List<ErrorDto> errors = new ArrayList<>();
        return errors;
    }
}
