package com.example.simpleProject.controller;

import com.example.simpleProject.dto.LoanerDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.LoanerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("loaner")
@RequiredArgsConstructor
public class LoanerController {
    private final LoanerService loanerService;
    @PostMapping("/create")
    public ResponseDto<LoanerDto> create(@Valid @RequestBody LoanerDto loanerDto){
        return loanerService.create(loanerDto);
    }

    @GetMapping("/get")
    public ResponseDto<LoanerDto> get(@RequestParam Integer loanerId){
        return loanerService.get(loanerId);
    }

    @PutMapping("/update")
    public ResponseDto<LoanerDto> update(@RequestBody LoanerDto loanerDto, @RequestParam Integer loanerId){
        return loanerService.update(loanerDto, loanerId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<LoanerDto> delete(@RequestParam Integer loanerId){
        return loanerService.delete(loanerId);
    }

}
