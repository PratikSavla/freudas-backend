package com.freudas.dev.service;

import com.freudas.dev.exception.BadRequestException;
import com.freudas.dev.exception.ResourceNotFoundException;
import com.freudas.dev.model.*;
import com.freudas.dev.repository.WorkQuestionTagRepository;
import com.freudas.dev.repository.WorkQuestionRepository;
import com.freudas.dev.repository.WorkUserQuestionAnswerRepository;
import com.freudas.dev.viewmodel.*;
import com.freudas.dev.viewmodel.work.*;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkQuestionService implements IWorkQuestionService{

    final private WorkQuestionRepository workQuestionRepository;
    final private WorkQuestionTagRepository workQuestionTagRepository;
    final private WorkUserQuestionAnswerRepository workUserQuestionAnswerRepository;
    final private IUserService userService;

    public WorkQuestionService(WorkQuestionRepository workQuestionRepository, WorkQuestionTagRepository workQuestionTagRepository, WorkUserQuestionAnswerRepository workUserQuestionAnswerRepository, IUserService userService) {
        this.workQuestionRepository = workQuestionRepository;
        this.workQuestionTagRepository = workQuestionTagRepository;
        this.workUserQuestionAnswerRepository = workUserQuestionAnswerRepository;
        this.userService = userService;
    }


    @Override
    @Transactional
    public void addNewQuestionForUser(WorkQuestion workQuestion, User user) {
        WorkUserQuestionAnswer wuqa = new WorkUserQuestionAnswer();
        wuqa.setQuestion(workQuestion);
        wuqa.setUser(user);
        wuqa.setDateTimeAdded(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(wuqa);
    }

    @Override
    public int countUnansweredQuestions(User user) {
        return this.workUserQuestionAnswerRepository.countUnansweredQuestionsForUser(user);
    }
    @Override
    public List<WorkQuestionTemplate> getAllQuestionsForUser(long userId) {
        User user = this.userService.getUserById(userId);
        List<WorkUserQuestionAnswer> questions = this.workUserQuestionAnswerRepository.getUnansweredQuestionsForUser(user);
        return questions.stream().map(question -> {
            WorkQuestionTemplate template;
            if (question.getQuestion() instanceof BinaryWorkQuestion) {
            template = this.mapQuestion((BinaryWorkQuestion) question.getQuestion());
            } else if (question.getQuestion() instanceof DilemmaWorkQuestion) {
            template = this.mapQuestion((DilemmaWorkQuestion) question.getQuestion());
            } else if (question.getQuestion() instanceof FreeTextWorkQuestion) {
            template = this.mapQuestion((FreeTextWorkQuestion) question.getQuestion());
            } else if (question.getQuestion() instanceof GrowingWorkQuestion) {
            template = this.mapQuestion((GrowingWorkQuestion) question.getQuestion());
            } else if (question.getQuestion() instanceof MapWorkQuestion) {
            template = this.mapQuestion((MapWorkQuestion) question.getQuestion());
            } else if (question.getQuestion() instanceof RaterWorkQuestion) {
            template = this.mapQuestion((RaterWorkQuestion) question.getQuestion());
            } else if (question.getQuestion() instanceof SliderWorkQuestion) {
            template = this.mapQuestion((SliderWorkQuestion) question.getQuestion());
            } else if (question.getQuestion() instanceof SuperBinaryWorkQuestion) {
            template = this.mapQuestion((SuperBinaryWorkQuestion) question.getQuestion());
            } else {
            throw new BadRequestException("Invalid question type");
            }
            template.setId(question.getId());
            return template;
        }).collect(Collectors.toList());
    }

    @Override
    public UserAnalysisViewModel getUserAnalysis(long userId) {
        User user = this.userService.getUserById(userId);
        LocalDate today = LocalDate.now(ZoneId.systemDefault());
        LocalDateTime endDate = today.plusDays(1).atStartOfDay();
        LocalDateTime startDate = endDate.minusYears(1);

        UserAnalysisViewModel viewModel = new UserAnalysisViewModel();
        viewModel.setUserId(userId);
        viewModel.setToDate(endDate);
        viewModel.setFromDate(startDate);
        viewModel.setScores(this.workUserQuestionAnswerRepository.findByUserAndDateTimeAnsweredBetweenAndSkipped(user, startDate, endDate, false)
                .stream().map(workUserQuestionAnswer -> {
            UserScoreViewModel vm = new UserScoreViewModel();
            vm.setTags(workUserQuestionAnswer.getQuestion().getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(), questionTag.getTag())).collect(Collectors.toList()));
            vm.setRemark(workUserQuestionAnswer.getRemarks());
            vm.setAnswer( Math.round(workUserQuestionAnswer.getAnswer() * 100.0) / 100.0);
            vm.setQuestionId(workUserQuestionAnswer.getId());
            vm.setQuestion(workUserQuestionAnswer.getQuestion().getDisplayQuestion());
            vm.setDateTimeAdded(workUserQuestionAnswer.getDateTimeAdded());
            vm.setDateTimeAnswered(workUserQuestionAnswer.getDateTimeAnswered());
            return vm;
        }).collect(Collectors.toList()));
        return viewModel;
    }

    @Override
    public ApiResponse addComment(CommentViewModel viewModel) {
        WorkUserQuestionAnswer wuqa = this.getQuestionAnswer(viewModel.getQuestionId());
        wuqa.setRemarks(viewModel.getComment());
        wuqa.setAnswer(-1.0);
        wuqa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(wuqa);
        return new ApiResponse(true, "Comment noted!");
    }

    @Override
    public ApiResponse skipQuestion(long questionAnswerId) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(questionAnswerId);
        qa.setSkipped(true);
        qa.setAnswer(-1.0);
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Question marked as skipped!");
    }

    @Override
    public ApiResponse answerQuestion(BinaryWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(viewModel.getScore());
        qa.setRemarks(viewModel.getAnswers().stream().map(String::valueOf).collect(Collectors.joining("|")));
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    public ApiResponse answerQuestion(DilemmaWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(viewModel.getScore());
        qa.setRemarks(Integer.valueOf(viewModel.getAnswer()).toString());
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    public ApiResponse answerQuestion(FreeTextWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(-1.0);
        qa.setRemarks(viewModel.getAnswer());
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    public ApiResponse answerQuestion(GrowingWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(viewModel.getScore());
        qa.setRemarks(Integer.valueOf(viewModel.getAnswer()).toString());
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    public ApiResponse answerQuestion(MapWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(viewModel.getScore());
        qa.setRemarks("v:"+viewModel.getVerticalAnswer()+"|h:"+viewModel.getHorizontalAnswer());
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    public ApiResponse answerQuestion(RaterWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(-1.0);
        qa.setRemarks(String.join("|", viewModel.getAnswers()));
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    public ApiResponse answerQuestion(SliderWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(viewModel.getScore());
        qa.setRemarks(viewModel.getAnswers().stream().map(String::valueOf).collect(Collectors.joining("|")));
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    public ApiResponse answerQuestion(SuperBinaryWorkAnswerViewModel viewModel) {
        WorkUserQuestionAnswer qa = this.getQuestionAnswer(viewModel.getQuestionId());
        qa.setAnswer(viewModel.getScore());
        qa.setRemarks(Boolean.valueOf(viewModel.isAnswer()).toString());
        qa.setDateTimeAnswered(LocalDateTime.now());
        this.workUserQuestionAnswerRepository.saveAndFlush(qa);
        return new ApiResponse(true, "Answer noted!");
    }

    @Override
    @Transactional
    public ApiResponse addBinaryWorkQuestions(List<NewBinaryWorkQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Binary Work Questions added successfully!");
    }

    @Override
    @Transactional
    public ApiResponse addDilemmaWorkQuestions(List<NewDilemmaWorkQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Dilemma Work Questions added successfully!");
    }

    @Override
    @Transactional
    public ApiResponse addFreeTextWorkQuestions(List<NewFreeTextWorkQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Free Text Work Questions added successfully!");
    }

    @Override
    @Transactional
    public ApiResponse addGrowingWorkQuestions(List<NewGrowingWorkQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Growing Work Questions added successfully!");
    }

    @Override
    @Transactional
    public ApiResponse addMapWorkQuestions(List<NewMapWorkQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Map Work Questions added successfully!");
    }

    @Override
    @Transactional
    public ApiResponse addRaterWorkQuestions(List<NewRaterWorkQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Rater Work Questions added successfully!");
    }

    @Override
    @Transactional
    public ApiResponse addSliderWorkQuestions(List<NewSliderWorkQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Slider Work Questions added successfully!");
    }

    @Override
    @Transactional
    public ApiResponse addSuperBinaryWorkQuestions(List<NewSuperBinaryQuestion> viewModels) {
        List<WorkQuestion> questions = viewModels.stream().map(this::mapViewModel).collect(Collectors.toList());
        this.workQuestionRepository.saveAll(questions);
        return new ApiResponse(true, "Super Binary Work Questions added successfully!");
    }

    @Override
    public List<WorkQuestionTagViewModel> getAllTags() {
        Pageable pageable = PageRequest.of(0, 50, Sort.by("tag").ascending());
        return this.workQuestionTagRepository.findAll(pageable)
                .stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(), questionTag.getTag()))
                .collect(Collectors.toList());
    }

    @Override
    public List<WorkQuestion> getAvailableWorkQuestions() {
        return this.workQuestionRepository.findAll();
    }

    private BinaryWorkQuestion mapViewModel(NewBinaryWorkQuestion viewModel) {
        BinaryWorkQuestion question = new BinaryWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setStatements(String.join("|", viewModel.getStatements()));
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private DilemmaWorkQuestion mapViewModel(NewDilemmaWorkQuestion viewModel) {
        DilemmaWorkQuestion question = new DilemmaWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private FreeTextWorkQuestion mapViewModel(NewFreeTextWorkQuestion viewModel) {
        FreeTextWorkQuestion question = new FreeTextWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private GrowingWorkQuestion mapViewModel(NewGrowingWorkQuestion viewModel) {
        GrowingWorkQuestion question = new GrowingWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private MapWorkQuestion mapViewModel(NewMapWorkQuestion viewModel) {
        MapWorkQuestion question = new MapWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private RaterWorkQuestion mapViewModel(NewRaterWorkQuestion viewModel) {
        RaterWorkQuestion question = new RaterWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setPredefinedWords(String.join("|", viewModel.getPredefinedWords()));
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private SliderWorkQuestion mapViewModel(NewSliderWorkQuestion viewModel) {
        SliderWorkQuestion question = new SliderWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setStatements(String.join("|", viewModel.getStatements()));
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private SuperBinaryWorkQuestion mapViewModel(NewSuperBinaryQuestion viewModel) {
        SuperBinaryWorkQuestion question = new SuperBinaryWorkQuestion();
        BeanUtils.copyProperties(viewModel, question);
        question.setTags(viewModel.getTags().stream().map(this::getTag).collect(Collectors.toSet()));
        return question;
    }
    private BinaryWorkQuestionViewModel mapQuestion(BinaryWorkQuestion question) {
        BinaryWorkQuestionViewModel template = new BinaryWorkQuestionViewModel();
        BeanUtils.copyProperties(question, template);
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        template.setStatements(question.getListStatements());
        template.setType("binary");
        return template;
    }
    private DilemmaWorkQuestionViewModel mapQuestion(DilemmaWorkQuestion question) {
        DilemmaWorkQuestionViewModel template = new DilemmaWorkQuestionViewModel();
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        BeanUtils.copyProperties(question, template);
        template.setType("dilemma");
        return template;
    }
    private FreeTextWorkQuestionViewModel mapQuestion(FreeTextWorkQuestion question) {
        FreeTextWorkQuestionViewModel template = new FreeTextWorkQuestionViewModel();
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        BeanUtils.copyProperties(question, template);
        template.setType("free-text");
        return template;
    }
    private GrowingWorkQuestionViewModel mapQuestion(GrowingWorkQuestion question) {
        GrowingWorkQuestionViewModel template = new GrowingWorkQuestionViewModel();
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        BeanUtils.copyProperties(question, template);
        template.setType("growing");
        return template;
    }
    private MapWorkQuestionViewModel mapQuestion(MapWorkQuestion question) {
        MapWorkQuestionViewModel template = new MapWorkQuestionViewModel();
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        BeanUtils.copyProperties(question, template);
        template.setType("map");
        return template;
    }
    private RaterWorkQuestionViewModel mapQuestion(RaterWorkQuestion question) {
        RaterWorkQuestionViewModel template = new RaterWorkQuestionViewModel();
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        BeanUtils.copyProperties(question, template);
        template.setPredefinedWords(question.getPredefinedWords());
        template.setType("rater");
        return template;
    }
    private SuperBinaryWorkQuestionViewModel mapQuestion(SuperBinaryWorkQuestion question) {
        SuperBinaryWorkQuestionViewModel template = new SuperBinaryWorkQuestionViewModel();
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        BeanUtils.copyProperties(question, template);
        template.setType("super-binary");
        return template;
    }
    private SliderWorkQuestionViewModel mapQuestion(SliderWorkQuestion question) {
        SliderWorkQuestionViewModel template = new SliderWorkQuestionViewModel();
        template.setTags(question.getTags().stream().map(questionTag -> new WorkQuestionTagViewModel(questionTag.getId(),questionTag.getTag())).collect(Collectors.toList()));
        BeanUtils.copyProperties(question, template);
        template.setStatements(question.getListStatements());
        template.setType("slider");
        return template;
    }
    private WorkQuestionTag getTag(long id){
        System.out.println("searching tag "+id);
        return this.workQuestionTagRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question Tag", "ID", id));
    }
    private WorkUserQuestionAnswer getQuestionAnswer(long id) {
        return this.workUserQuestionAnswerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("WorkQuestionAnswer", "ID", id));
    }
}
