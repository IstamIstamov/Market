package com.example.simpleProject.controller;

import com.example.simpleProject.dto.ProductBaseDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.ProductBaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product-base")
@RequiredArgsConstructor
public class ProductBaseController {
    private final ProductBaseService productBaseService;
    @PostMapping("/create")
    public ResponseDto<ProductBaseDto> create(@Valid @RequestBody ProductBaseDto productBaseDto){
        return productBaseService.create(productBaseDto);
    }

    @GetMapping("/get")
    public ResponseDto<ProductBaseDto> get(@RequestParam Integer productBaseId){
        return productBaseService.get(productBaseId);
    }

    @PutMapping("/update")
    public ResponseDto<ProductBaseDto> update(@RequestBody ProductBaseDto productBaseDto, @RequestParam Integer productBaseId){
        return productBaseService.update(productBaseDto, productBaseId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<ProductBaseDto> delete(@RequestParam Integer productBaseId){
        return productBaseService.delete(productBaseId);
    }

}
