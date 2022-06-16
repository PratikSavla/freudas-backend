package com.freudas.dev.repository;

import com.freudas.dev.model.UserAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAnalysisRepository extends JpaRepository<UserAnalysis, Long> {
}
