package com.freudas.dev.service;

import com.freudas.dev.exception.ResourceNotFoundException;
import com.freudas.dev.model.BigFiveAnswer;
import com.freudas.dev.model.BigFiveTags;
import com.freudas.dev.model.User;
import com.freudas.dev.repository.BigFiveAnswerRepository;
import com.freudas.dev.repository.BigFiveQuestionRepository;
import com.freudas.dev.repository.UserRepository;
import com.freudas.dev.viewmodel.ApiResponse;
import com.freudas.dev.viewmodel.bigfive.BigFiveQuestionViewModel;
import com.freudas.dev.viewmodel.bigfive.BigFiveTraitsViewModel;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class BigFiveService implements IBigFiveService{

    final private BigFiveAnswerRepository answerRepository;
    final private BigFiveQuestionRepository questionRepository;
    final private UserRepository userRepository;

    public BigFiveService(BigFiveAnswerRepository answerRepository, BigFiveQuestionRepository questionRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addQuestionsToUser(User user) {
        if (this.getUnansweredQuestions(user).size() > 0) return;
        this.answerRepository.saveAll(this.questionRepository.findAll()
                .stream().map(bigFiveQuestion -> new BigFiveAnswer(user, bigFiveQuestion))
                .collect(Collectors.toList()));
    }

    @Override
    public List<BigFiveQuestionViewModel> getUnansweredQuestions(long id) {
        return this.getUnansweredQuestions(this.userRepository.getById(id));
    }

    @Override
    public ApiResponse answerQuestion(long answerId, int score) {
        BigFiveAnswer answer = this.answerRepository.findById(answerId)
                .orElseThrow(() -> new ResourceNotFoundException("Big Five Answer", "ID", answerId));
        answer.setDateTimeAnswered(LocalDateTime.now());
        answer.setAnswer(score);
        this.answerRepository.saveAndFlush(answer);
        return new ApiResponse(true,"Question Answered Successfully!");
    }
    private Map<BigFiveTags, Double> getBigFiveScores(LocalDateTime start, LocalDateTime end, User user) {
        Map<BigFiveTags, Double> counts = new HashMap<>();
        counts.put(BigFiveTags.agreeablenes, 0.0);
        counts.put(BigFiveTags.conscientiousness, 0.0);
        counts.put(BigFiveTags.extraversion, 0.0);
        counts.put(BigFiveTags.neuroticism, 0.0);
        counts.put(BigFiveTags.openness, 0.0);
        Map<BigFiveTags, Double> score = new HashMap<>();
        counts.put(BigFiveTags.agreeablenes, 0.0);
        counts.put(BigFiveTags.conscientiousness, 0.0);
        counts.put(BigFiveTags.extraversion, 0.0);
        counts.put(BigFiveTags.neuroticism, 0.0);
        counts.put(BigFiveTags.openness, 0.0);
        this.answerRepository.getAllAnsweredQuestionsForUser(user, start, end).forEach(bigFiveAnswer -> {
            BigFiveTags tag = bigFiveAnswer.getQuestion().getTag();
            score.put(tag,  bigFiveAnswer.getScore() + (score.get(tag)!=null?score.get(tag) : 0));
            counts.put(tag, counts.get(tag) + 1);
        });
        score.put(BigFiveTags.agreeablenes, counts.get(BigFiveTags.agreeablenes) > 1 ? score.get(BigFiveTags.agreeablenes) / counts.get(BigFiveTags.agreeablenes) * 20: 0);
        score.put(BigFiveTags.conscientiousness, counts.get(BigFiveTags.conscientiousness) > 1 ? score.get(BigFiveTags.conscientiousness) / counts.get(BigFiveTags.conscientiousness)  * 20: 0);
        score.put(BigFiveTags.extraversion, counts.get(BigFiveTags.extraversion) > 1 ? score.get(BigFiveTags.extraversion) / counts.get(BigFiveTags.extraversion)  * 20: 0);
        score.put(BigFiveTags.neuroticism, counts.get(BigFiveTags.neuroticism) > 1 ? score.get(BigFiveTags.neuroticism) / counts.get(BigFiveTags.neuroticism)  * 20: 0);
        score.put(BigFiveTags.openness, counts.get(BigFiveTags.openness) > 1 ? score.get(BigFiveTags.openness) / counts.get(BigFiveTags.openness)  * 20: 0);
        return score;
    }

    @Override
    public BigFiveTraitsViewModel getTraits(long userId) {
        User user = this.userRepository.getById(userId);
        BigFiveTraitsViewModel vm = new BigFiveTraitsViewModel();
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(7);

        for (int i=0;i<4;i++) {
            vm.getMonth().add(this.getBigFiveScores(start, end, user));
            end = start.minusNanos(1);
            start = start.minusDays(7);
        }
        end = LocalDateTime.now();
        start = end.minusMonths(1);
        for (int i=0;i<4;i++) {
            vm.getYear().add(this.getBigFiveScores(start, end, user));
            end = start.minusNanos(1);
            start = start.minusMonths(1);
        }
        return vm;
    }

    private List<BigFiveQuestionViewModel> getUnansweredQuestions(User user) {
        return this.answerRepository.getAllUnansweredQuestionsForUser(user)
                .stream().map(BigFiveQuestionViewModel::new)
                .collect(Collectors.toList());
    }
}
