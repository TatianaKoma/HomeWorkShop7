package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartCreationDto {

    @NotNull(message = "Id person cannot be null")
    @PositiveOrZero(message = "Id person cannot be negative")
    private Integer personId;

    @PositiveOrZero(message = "Id product cannot be negative")
    private Integer productId;
}
