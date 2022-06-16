package com.freudas.dev.service;

import com.freudas.dev.model.Recommendations;
import com.freudas.dev.model.User;
import com.freudas.dev.repository.RecommendationRepository;
import com.freudas.dev.repository.UserRepository;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.RecommendationsViewModel;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.beans.Beans;

@Service
public class RecommendationService implements IRecommendationService{

    final private RecommendationRepository recommendationRepository;
    final private UserRepository userRepository;

    public RecommendationService(RecommendationRepository recommendationRepository, UserRepository userRepository) {
        this.recommendationRepository = recommendationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse setRecommendation(long userId, String type, RecommendationsViewModel vm) {
        User user = this.userRepository.getById(userId);
        Recommendations recommendations = user.getRecommendations() == null ? new Recommendations(user):user.getRecommendations();
        if (type.equalsIgnoreCase("article")) {
            recommendations.setArticleURL(vm.getArticleURL());
            recommendations.setArticleTitle(vm.getArticleTitle());
        } else if (type.equalsIgnoreCase("quote")) {
            recommendations.setQuote(vm.getQuote());
        }
        this.recommendationRepository.save(recommendations);
        return new ApiResponse(true, "Recommendation saved!");
    }

    @Override
    public RecommendationsViewModel getRecommendations(long userId) {
        Recommendations recommendations = this.userRepository.getById(userId).getRecommendations();
        RecommendationsViewModel vm = new RecommendationsViewModel();
        BeanUtils.copyProperties(recommendations == null?new Recommendations():recommendations, vm);
        return vm;
    }
}
