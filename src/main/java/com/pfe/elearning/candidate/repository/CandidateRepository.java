package com.pfe.elearning.candidate.repository;

import com.pfe.elearning.candidate.entity.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
