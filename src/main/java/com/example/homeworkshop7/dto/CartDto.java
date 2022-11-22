package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private Integer id;
    private Integer personId;
    private BigDecimal sumUah;
    private BigDecimal sumUsd;
    private List<Integer> productsId;
}
