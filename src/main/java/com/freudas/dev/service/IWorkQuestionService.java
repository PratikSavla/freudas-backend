package com.freudas.dev.service;

import com.freudas.dev.model.User;
import com.freudas.dev.model.WorkQuestion;
import com.freudas.dev.viewmodel.*;
import com.freudas.dev.viewmodel.work.*;

import java.util.List;

public interface IWorkQuestionService {

    List<WorkQuestionTemplate> getAllQuestionsForUser(long userId);
    UserAnalysisViewModel getUserAnalysis(long userId);
    ApiResponse addComment(CommentViewModel viewModel);
    ApiResponse skipQuestion(long questionAnswerId);
    ApiResponse answerQuestion(BinaryWorkAnswerViewModel viewModel);
    ApiResponse answerQuestion(DilemmaWorkAnswerViewModel viewModel);
    ApiResponse answerQuestion(FreeTextWorkAnswerViewModel viewModel);
    ApiResponse answerQuestion(GrowingWorkAnswerViewModel viewModel);
    ApiResponse answerQuestion(MapWorkAnswerViewModel viewModel);
    ApiResponse answerQuestion(RaterWorkAnswerViewModel viewModel);
    ApiResponse answerQuestion(SliderWorkAnswerViewModel viewModel);
    ApiResponse answerQuestion(SuperBinaryWorkAnswerViewModel viewModel);
    ApiResponse addBinaryWorkQuestions(List<NewBinaryWorkQuestion> viewModels);
    ApiResponse addDilemmaWorkQuestions(List<NewDilemmaWorkQuestion> viewModels);
    ApiResponse addFreeTextWorkQuestions(List<NewFreeTextWorkQuestion> viewModels);
    ApiResponse addGrowingWorkQuestions(List<NewGrowingWorkQuestion> viewModels);
    ApiResponse addMapWorkQuestions(List<NewMapWorkQuestion> viewModels);
    ApiResponse addRaterWorkQuestions(List<NewRaterWorkQuestion> viewModels);
    ApiResponse addSliderWorkQuestions(List<NewSliderWorkQuestion> viewModels);
    ApiResponse addSuperBinaryWorkQuestions(List<NewSuperBinaryQuestion> viewModels);
    List<WorkQuestionTagViewModel> getAllTags();

    void addNewQuestionForUser(WorkQuestion workQuestion, User user);
    int countUnansweredQuestions(User user);
    List<WorkQuestion> getAvailableWorkQuestions();
}
