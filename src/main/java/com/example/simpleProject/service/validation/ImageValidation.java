package com.example.simpleProject.service.validation;

import com.example.simpleProject.dto.ErrorDto;
import com.example.simpleProject.dto.ImageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class ImageValidation {

    public List<ErrorDto> validate(ImageDto imageDto) {
        List<ErrorDto> errors = new ArrayList<>();
        return errors;
    }
}
