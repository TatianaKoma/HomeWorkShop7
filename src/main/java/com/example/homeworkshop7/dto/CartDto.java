package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Integer id;
    private Integer personId;
    private BigDecimal sum;
    private List<Integer> productsId;
}
