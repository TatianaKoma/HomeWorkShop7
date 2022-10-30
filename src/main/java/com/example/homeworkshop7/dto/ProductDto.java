package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer productId;
    private String name;
    private BigDecimal price;
    private Integer shopId;

    @Override
    public String toString() {
        return "{" +
                "id=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", shopId=" + shopId +
                '}';
    }
}
