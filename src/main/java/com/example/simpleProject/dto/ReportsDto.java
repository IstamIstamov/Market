package com.example.simpleProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportsDto {
    private Integer reportsId;
    private String prodName;
    private Double prodPercent;
    private Set<CategoryDto> categories;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
