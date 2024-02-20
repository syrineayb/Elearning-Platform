package com.pfe.elearning.domain.repository;

import com.pfe.elearning.domain.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<Domain,Long> {
}
