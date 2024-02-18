package com.pfe.elearning.service.impl;

import com.pfe.elearning.dto.mapper.CandidateMapper;
import com.pfe.elearning.dto.request.CandidateRequest;
import com.pfe.elearning.dto.response.CandidateResponse;
import com.pfe.elearning.entity.Candidate;
import com.pfe.elearning.repository.CandidateRepository;
import com.pfe.elearning.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;
    private final CandidateMapper mapper;

    @Override
    public Integer save(CandidateRequest studentRequest) {
        Candidate student = mapper.toCandidate(studentRequest);
        Candidate savedStudent = candidateRepository.save(student);
        return savedStudent.getId();
    }

    @Override
    public CandidateResponse findById(Integer id) {
        Candidate student = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found with ID: " + id));
        return mapper.toCandidateDto(student);
    }

    @Override
    public List<CandidateResponse> findAll() {
        List<Candidate> students = candidateRepository.findAll();
        return students.stream()
                .map(mapper::toCandidateDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        candidateRepository.deleteById(id);
    }
}
