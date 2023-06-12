package com.example.simpleProject.service;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ReportsDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.Reports;
import com.example.simpleProject.repository.ReportsRepository;
import com.example.simpleProject.service.mapper.ReportsMapper;
import com.example.simpleProject.service.validation.ReportsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportsService {
    private final ReportsRepository reportsRepository;
    private final ReportsMapper reportsMapper;
    private final ReportsValidation reportsValidation;
    public ResponseDto<ReportsDto> create(ReportsDto reportsDto) {
        List<ErrorDto> errors = reportsValidation.validate(reportsDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ReportsDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(reportsDto)
                    .errors(errors)
                    .build();
        }
        try {
            Reports reports = reportsMapper.toEntity(reportsDto);
            reports.setCreateAt(LocalDateTime.now());
            reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(reportsMapper.toDto(reports))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ReportsDto>builder()
                    .code(-3)
                    .message(String.format("Reports while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ReportsDto> get(Integer reportsId) {
        try{
            Optional<Reports> optional = reportsRepository.findByReportsIdAndDeleteAtIsNull(reportsId);
            if (optional.isEmpty()){
                return ResponseDto.<ReportsDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Reports is not found")
                        .build();
            }
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(reportsMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<ReportsDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ReportsDto> update(ReportsDto reportsDto, Integer reportsId) {
        List<ErrorDto> errors = reportsValidation.validate(reportsDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ReportsDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(reportsDto)
                    .errors(errors)
                    .build();
        }
        Optional<Reports> optional = reportsRepository.findByReportsIdAndDeleteAtIsNull(reportsId);
        if (optional.isEmpty()){
            return ResponseDto.<ReportsDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Reports is not found")
                    .build();
        }
        try {
            Reports reports = optional.get();
            reports.setUpdateAt(LocalDateTime.now());
            this.reportsMapper.updateReportsFromDto(reportsDto, reports);
            this.reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.reportsMapper.toDto(reports))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ReportsDto>builder()
                    .code(-3)
                    .message(String.format("Reports while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ReportsDto> delete(Integer reportsId) {
        Optional<Reports> optional = reportsRepository.findByReportsIdAndDeleteAtIsNull(reportsId);
        if (optional.isEmpty()){
            return ResponseDto.<ReportsDto>builder()
                    .code(-1)
                    .message("Reports is not found")
                    .build();
        }
        try {
            Reports reports = optional.get();
            reports.setDeleteAt(LocalDateTime.now());
            reportsRepository.save(reports);
            return ResponseDto.<ReportsDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(reportsMapper.toDto(reports)).build();
        }catch (Exception e){
            return ResponseDto.<ReportsDto>builder()
                    .code(-3)
                    .message(String.format("Reports while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
