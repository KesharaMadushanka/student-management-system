package com.example.studentmanagementsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jdk.jfr.DataAmount;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String studentNumber;

    @NotEmpty(message = "Enter Student's First Name")
    private String studentFirstName;

    @NotEmpty(message = "Enter Student's Last Name")
    private String studentLastName;

    @NotEmpty(message = "Enter Student &#39; s or Guardian's NIC Number")
    @Size(max = 12, message = "NIC Number has less than 12 digits")
    private String NIC;

    @NotEmpty(message = "Enter Phone Number")
    @Size(min = 10, max = 10, message = "Should Contain 10 Numbers")
    private String phoneNumber;

    @NotEmpty(message = "Enter Student's Email Address")
    @Email
    private String email;

    @NotNull(message = "Enter Date of Birth")
    private LocalDate dob;
}