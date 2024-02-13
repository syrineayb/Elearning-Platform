package com.pfe.elearning.dto;

import com.pfe.elearning.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

    public Student toStudent(RegisterRequest s) {
        Student student = new Student();
        student.setFirstname(s.getFirstname());
        student.setLastname(s.getLastname());
        student.setEmail(s.getEmail());
        // FIXME
        student.setPassword(s.getPassword());
        student.setEnabled(true);
        return student;
    }
}