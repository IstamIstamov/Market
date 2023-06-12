package com.example.simpleProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ForeignDebtDto {
    private Integer foreignId;
    private String companyName;
    private String fullName;
    private String firstPhoneNumber;
    private String secondPhoneNumber;
    private Set<ProductDto> products;
    private Boolean status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
