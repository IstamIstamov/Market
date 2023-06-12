package com.example.simpleProject.controller;

import com.example.simpleProject.dto.ImageDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.service.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("image")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @PostMapping("/create")
    public ResponseDto<ImageDto> create(@Valid @RequestBody ImageDto imageDto){
        return imageService.create(imageDto);
    }

    @GetMapping("/get")
    public ResponseDto<ImageDto> get(@RequestParam Integer imageId){
        return imageService.get(imageId);
    }

    @PutMapping("/update")
    public ResponseDto<ImageDto> update(@RequestBody ImageDto imageDto, @RequestParam Integer imageId){
        return imageService.update(imageDto, imageId);
    }

    @DeleteMapping("/delete")
    public ResponseDto<ImageDto> delete(@RequestParam Integer imageId){
        return imageService.delete(imageId);
    }

}
