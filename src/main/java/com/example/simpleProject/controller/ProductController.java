package com.example.simpleProject.controller;

import com.example.simpleProject.dto.ProductDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/create")
    public ResponseDto<ProductDto> create(@Valid @RequestBody ProductDto productDto){
        return productService.create(productDto);
    }
    @GetMapping("/get")
    public ResponseDto<ProductDto> get(@RequestParam Integer productId){
        return productService.get(productId);
    }

    @PutMapping("/update")
    public ResponseDto<ProductDto> update(@RequestBody ProductDto productDto, @RequestParam Integer productId){
        return productService.update(productDto, productId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<ProductDto> delete(@RequestParam Integer productId){
        return productService.delete(productId);
    }

}
