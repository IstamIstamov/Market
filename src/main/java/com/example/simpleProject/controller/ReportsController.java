package com.example.simpleProject.controller;

import com.example.simpleProject.dto.ReportsDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.ReportsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("report")
@RequiredArgsConstructor
public class ReportsController {
    private final ReportsService reportsService;
    @PostMapping("/create")
    public ResponseDto<ReportsDto> create(@Valid @RequestBody ReportsDto reportsDto){
        return reportsService.create(reportsDto);
    }
    @GetMapping("/get")
    public ResponseDto<ReportsDto> get(@RequestParam Integer reportsId){
        return reportsService.get(reportsId);
    }

    @PutMapping("/update")
    public ResponseDto<ReportsDto> update(@RequestBody ReportsDto reportsDto, @RequestParam Integer reportsId){
        return reportsService.update(reportsDto, reportsId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<ReportsDto> delete(@RequestParam Integer reportsId){
        return reportsService.delete(reportsId);
    }

}
