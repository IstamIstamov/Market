package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ProductDto;
import com.example.simpleProject.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductValidation {
    private final BasketService basketService;
    public List<ErrorDto> validate(ProductDto productDto) {
        List<ErrorDto> errors = new ArrayList<>();
        if (basketService.get(productDto.getBasketId()).getData() == null){
            errors.add(new ErrorDto("basketId", "Basket is not found"));
        }
        if (basketService.get(productDto.getForeignId()).getData() == null){
            errors.add(new ErrorDto("foreignId", "Foreign is not found"));
        }
        if (basketService.get(productDto.getProductBaseId()).getData() == null){
            errors.add(new ErrorDto("productBaseId", "Product base is not found"));
        }
        if (basketService.get(productDto.getCategoryId()).getData() == null){
            errors.add(new ErrorDto("categoryId", "Category is not found"));
        }
        return errors;
    }
}
