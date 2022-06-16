package com.freudas.dev.repository;

import com.freudas.dev.model.WorkQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkQuestionRepository extends JpaRepository<WorkQuestion, Long> {
}
