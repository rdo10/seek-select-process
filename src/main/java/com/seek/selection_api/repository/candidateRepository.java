package com.seek.selection_api.repository;

import com.seek.selection_api.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface candidateRepository extends JpaRepository<Candidate, Long> {
}
