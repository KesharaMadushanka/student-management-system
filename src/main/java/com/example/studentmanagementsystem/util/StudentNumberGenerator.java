package com.example.studentmanagementsystem.util;

public class StudentNumberGenerator {
    public static String getStudentNumber(Long id) {
        Long newId = id + 1;
        return "ST"+id;
    }
}
