package com.example.studentmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotEmpty(message = "Please enter your name")
    private String name;

    @NotEmpty(message = "Please enter a valid email address")
    @Email
    private String email;

    @NotEmpty(message = "Please enter a password")
    private String password;
}