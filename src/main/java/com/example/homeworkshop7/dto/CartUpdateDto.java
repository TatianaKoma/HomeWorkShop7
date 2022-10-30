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
public class CartUpdateDto {

    @NotNull(message = "Id cart cannot be null")
    @PositiveOrZero(message = "Id cart cannot be negative")
    private Integer id;

    @NotNull(message = "Id product cannot be null")
    @PositiveOrZero(message = "Id product cannot be negative")
    private Integer productsId;
}
