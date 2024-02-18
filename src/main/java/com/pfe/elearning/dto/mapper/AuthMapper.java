package com.pfe.elearning.dto.mapper;

import com.pfe.elearning.dto.request.RegisterRequest;
import com.pfe.elearning.entity.Candidate;
import org.springframework.stereotype.Service;

@Service
public class AuthMapper {

    public Candidate toCandidate(RegisterRequest s) {
        Candidate candidate = new Candidate();
        candidate.setFirstname(s.getFirstname());
        candidate.setLastname(s.getLastname());
        candidate.setEmail(s.getEmail());
        // FIXME
        candidate.setPassword(s.getPassword());
        candidate.setEnabled(true);
        return candidate;
    }
}