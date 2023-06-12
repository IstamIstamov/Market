package com.example.simpleProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoanerDto {
    private Integer loanerId;
    private Integer baskedId;
    private Double totalPrice;
    private Boolean status;
    private Set<BasketDto> basket;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
