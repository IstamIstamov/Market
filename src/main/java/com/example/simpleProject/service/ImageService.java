package com.example.simpleProject.service;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ImageDto;
import com.example.simpleProject.dto.ResponseDto;
import com.example.simpleProject.model.Image;
import com.example.simpleProject.repository.ImageRepository;
import com.example.simpleProject.service.mapper.ImageMapper;
import com.example.simpleProject.service.validation.ImageValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;
    private final ImageValidation imageValidation;
    public ResponseDto<ImageDto> create(ImageDto imageDto) {
        List<ErrorDto> errors = imageValidation.validate(imageDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ImageDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(imageDto)
                    .errors(errors)
                    .build();
        }
        try {
            Image image = imageMapper.toEntity(imageDto);
            image.setCreateAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .code(1)
                    .message("Ok")
                    .data(imageMapper.toDto(image))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message(String.format("Image while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ImageDto> get(Integer imageId) {
        try{
            Optional<Image> optional = imageRepository.findByImageIdAndDeleteAtIsNull(imageId);
            if (optional.isEmpty()){
                return ResponseDto.<ImageDto>builder()
                        .success(false)
                        .code(-1)
                        .message("Image is not found")
                        .build();
            }
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .code(0)
                    .message("Ok")
                    .data(imageMapper.toDto(optional.get()))
                    .build();
        }catch (Exception c){
            return ResponseDto.<ImageDto>builder()
                    .message("Database error: " + c.getMessage())
                    .code(-3)
                    .data(null)
                    .build();
        }
    }

    public ResponseDto<ImageDto> update(ImageDto imageDto, Integer imageId) {
        List<ErrorDto> errors = imageValidation.validate(imageDto);
        if (!errors.isEmpty()){
            return ResponseDto.<ImageDto>builder()
                    .message("Validation error")
                    .code(-2)
                    .data(imageDto)
                    .errors(errors)
                    .build();
        }
        Optional<Image> optional = imageRepository.findByImageIdAndDeleteAtIsNull(imageId);
        if (optional.isEmpty()){
            return ResponseDto.<ImageDto>builder()
                    .success(false)
                    .code(-1)
                    .message("Image is not found")
                    .build();
        }
        try {
            Image image = optional.get();
            image.setUpdateAt(LocalDateTime.now());
            this.imageMapper.updateImageFromDto(imageDto, image);
            this.imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(this.imageMapper.toDto(image))
                    .build();
        }catch (Exception e){
            return ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message(String.format("Image while saving error -> %s", e.getMessage()))
                    .build();
        }
    }

    public ResponseDto<ImageDto> delete(Integer imageId) {
        Optional<Image> optional = imageRepository.findByImageIdAndDeleteAtIsNull(imageId);
        if (optional.isEmpty()){
            return ResponseDto.<ImageDto>builder()
                    .code(-1)
                    .message("Image is not found")
                    .build();
        }
        try {
            Image image = optional.get();
            image.setDeleteAt(LocalDateTime.now());
            imageRepository.save(image);
            return ResponseDto.<ImageDto>builder()
                    .success(true)
                    .message("Ok")
                    .data(imageMapper.toDto(image)).build();
        }catch (Exception e){
            return ResponseDto.<ImageDto>builder()
                    .code(-3)
                    .message(String.format("Image while saving error -> %s", e.getMessage()))
                    .build();
        }
    }
}
