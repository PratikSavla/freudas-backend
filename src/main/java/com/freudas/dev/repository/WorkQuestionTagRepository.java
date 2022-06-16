package com.freudas.dev.repository;

import com.freudas.dev.model.WorkQuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkQuestionTagRepository extends JpaRepository<WorkQuestionTag, Long> {
}
