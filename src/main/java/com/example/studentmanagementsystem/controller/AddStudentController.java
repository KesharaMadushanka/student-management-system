package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentDto;
import com.example.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/AddStudent")
public class AddStudentController {

    private final StudentService studentService;

    public AddStudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping()
    public String getAddStudentForm(Model model) {
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "admin/addStudent";
    }

    @PostMapping()
    public String addStudent(
            @Valid
            @ModelAttribute("student") StudentDto studentDto,
            BindingResult results,
            Model model

    ) {
        //check if student exists
        String existingField = studentService.checkStudentExists(studentDto.getNIC(), studentDto.getEmail());

        if (existingField != null) {
            switch (existingField) {
                case "nic" -> results.rejectValue("NIC", null, "Student already exist in this NIC");
                case "email" -> results.rejectValue("email", null, "Student already exist in this email");
        }

        }

        if (results.hasErrors()) {
            model.addAttribute("student", studentDto);
            return "admin/addStudent";
        }

        //if no errors save
        studentService.saveStudent(studentDto);
        return "redirect:/admin/AddStudent?success";
    }

}
