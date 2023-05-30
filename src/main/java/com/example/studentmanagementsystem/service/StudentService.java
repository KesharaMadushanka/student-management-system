package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.StudentDto;

public interface StudentService {

    String checkStudentExists(String nic, String email);
    void saveStudent(StudentDto studentDto);

}
