package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.StudentDto;
import com.example.studentmanagementsystem.model.Student;

import java.util.List;

public interface StudentService {

    String checkStudentExists(String nic, String email);
    void saveStudent(StudentDto studentDto);
    List<Student> getAllStudents();
    boolean deleteStudent(Long id);

}
