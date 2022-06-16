package com.freudas.dev.service;

//import com.freudas.dev.config.NLPPipeline;
import com.freudas.dev.exception.ResourceNotFoundException;
import com.freudas.dev.model.ChatMessage;
import com.freudas.dev.model.Conversation;
import com.freudas.dev.model.User;
import com.freudas.dev.model.UserAnalysis;
import com.freudas.dev.repository.ChatMessageRepository;
import com.freudas.dev.repository.ConversationRepository;
import com.freudas.dev.repository.UserAnalysisRepository;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.ChatMessageViewModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ChatService implements IChatService{

    private final ConversationRepository conversationRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final UserAnalysisRepository analysisRepository;
    private final IUserService userService;
//    private final NLPPipeline nlpPipeline;

    public ChatService(ConversationRepository conversationRepository, ChatMessageRepository chatMessageRepository, UserAnalysisRepository analysisRepository, IUserService userService) {
        this.conversationRepository = conversationRepository;
        this.chatMessageRepository = chatMessageRepository;
        this.analysisRepository = analysisRepository;
        this.userService = userService;
    }

    @Override
    public ApiResponse startConversation(long userId) {
        User user = userService.getUserById(userId);
        Conversation conv = new Conversation();
        conv.setUser(user);
        conv.setStartTimestamp(LocalDateTime.now());
        conv.setEndTimeStamp(LocalDateTime.now());
        long convId = conversationRepository.save(conv).getId();
        return new ApiResponse(true, Long.toString(convId));
    }

    @Override
    public ApiResponse addMessage(long conversationId, ChatMessageViewModel vm) {
        Conversation conv = this.conversationRepository.findById(conversationId).orElseThrow(() -> new ResourceNotFoundException("Conversation", "id", conversationId));
//        ChatMessage message = new ChatMessage(vm.getQuestion(), vm.getReply(), this.nlpPipeline.estimatingSentiment(vm.getReply()));
        ChatMessage message = new ChatMessage(vm.getQuestion(), vm.getReply(), 1.0);

        conv.getMessages().add(message);
        conv.setEndTimeStamp(LocalDateTime.now());
        this.conversationRepository.save(conv);
        return new ApiResponse(true, "Message Logged!");
    }

    @Override
    public void doAnalysisFor7Days(User user, LocalDateTime endDateTime) {
         double score = this.conversationRepository.findAllByUserAndEndTimeStampBetween(user, endDateTime.minusDays(7),endDateTime)
                .stream().flatMap(conversation -> conversation.getMessages().stream())
                 .mapToDouble(ChatMessage::getScore)
                .average().orElse(0.0);
         this.analysisRepository.save(new UserAnalysis(score, user, endDateTime));
    }
}
