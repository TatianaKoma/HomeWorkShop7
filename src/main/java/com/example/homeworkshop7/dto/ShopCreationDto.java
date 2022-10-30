package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopCreationDto {

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 255, message = "Name must have at least 2 characters")
    private String name;
}
