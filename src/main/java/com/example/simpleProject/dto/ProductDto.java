package com.example.simpleProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private Integer productId;
    private String productName;
    private Double receivedPrice;
    private Double sellingPrice;
    private Double productMass;
    private Double amount;
    @NotNull(message = "baskedId cannot be null")
    private Integer basketId;
    @NotNull(message = "categoryId cannot be null")
    private Integer categoryId;
    @NotNull(message = "foreignId cannot be null")
    private Integer foreignId;
    @NotNull(message = "productBaseId cannot be null")
    private Integer productBaseId;
    private LocalDateTime expiredAt;
    private ImageDto images;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
