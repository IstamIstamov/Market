package com.example.simpleProject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String middleName;
    private String username;
    private String borrowName;
    private String phoneNumber;
    private String passportSeries;
    private String firstAddress;
    private String secondAddress;
    private Double monthlyPrice;
    private LoanerDto loaner;
    private LocalDateTime birthDate;
    private LocalDateTime workingTime;
    private LocalDateTime workingDays;
    @NotNull(message = "employeeId cannot be null")
    private Integer employeeId;
    private ImageDto image;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;
}
