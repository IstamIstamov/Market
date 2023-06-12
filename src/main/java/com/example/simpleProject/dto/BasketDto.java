package com.example.simpleProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasketDto {
    private Integer basketId;
    private Set<ProductDto> products;
    @NotNull(message = "loanerId cannot be null")
    private Integer loanerId;
    private Double prodMass;
    private Double prodPrice;
    private Double totalPrice;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
