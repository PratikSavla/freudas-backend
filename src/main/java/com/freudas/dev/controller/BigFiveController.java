package com.freudas.dev.controller;

import com.freudas.dev.security.CurrentUser;
import com.freudas.dev.security.UserPrincipal;
import com.freudas.dev.service.IBigFiveService;
import com.freudas.dev.util.Constants;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.bigfive.BigFiveQuestionViewModel;
import com.freudas.dev.viewmodel.bigfive.BigFiveTraitsViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/big-five")
public class BigFiveController {
    private final IBigFiveService bigFiveService;

    public BigFiveController(IBigFiveService bigFiveService) {
        this.bigFiveService = bigFiveService;
    }

    @GetMapping("/questions")
    @PreAuthorize("hasRole('"+ Constants.LIFE_USER_ROLE +"')")
    ResponseEntity<List<BigFiveQuestionViewModel>> getAllUnansweredQuestions(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.bigFiveService.getUnansweredQuestions(userPrincipal.getId()));
    }

    @PostMapping("/answer/{answerId}")
    @PreAuthorize("hasRole('"+ Constants.LIFE_USER_ROLE +"')")
    ResponseEntity<ApiResponse> answerQuestion(@PathVariable long answerId, @RequestParam(required = true, name = "value") int value ) {
        return ResponseEntity.ok(this.bigFiveService.answerQuestion(answerId, value));
    }

    @GetMapping("/traits")
    @PreAuthorize("hasRole('"+ Constants.LIFE_USER_ROLE +"')")
    ResponseEntity<BigFiveTraitsViewModel> getTraits(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.bigFiveService.getTraits(userPrincipal.getId()));
    }
}
