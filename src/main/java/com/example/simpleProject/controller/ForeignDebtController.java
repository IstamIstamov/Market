package com.example.simpleProject.controller;

import com.example.simpleProject.dto.ForeignDebtDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.ForeignDebtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("foreign")
@RequiredArgsConstructor
public class ForeignDebtController {
    private final ForeignDebtService foreignDebtService;
    @PostMapping("/create")
    public ResponseDto<ForeignDebtDto> create(@Valid @RequestBody ForeignDebtDto foreignDebtDto){
        return foreignDebtService.create(foreignDebtDto);
    }

    @GetMapping("/get")
    public ResponseDto<ForeignDebtDto> get(@RequestParam Integer foreignId){
        return foreignDebtService.get(foreignId);
    }

    @PutMapping("/update")
    public ResponseDto<ForeignDebtDto> update(@RequestBody ForeignDebtDto foreignDebtDto, @RequestParam Integer foreignId){
        return foreignDebtService.update(foreignDebtDto, foreignId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<ForeignDebtDto> delete(@RequestParam Integer foreignId){
        return foreignDebtService.delete(foreignId);
    }

}
