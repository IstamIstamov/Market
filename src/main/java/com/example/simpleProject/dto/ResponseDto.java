package com.example.simpleProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private Boolean success;
    private Integer code;
    /*
     * 0 - all ok
     * -1 - not found
     * -2 - validation error
     * -3 - database error
     */
    private String message;
    private T data;
    private List<ErrorDto> errors;
}
