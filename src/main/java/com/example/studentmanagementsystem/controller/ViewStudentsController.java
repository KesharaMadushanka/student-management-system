package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/viewStudent")
public class ViewStudentsController {

    private final StudentService studentService;

    public ViewStudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public String ViewAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        if (students == null) {
            return "admin/viewStudent?notfound";
        } else {
            model.addAttribute("students", students);
            return "admin/viewStudent";
        }

    }

    @PostMapping("/delete/{id}")
    public String DeleteStudent(@PathVariable Long id) {
        if (studentService.deleteStudent(id)) {
            return "redirect:/admin/viewStudent?delSuccess";
        } else {
            return "redirect:/admin/viewStudent?delError";
        }
    }

}
