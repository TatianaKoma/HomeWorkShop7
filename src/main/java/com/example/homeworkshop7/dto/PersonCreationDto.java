package com.example.homeworkshop7.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonCreationDto {

    @NotBlank(message = "Name cannot be null")
    @Size(min = 2, max = 255, message = "Name must have at least 2 characters")
    private String name;

    @NotBlank(message = "Surname cannot be null")
    @Size(min = 2, max = 255, message = "Surname must have at least 2 characters")
    private String surname;

    @Size(max = 60)
    @Email(message = "Should have email format")
    @NonNull
    private String email;

    @NotBlank(message = "Username cannot be null")
    @Size(min = 2, max = 255, message = "Username  must have at least 2 characters")
    private String username;

    @NotBlank(message = "Password cannot be null")
    @Size(min = 2, max = 255, message = "Password must have at least 2 characters")
    private String password;
}
