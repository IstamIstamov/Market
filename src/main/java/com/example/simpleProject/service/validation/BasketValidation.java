package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.BasketDto;
import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.service.LoanerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BasketValidation {
    private final LoanerService loanerService;
    public List<ErrorDto> validate(BasketDto basketDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (loanerService.get(basketDto.getLoanerId()).getData() == null){
            errors.add(new ErrorDto("loanerId", "Loaner is not found"));
        }
        return errors;
    }
}
