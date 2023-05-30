package com.example.studentmanagementsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "student_number", unique = true)
    private String studentNumber;

    @Column(name = "first_name")
    private String studentFirstName;

    @Column(name = "last_name")
    private String studentLastName;

    @Column(unique = true, nullable = true)
    private String NIC;

    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private LocalDate dob;

}