package com.example.simpleProject.service;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ForeignDebtDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.ForeignDebt;
import com.example.simpleProject.repository.ForeignDebtRepository;
import com.example.simpleProject.service.mapper.ForeignDebtMapper;
import com.example.simpleProject.service.validation.ForeignDebtValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForeignDebtService {
    private final ForeignDebtRepository foreignDebtRepository;
    private final ForeignDebtMapper foreignDebtMapper;
    private final ForeignDebtValidation foreignDebtValidation;
    public ResponseDto<ForeignDebtDto> create(ForeignDebtDto foreignDebtDto) {
        List<ErrorDto> errors = foreignDebtValidation.validate(foreignDebtDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(foreignDebtDto)
                    .errors(errors)
                    .build();
        }
        try {
            ForeignDebt foreignDebt = foreignDebtMapper.toEntity(foreignDebtDto);
            foreignDebt.setCreateAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(foreignDebtMapper.toDto(foreignDebt))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ForeignDebtDto>builder()
                    .code(-3)
                    .message(String.format("Foreign debt while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ForeignDebtDto> get(Integer foreignId) {
        try{
            Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignIdAndDeleteAtIsNull(foreignId);
            if (optional.isEmpty()){
                return ResponseDto.<ForeignDebtDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Foreign debt is not found")
                        .build();
            }
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(foreignDebtMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ForeignDebtDto> update(ForeignDebtDto foreignDebtDto, Integer foreignId) {
        List<ErrorDto> errors = foreignDebtValidation.validate(foreignDebtDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ForeignDebtDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(foreignDebtDto)
                    .errors(errors)
                    .build();
        }
        Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignIdAndDeleteAtIsNull(foreignId);
        if (optional.isEmpty()){
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Foreign debt is not found")
                    .build();
        }
        try {
            ForeignDebt foreignDebt = optional.get();
            foreignDebt.setUpdateAt(LocalDateTime.now());
            this.foreignDebtMapper.updateForeignFromDto(foreignDebtDto, foreignDebt);
            this.foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.foreignDebtMapper.toDto(foreignDebt))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ForeignDebtDto>builder()
                    .code(-3)
                    .message(String.format("Foreign debt while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ForeignDebtDto> delete(Integer foreignId) {
        Optional<ForeignDebt> optional = foreignDebtRepository.findByForeignIdAndDeleteAtIsNull(foreignId);
        if (optional.isEmpty()){
            return ResponseDto.<ForeignDebtDto>builder()
                    .code(-1)
                    .message("Foreign debt is not found")
                    .build();
        }
        try {
            ForeignDebt foreignDebt = optional.get();
            foreignDebt.setDeleteAt(LocalDateTime.now());
            foreignDebtRepository.save(foreignDebt);
            return ResponseDto.<ForeignDebtDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(foreignDebtMapper.toDto(foreignDebt)).build();
        }catch (Exception e){
            return ResponseDto.<ForeignDebtDto>builder()
                    .code(-3)
                    .message(String.format("Foreign debt while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
