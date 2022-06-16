package com.freudas.dev.controller;

import com.freudas.dev.service.IWorkQuestionService;
import com.freudas.dev.viewmodel.WorkQuestionTagViewModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/question-tags")
public class QuestionTagsController {


    final private IWorkQuestionService workQuestionService;

    public QuestionTagsController(IWorkQuestionService workQuestionService) {
        this.workQuestionService = workQuestionService;
    }

    @GetMapping("/work")
    public List<WorkQuestionTagViewModel> getCurrentUser() {
        return this.workQuestionService.getAllTags();
    }
}
