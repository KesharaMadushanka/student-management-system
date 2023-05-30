package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.StudentDto;
import com.example.studentmanagementsystem.model.Student;
import com.example.studentmanagementsystem.repository.StudentRepository;
import com.example.studentmanagementsystem.util.StudentNumberGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public String checkStudentExists(String nic, String email) {

        Long studentByNIC = studentRepository.getStudentByNIC(nic);
        Student studentByEmail = studentRepository.getStudentByEmail(email);

        if (studentByNIC != null) {
            return "nic";
        } else if (studentByEmail != null) {
            return "email";
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public void saveStudent(StudentDto studentDto) {


        Student student = new Student();
        student.setStudentFirstName(studentDto.getStudentFirstName());
        student.setStudentLastName(studentDto.getStudentLastName());
        student.setEmail(studentDto.getEmail());
        student.setNIC(studentDto.getNIC());
        student.setPhoneNumber(studentDto.getPhoneNumber());
        student.setDob(studentDto.getDob());

        entityManager.persist(student);
        entityManager.flush();

        Long id = student.getId();
        student.setStudentNumber(StudentNumberGenerator.getStudentNumber(id));

        studentRepository.save(student);
    }
}
