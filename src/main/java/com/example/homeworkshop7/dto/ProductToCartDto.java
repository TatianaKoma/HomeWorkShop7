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
public class ProductToCartDto {

    @NotNull(message = "ProductId cannot be null")
    @PositiveOrZero(message = "ProductId cannot be negative")
    private int productId;

    @PositiveOrZero(message = "CartId cannot be negative")
    private int cartId;
}
