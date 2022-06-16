package com.freudas.dev.controller;

import com.freudas.dev.security.CurrentUser;
import com.freudas.dev.security.UserPrincipal;
import com.freudas.dev.service.IChatService;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.ChatMessageViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {

    private final IChatService chatService;

    public ChatController(IChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/new")
    public ResponseEntity<ApiResponse> newChat(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.chatService.startConversation(userPrincipal.getId()));
    }
    @PutMapping("/{conversationId}")
    public ResponseEntity<ApiResponse> logMessage(@PathVariable long conversationId, @RequestBody ChatMessageViewModel vm) {
        return ResponseEntity.ok(this.chatService.addMessage(conversationId,vm));
    }
}
