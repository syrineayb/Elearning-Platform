package com.pfe.elearning.candidate.service.serviceImpl;

import com.pfe.elearning.candidate.dto.mapper.CandidateMapper;
import com.pfe.elearning.candidate.dto.request.CandidateRequest;
import com.pfe.elearning.candidate.dto.response.CandidateResponse;
import com.pfe.elearning.candidate.entity.Candidate;
import com.pfe.elearning.candidate.repository.CandidateRepository;
import com.pfe.elearning.candidate.service.CandidateService;
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
    public Long save(CandidateRequest studentRequest) {
        Candidate student = mapper.toCandidate(studentRequest);
        Candidate savedStudent = candidateRepository.save(student);
        return savedStudent.getId();
    }

    @Override
    public CandidateResponse findById(Long id) {
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
    public void deleteById(Long id) {
        candidateRepository.deleteById(id);
    }
}
