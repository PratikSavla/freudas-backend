package com.freudas.dev.repository;

import com.freudas.dev.model.BigFiveAnswer;
import com.freudas.dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BigFiveAnswerRepository extends JpaRepository<BigFiveAnswer, Long> {
    @Query("SELECT bfa FROM BigFiveAnswer bfa WHERE bfa.user = ?1 AND bfa.dateTimeAnswered = null")
    List<BigFiveAnswer> getAllUnansweredQuestionsForUser(User user);

    @Query("SELECT bfa FROM BigFiveAnswer bfa WHERE bfa.user = ?1 AND bfa.dateTimeAnswered >= ?2 AND bfa.dateTimeAnswered <= ?3")
    List<BigFiveAnswer> getAllAnsweredQuestionsForUser(User user, LocalDateTime start, LocalDateTime end);
}
