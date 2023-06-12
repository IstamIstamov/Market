package com.example.simpleProject.service;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ProductBaseDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.ProductBase;
import com.example.simpleProject.repository.ProductBaseRepository;
import com.example.simpleProject.service.mapper.ProductBaseMapper;
import com.example.simpleProject.service.validation.ProductBaseValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductBaseService {
    private final ProductBaseRepository productBaseRepository;
    private final ProductBaseMapper productBaseMapper;
    private final ProductBaseValidation productBaseValidation;

    public ResponseDto<ProductBaseDto> create(ProductBaseDto productBaseDto) {
        List<ErrorDto> errors = productBaseValidation.validate(productBaseDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(productBaseDto)
                    .errors(errors)
                    .build();
        }
        try {
            ProductBase productBase = productBaseMapper.toEntity(productBaseDto);
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(productBaseMapper.toDto(productBase))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ProductBaseDto>builder()
                    .code(-3)
                    .message(String.format("Product base while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> get(Integer productBaseId) {
        try{
            Optional<ProductBase> optional = productBaseRepository.findByProductBaseId(productBaseId);
            if (optional.isEmpty()){
                return ResponseDto.<ProductBaseDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Product base is not found")
                        .build();
            }
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(productBaseMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> update(ProductBaseDto productBaseDto, Integer productBaseId) {
        List<ErrorDto> errors = productBaseValidation.validate(productBaseDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ProductBaseDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(productBaseDto)
                    .errors(errors)
                    .build();
        }
        Optional<ProductBase> optional = productBaseRepository.findByProductBaseId(productBaseId);
        if (optional.isEmpty()){
            return ResponseDto.<ProductBaseDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Product base is not found")
                    .build();
        }
        try {
            ProductBase productBase = optional.get();
            this.productBaseMapper.updateProductBaseFromDto(productBaseDto, productBase);
            this.productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.productBaseMapper.toDto(productBase))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ProductBaseDto>builder()
                    .code(-3)
                    .message(String.format("Product base while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ProductBaseDto> delete(Integer productBaseId) {
        Optional<ProductBase> optional = productBaseRepository.findByProductBaseId(productBaseId);
        if (optional.isEmpty()){
            return ResponseDto.<ProductBaseDto>builder()
                    .code(-1)
                    .message("Product base is not found")
                    .build();
        }
        try {
            ProductBase productBase = optional.get();
            productBaseRepository.save(productBase);
            return ResponseDto.<ProductBaseDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(productBaseMapper.toDto(productBase)).build();
        }catch (Exception e){
            return ResponseDto.<ProductBaseDto>builder()
                    .code(-3)
                    .message(String.format("Product base while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
