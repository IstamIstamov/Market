package com.example.simpleProject.dto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerResource {
    @ExceptionHandler
    public ResponseEntity<ResponseDto<Void>> methodArgumentNotValidException(MethodArgumentNotValidException e){
        List<ErrorDto> errors = e.getBindingResult().getFieldErrors().stream().map( fieldError -> {
            String field = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            String rejectionValue = String.valueOf(fieldError.getRejectedValue());
            return new ErrorDto(field, message + " Rejection value: " + rejectionValue);
        }
        ).toList();
        return ResponseEntity.badRequest().body(
                ResponseDto.<Void>builder()
                        .message("Validation error")
                        .code(-2)
                        .errors(errors)
                        .build()

        );
    }
}
