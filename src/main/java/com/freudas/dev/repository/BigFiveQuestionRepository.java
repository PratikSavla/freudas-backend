package com.freudas.dev.repository;

import com.freudas.dev.model.BigFiveQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BigFiveQuestionRepository extends JpaRepository<BigFiveQuestion, Long> {
}
