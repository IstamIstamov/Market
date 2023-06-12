package com.example.simpleProject.service;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ProductDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.Product;
import com.example.simpleProject.repository.ProductRepository;
import com.example.simpleProject.service.mapper.ImageMapper;
import com.example.simpleProject.service.mapper.ProductMapper;
import com.example.simpleProject.service.validation.ProductValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductValidation productValidation;/*
    private final ImageMapper imageMapper;
    private final ImageService imageService;*/

    public ResponseDto<ProductDto> create(ProductDto productDto) {
        List<ErrorDto> errors = productValidation.validate(productDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ProductDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(productDto)
                    .errors(errors)
                    .build();
        }
        try {
            Product product = productMapper.toEntity(productDto);
            product.setCreateAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(productMapper.toDto(product))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(String.format("Product while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ProductDto> get(Integer productId) {
        try{
            Optional<Product> optional = productRepository.findByProductIdAndDeleteAtIsNull(productId);
            if (optional.isEmpty()){
                return ResponseDto.<ProductDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Product is not found")
                        .build();
            }
            /*optional.get().setImage(imageMapper.toEntity(imageService.get(productId).getData()));*/
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(productMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<ProductDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ProductDto> update(ProductDto productDto, Integer productId) {
        List<ErrorDto> errors = productValidation.validate(productDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ProductDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(productDto)
                    .errors(errors)
                    .build();
        }
        Optional<Product> optional = productRepository.findByProductIdAndDeleteAtIsNull(productId);
        if (optional.isEmpty()){
            return ResponseDto.<ProductDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Product is not found")
                    .build();
        }
        try {
            Product product = optional.get();
            product.setUpdateAt(LocalDateTime.now());
            this.productMapper.updateProductFromDto(productDto, product);
            this.productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.productMapper.toDto(product))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(String.format("Product while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ProductDto> delete(Integer productId) {
        Optional<Product> optional = productRepository.findByProductIdAndDeleteAtIsNull(productId);
        if (optional.isEmpty()){
            return ResponseDto.<ProductDto>builder()
                    .code(-1)
                    .message("Product is not found")
                    .build();
        }
        try {
            Product product = optional.get();
            product.setDeleteAt(LocalDateTime.now());
            productRepository.save(product);
            return ResponseDto.<ProductDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(productMapper.toDto(product)).build();
        }catch (Exception e){
            return ResponseDto.<ProductDto>builder()
                    .code(-3)
                    .message(String.format("Product while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
