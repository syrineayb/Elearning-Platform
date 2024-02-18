package com.pfe.elearning.service;

import com.pfe.elearning.dto.request.CandidateRequest;
import com.pfe.elearning.dto.response.CandidateResponse;

import java.util.List;

public interface CandidateService {

    Integer save(CandidateRequest s);
    CandidateResponse findById(Integer id);
    List<CandidateResponse> findAll();
    void deleteById(Integer id);
}