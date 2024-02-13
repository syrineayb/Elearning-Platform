package com.pfe.elearning.controller;

import com.pfe.elearning.dto.StudentRequest;
import com.pfe.elearning.dto.StudentResponse;
import com.pfe.elearning.services.StudentService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/students")
// @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('W')")
    public ResponseEntity<Integer> save(
            @RequestBody @Valid StudentRequest student
    ) {
        return ResponseEntity
                .accepted().body(service.save(student));
    }

    @GetMapping("/{student-id}")
    @PreAuthorize("hasAnyRole('ADMIN','STUDENT')")// ROLE_{ROLENAME} is forbidden
    public ResponseEntity<StudentResponse> findById(
            @PathVariable("student-id") Integer studentId
    ) {
        return ResponseEntity.ok(service.findById(studentId));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}