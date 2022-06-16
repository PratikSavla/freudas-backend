package com.freudas.dev.controller;

import com.freudas.dev.security.CurrentUser;
import com.freudas.dev.security.UserPrincipal;
import com.freudas.dev.service.IWorkQuestionService;
import com.freudas.dev.util.Constants;
import com.freudas.dev.viewmodel.*;
import com.freudas.dev.viewmodel.work.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work-question")
public class WorkQuestionsController {

    private final IWorkQuestionService workQuestionService;

    public WorkQuestionsController(IWorkQuestionService workQuestionService) {
        this.workQuestionService = workQuestionService;
    }
    @GetMapping("/")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public List<WorkQuestionTemplate> getUserQuestions(@CurrentUser UserPrincipal userPrincipal) {
        return this.workQuestionService.getAllQuestionsForUser(userPrincipal.getId());
    }
    @GetMapping("/analysis")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public UserAnalysisViewModel getUserAnalysis(@CurrentUser UserPrincipal userPrincipal) {
        return this.workQuestionService.getUserAnalysis(userPrincipal.getId());
    }
    @PostMapping("/add-comment")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse addComment(@RequestBody CommentViewModel viewModel) {
        return this.workQuestionService.addComment(viewModel);
    }
    @PutMapping("/skip/{questionId}")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse skipQuestion(@PathVariable long questionId) {
        return this.workQuestionService.skipQuestion(questionId);
    }
    @PostMapping("/add/binary")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addBinaryWorkQuestions(@RequestBody List<NewBinaryWorkQuestion> viewModels) {
        return this.workQuestionService.addBinaryWorkQuestions(viewModels);
    }
    @PostMapping("/add/dilemma")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addDilemmaWorkQuestions(@RequestBody List<NewDilemmaWorkQuestion> viewModels) {
        return this.workQuestionService.addDilemmaWorkQuestions(viewModels);
    }
    @PostMapping("/add/free-text")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addFreeTextWorkQuestions(@RequestBody List<NewFreeTextWorkQuestion> viewModels) {
        return this.workQuestionService.addFreeTextWorkQuestions(viewModels);
    }
    @PostMapping("/add/growing")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addGrowingWorkQuestions(@RequestBody List<NewGrowingWorkQuestion> viewModels) {
        return this.workQuestionService.addGrowingWorkQuestions(viewModels);
    }
    @PostMapping("/add/map")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addMapWorkQuestions(@RequestBody List<NewMapWorkQuestion> viewModels) {
        return this.workQuestionService.addMapWorkQuestions(viewModels);
    }
    @PostMapping("/add/rater")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addRaterWorkQuestions(@RequestBody List<NewRaterWorkQuestion> viewModels) {
        return this.workQuestionService.addRaterWorkQuestions(viewModels);
    }
    @PostMapping("/add/slider")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addSliderWorkQuestions(@RequestBody List<NewSliderWorkQuestion> viewModels) {
        return this.workQuestionService.addSliderWorkQuestions(viewModels);
    }
    @PostMapping("/add/super-binary")
    @PreAuthorize("hasRole('"+ Constants.ADMIN_USER_ROLE +"')")
    public ApiResponse addSuperBinaryWorkQuestions(@RequestBody List<NewSuperBinaryQuestion> viewModels) {
        return this.workQuestionService.addSuperBinaryWorkQuestions(viewModels);
    }

    @PutMapping("/answer/binary")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerBinaryQuestion(@RequestBody BinaryWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
    @PutMapping("/answer/dilemma")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerDilemmaQuestion(@RequestBody DilemmaWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
    @PutMapping("/answer/free-text")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerFreeTextQuestion(@RequestBody FreeTextWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
    @PutMapping("/answer/growing")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerGrowingQuestion(@RequestBody GrowingWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
    @PutMapping("/answer/map")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerMapQuestion(@RequestBody MapWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
    @PutMapping("/answer/rater")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerRaterQuestion(@RequestBody RaterWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
    @PutMapping("/answer/slider")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerSliderQuestion(@RequestBody SliderWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
    @PutMapping("/answer/super-binary")
    @PreAuthorize("hasRole('"+ Constants.WORK_USER_ROLE +"')")
    public ApiResponse answerSuperBinaryQuestion(@RequestBody SuperBinaryWorkAnswerViewModel viewModel) {
        return this.workQuestionService.answerQuestion(viewModel);
    }
}
