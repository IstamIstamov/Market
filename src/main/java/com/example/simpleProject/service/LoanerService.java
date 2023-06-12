package com.example.simpleProject.service;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.LoanerDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.Loaner;
import com.example.simpleProject.repository.LoanerRepository;
import com.example.simpleProject.service.mapper.LoanerMapper;
import com.example.simpleProject.service.validation.LoanerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanerService {
    private final LoanerRepository loanerRepository;
    private final LoanerMapper loanerMapper;
    private final LoanerValidation loanerValidation;
    public ResponseDto<LoanerDto> create(LoanerDto loanerDto) {
        List<ErrorDto> errors = loanerValidation.validate(loanerDto);
        if (!errors.isEmpty()){
            return ResponseDto.<LoanerDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(loanerDto)
                    .errors(errors)
                    .build();
        }
        try {
            Loaner loaner = loanerMapper.toEntity(loanerDto);
            loaner.setCreateAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(loanerMapper.toDto(loaner))
                    .build();
        }catch (Exception e){
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message(String.format("Loaner while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<LoanerDto> get(Integer loanerId) {
        try{
            Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeleteAtIsNull(loanerId);
            if (optional.isEmpty()){
                return ResponseDto.<LoanerDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Loaner is not found")
                        .build();
            }
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(loanerMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<LoanerDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<LoanerDto> update(LoanerDto loanerDto, Integer loanerId) {
        List<ErrorDto> errors = loanerValidation.validate(loanerDto);
        if (!errors.isEmpty()){
            return ResponseDto.<LoanerDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(loanerDto)
                    .errors(errors)
                    .build();
        }
        Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeleteAtIsNull(loanerId);
        if (optional.isEmpty()){
            return ResponseDto.<LoanerDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Loaner is not found")
                    .build();
        }
        try {
            Loaner loaner = optional.get();
            loaner.setUpdateAt(LocalDateTime.now());
            this.loanerMapper.updateLoanerFromDto(loanerDto, loaner);
            this.loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.loanerMapper.toDto(loaner))
                    .build();
        }catch (Exception e){
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message(String.format("Loaner while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<LoanerDto> delete(Integer loanerId) {
        Optional<Loaner> optional = loanerRepository.findByLoanerIdAndDeleteAtIsNull(loanerId);
        if (optional.isEmpty()){
            return ResponseDto.<LoanerDto>builder()
                    .code(-1)
                    .message("Loaner is not found")
                    .build();
        }
        try {
            Loaner loaner = optional.get();
            loaner.setDeleteAt(LocalDateTime.now());
            loanerRepository.save(loaner);
            return ResponseDto.<LoanerDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(loanerMapper.toDto(loaner)).build();
        }catch (Exception e){
            return ResponseDto.<LoanerDto>builder()
                    .code(-3)
                    .message(String.format("Loaner while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
