package com.example.studentmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoUpdate {

    private Long id;

    @NotEmpty(message = "Please enter your name")
    private String name;

    @NotEmpty(message = "Please enter a valid email address")
    @Email
    private String email;

}