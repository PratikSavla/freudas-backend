package com.freudas.dev.controller;

import com.freudas.dev.security.CurrentUser;
import com.freudas.dev.security.UserPrincipal;
import com.freudas.dev.service.IRecommendationService;
import com.freudas.dev.viewmodel.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/recommendations")
public class RecommendationsController {

    final private IRecommendationService recommendationService;

    public RecommendationsController(IRecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @PostMapping("/{type}")
    public ResponseEntity<ApiResponse> setRecommendations(@RequestBody RecommendationsViewModel viewModel, @PathVariable("type") String type, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.recommendationService.setRecommendation(userPrincipal.getId(), type, viewModel));
    }
    @GetMapping("/")
    public ResponseEntity<RecommendationsViewModel> getRecommendations(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(this.recommendationService.getRecommendations(userPrincipal.getId()));
    }
}
