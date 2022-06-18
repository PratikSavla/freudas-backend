package com.freudas.dev.service;

import com.freudas.dev.model.User;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.ChatMessageViewModel;

import java.time.LocalDateTime;

public interface IChatService {
    ApiResponse startConversation(long userId);
    ApiResponse addMessage(long conversationId, ChatMessageViewModel vm);
    void doAnalysisFor7Days(User user, LocalDateTime endDateTime);
}
