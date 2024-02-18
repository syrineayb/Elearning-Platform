package com.pfe.elearning.dto.mapper;

import com.pfe.elearning.dto.request.CandidateRequest;
import com.pfe.elearning.dto.response.CandidateResponse;
import com.pfe.elearning.entity.Candidate;

import org.springframework.stereotype.Service;
@Service
public class CandidateMapper {

    public CandidateResponse toCandidateDto(Candidate candidate) {

        return CandidateResponse.builder()
                .firstname(candidate.getFirstname())
                .lastname(candidate.getLastname())
                .age(candidate.getAge())
                .build();
    }

    public Candidate toCandidate(CandidateRequest candidate) {
        Candidate candidate1 = new Candidate();
        candidate1.setId(candidate.getId());
        candidate1.setFirstname(candidate.getFirstname());
        candidate1.setLastname(candidate.getLastname());
        candidate1.setAge(candidate.getAge());
        return candidate1;
    }
}