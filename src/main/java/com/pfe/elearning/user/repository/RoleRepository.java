package com.pfe.elearning.user.repository;

import com.pfe.elearning.user.entity.Role;
import com.pfe.elearning.user.entity.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleEnum name);
}
