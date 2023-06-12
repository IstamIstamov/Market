package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.UserDto;
import com.example.simpleProject.service.EmployeesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private final EmployeesService employeesService;
    public List<ErrorDto> validate(UserDto userDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (employeesService.get(userDto.getEmployeeId()).getData() == null){
            errors.add(new ErrorDto("employeesId", "Employee is not found"));
        }
        return errors;
    }
}
