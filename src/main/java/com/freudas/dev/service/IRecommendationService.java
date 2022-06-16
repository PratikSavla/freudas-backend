package com.freudas.dev.service;

import com.freudas.dev.model.Recommendations;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.RecommendationsViewModel;

public interface IRecommendationService {
    ApiResponse setRecommendation(long userId, String type, RecommendationsViewModel vm);
    RecommendationsViewModel getRecommendations(long userId);
}
