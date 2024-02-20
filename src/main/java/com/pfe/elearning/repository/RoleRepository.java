package com.pfe.elearning.repository;

import com.pfe.elearning.entity.Role;
import com.pfe.elearning.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleEnum name);
}
