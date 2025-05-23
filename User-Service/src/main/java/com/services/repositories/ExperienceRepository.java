package com.services.repositories;

import com.services.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience,Long> {
    Optional<Experience> findByUserIdAndId(Long userId, Long expiId);
    List<Experience> findAllByUserId(Long id);
}
