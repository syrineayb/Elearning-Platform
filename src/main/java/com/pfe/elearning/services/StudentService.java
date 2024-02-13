package com.pfe.elearning.services;

import com.pfe.elearning.dto.StudentRequest;
import com.pfe.elearning.dto.StudentResponse;

import java.util.List;

public interface StudentService {

    Integer save(StudentRequest s);
    StudentResponse findById(Integer id);
    List<StudentResponse> findAll();
    void deleteById(Integer id);
}