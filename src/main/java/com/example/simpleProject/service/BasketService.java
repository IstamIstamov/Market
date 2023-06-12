package com.example.simpleProject.service;

import com.example.simpleProject.dto.BasketDto;
import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.Basket;
import com.example.simpleProject.repository.BasketRepository;
import com.example.simpleProject.service.mapper.BasketMapper;
import com.example.simpleProject.service.validation.BasketValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BasketService {
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;
    private final BasketValidation basketValidation;
    public ResponseDto<BasketDto> create(BasketDto basketDto) {
        List<ErrorDto> errors = basketValidation.validate(basketDto);
        if (!errors.isEmpty()){
            return ResponseDto.<BasketDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(basketDto)
                    .errors(errors)
                    .build();
        }
        try {
            Basket basket = basketMapper.toEntity(basketDto);
            basket.setCreateAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(basketMapper.toDto(basket))
                    .build();
        }catch (Exception e){
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message(String.format("Card while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<BasketDto> get(Integer basketId) {
        try{
            Optional<Basket> optional = basketRepository.findByBasketIdAndDeleteAtIsNull(basketId);
            if (optional.isEmpty()){
                return ResponseDto.<BasketDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Basket is not found")
                        .build();
            }
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(basketMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<BasketDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<BasketDto> update(BasketDto basketDto, Integer basketId) {
        List<ErrorDto> errors = basketValidation.validate(basketDto);
        if (!errors.isEmpty()){
            return ResponseDto.<BasketDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(basketDto)
                    .errors(errors)
                    .build();
        }
        Optional<Basket> optional = basketRepository.findByBasketIdAndDeleteAtIsNull(basketId);
        if (optional.isEmpty()){
            return ResponseDto.<BasketDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Basket is not found")
                    .build();
        }
        try {
            Basket basket = optional.get();
            basket.setUpdateAt(LocalDateTime.now());
            this.basketMapper.updateBasketFromDto(basketDto, basket);
            this.basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.basketMapper.toDto(basket))
                    .build();
        }catch (Exception e){
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message(String.format("Basket while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<BasketDto> delete(Integer basketId) {
        Optional<Basket> optional = basketRepository.findByBasketIdAndDeleteAtIsNull(basketId);
        if (optional.isEmpty()){
            return ResponseDto.<BasketDto>builder()
                    .code(-1)
                    .message("Basket is not found")
                    .build();
        }
        try {
            Basket basket = optional.get();
            basket.setDeleteAt(LocalDateTime.now());
            basketRepository.save(basket);
            return ResponseDto.<BasketDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(basketMapper.toDto(basket)).build();
        }catch (Exception e){
            return ResponseDto.<BasketDto>builder()
                    .code(-3)
                    .message(String.format("Basket while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
