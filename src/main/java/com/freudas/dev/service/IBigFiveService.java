package com.freudas.dev.service;

import com.freudas.dev.model.User;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.bigfive.BigFiveQuestionViewModel;
import com.freudas.dev.viewmodel.bigfive.BigFiveTraitsViewModel;

import java.util.List;

public interface IBigFiveService {
    void addQuestionsToUser(User user);
    List<BigFiveQuestionViewModel> getUnansweredQuestions(long userId);
    ApiResponse answerQuestion(long answerId, int score);
    BigFiveTraitsViewModel getTraits(long userId);
}
