package com.example.simpleProject.service;

import com.example.simpleProject.dto.EmployeesDto;
import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.Employees;
import com.example.simpleProject.repository.EmployeesRepository;
import com.example.simpleProject.service.mapper.EmployeesMapper;
import com.example.simpleProject.service.validation.EmployeesValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeesService {
    private final EmployeesValidation employeesValidation;
    private final EmployeesMapper employeesMapper;
    private final EmployeesRepository employeesRepository;
    public ResponseDto<EmployeesDto> create(EmployeesDto employeesDto) {
        List<ErrorDto> errors = employeesValidation.validate(employeesDto);
        if (!errors.isEmpty()){
            return ResponseDto.<EmployeesDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(employeesDto)
                    .errors(errors)
                    .build();
        }
        try {
            Employees employees = employeesMapper.toEntity(employeesDto);
            employees.setCreateAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(employeesMapper.toDto(employees))
                    .build();
        }catch (Exception e){
            return ResponseDto.<EmployeesDto>builder()
                    .code(-3)
                    .message(String.format("Employee while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> get(Integer employeeId) {
        try{
            Optional<Employees> optional = employeesRepository.findByEmployeeIdAndDeleteAtIsNull(employeeId);
            if (optional.isEmpty()){
                return ResponseDto.<EmployeesDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Employee is not found")
                        .build();
            }
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(employeesMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<EmployeesDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> update(EmployeesDto employeesDto, Integer employeeId) {
        List<ErrorDto> errors = employeesValidation.validate(employeesDto);
        if (!errors.isEmpty()){
            return ResponseDto.<EmployeesDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(employeesDto)
                    .errors(errors)
                    .build();
        }
        Optional<Employees> optional = employeesRepository.findByEmployeeIdAndDeleteAtIsNull(employeeId);
        if (optional.isEmpty()){
            return ResponseDto.<EmployeesDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Employee is not found")
                    .build();
        }
        try {
            Employees employees = employeesMapper.toEntity(employeesDto);
            employees.setUpdateAt(LocalDateTime.now());
            this.employeesMapper.updateEmployeesFromDto(employeesDto, employees);
            this.employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.employeesMapper.toDto(employees))
                    .build();
        }catch (Exception e){
            return ResponseDto.<EmployeesDto>builder()
                    .code(-3)
                    .message(String.format("Employee while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<EmployeesDto> delete(Integer employeeId) {
        Optional<Employees> optional = employeesRepository.findByEmployeeIdAndDeleteAtIsNull(employeeId);
        if (optional.isEmpty()){
            return ResponseDto.<EmployeesDto>builder()
                    .code(-1)
                    .message("Employee is not found")
                    .build();
        }
        try {
            Employees employees = optional.get();
            employees.setDeleteAt(LocalDateTime.now());
            employeesRepository.save(employees);
            return ResponseDto.<EmployeesDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(employeesMapper.toDto(employees)).build();
        }catch (Exception e){
            return ResponseDto.<EmployeesDto>builder()
                    .code(-3)
                    .message(String.format("Employee while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
