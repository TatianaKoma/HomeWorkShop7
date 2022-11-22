package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationDto {
    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 255, message = "Name must have at least 2 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price cannot be negative")
    private BigDecimal priceUah;

    @NotNull(message = "ShopId cannot be null")
    @PositiveOrZero(message = "ShopId cannot be negative")
    private int shopId;
}
