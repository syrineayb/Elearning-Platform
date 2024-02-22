package com.pfe.elearning.candidate.service;

import com.pfe.elearning.candidate.dto.request.CandidateRequest;
import com.pfe.elearning.candidate.dto.response.CandidateResponse;

import java.util.List;

public interface CandidateService {

    Long save(CandidateRequest s);
    CandidateResponse findById(Long id);
    List<CandidateResponse> findAll();
    void deleteById(Long id);
}