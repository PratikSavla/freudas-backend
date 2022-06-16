package com.freudas.dev.repository;

import com.freudas.dev.model.User;
import com.freudas.dev.model.WorkUserQuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkUserQuestionAnswerRepository extends JpaRepository<WorkUserQuestionAnswer, Long> {

    @Query("SELECT count(qa) FROM WorkUserQuestionAnswer qa WHERE qa.user = ?1 AND qa.dateTimeAnswered = null")
    int countUnansweredQuestionsForUser(User user);

    @Query("SELECT qa FROM WorkUserQuestionAnswer qa WHERE qa.user = ?1 AND qa.dateTimeAnswered = null")
    List<WorkUserQuestionAnswer> getUnansweredQuestionsForUser(User user);

    @Query("SELECT qa FROM WorkUserQuestionAnswer qa WHERE qa.dateTimeAnswered >= ?1 AND "
            + "qa.dateTimeAnswered <= ?2 AND "
            + "qa.skipped = false AND "
            + "qa.user IN ?3")
    List<WorkUserQuestionAnswer> getAnswersForUsersBetweenDates(LocalDateTime startDate, LocalDateTime endDate, List<User> user);

    List<WorkUserQuestionAnswer> findByUserAndDateTimeAnsweredBetweenAndSkipped(User user, LocalDateTime startDate, LocalDateTime endDate, boolean skipped);
}