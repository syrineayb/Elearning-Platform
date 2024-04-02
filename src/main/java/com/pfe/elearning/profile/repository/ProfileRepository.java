package com.pfe.elearning.profile.repository;

import com.pfe.elearning.profile.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    boolean existsByUserId(Long userId);

    Optional<Profile> findById(Long userId);
    Profile findByEmail(String oldEmail);

}
