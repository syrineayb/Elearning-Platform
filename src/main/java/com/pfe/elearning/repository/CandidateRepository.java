package com.pfe.elearning.repository;

import com.pfe.elearning.entity.Candidate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
