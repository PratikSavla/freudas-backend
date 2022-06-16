package com.freudas.dev.repository;

import com.freudas.dev.model.ChatMessage;
import com.freudas.dev.model.Conversation;
import com.freudas.dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findAllByUserAndEndTimeStampBetween(User user, LocalDateTime startTime, LocalDateTime endTime);
}
