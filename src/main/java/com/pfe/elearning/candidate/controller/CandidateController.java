package com.pfe.elearning.candidate.controller;

import com.pfe.elearning.candidate.service.serviceImpl.CandidateServiceImpl;
import com.pfe.elearning.candidate.dto.request.CandidateRequest;
import com.pfe.elearning.candidate.dto.response.CandidateResponse;

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
@RequestMapping("/candidates")
// @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")
public class CandidateController {

    private final CandidateServiceImpl service;

    public CandidateController(CandidateServiceImpl service) {
        this.service = service;
    }


    @PostMapping
    //@PreAuthorize("hasAuthority('W')")
    public ResponseEntity<Long> save(
            @RequestBody @Valid CandidateRequest candidate
    ) {
        return ResponseEntity
                .accepted().body(service.save(candidate));
    }

    @GetMapping("/{candidate-id}")
    @PreAuthorize("hasAnyRole('ADMIN','CANDIDATE')")// ROLE_{ROLENAME} is forbidden
    public ResponseEntity<CandidateResponse> findById(
            @PathVariable("candidate-id") Long candidateId
    ) {
        return ResponseEntity.ok(service.findById(candidateId));
    }

    @GetMapping
    public ResponseEntity<List<CandidateResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

}