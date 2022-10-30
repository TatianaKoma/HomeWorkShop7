package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String passwordConfirm;
}
