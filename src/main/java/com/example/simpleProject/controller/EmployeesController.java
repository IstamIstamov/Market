package com.example.simpleProject.controller;

import com.example.simpleProject.dto.EmployeesDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.EmployeesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeesController {
    private final EmployeesService employeesService;
    @PostMapping("/create")
    public ResponseDto<EmployeesDto> create(@Valid @RequestBody EmployeesDto employeesDto){
        return employeesService.create(employeesDto);
    }

    @GetMapping("/get")
    public ResponseDto<EmployeesDto> get(@RequestParam Integer employeeId){
        return employeesService.get(employeeId);
    }

    @PutMapping("/update")
    public ResponseDto<EmployeesDto> update(@RequestBody EmployeesDto employeesDto, @RequestParam Integer employeeId){
        return employeesService.update(employeesDto, employeeId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<EmployeesDto> delete(@RequestParam Integer employeeId){
        return employeesService.delete(employeeId);
    }

}
