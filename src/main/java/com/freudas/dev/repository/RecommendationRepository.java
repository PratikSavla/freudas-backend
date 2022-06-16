package com.freudas.dev.repository;

import com.freudas.dev.model.Recommendations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendationRepository extends JpaRepository<Recommendations, Long> {
}
