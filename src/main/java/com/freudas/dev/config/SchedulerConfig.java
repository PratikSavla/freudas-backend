package com.freudas.dev.config;

import com.freudas.dev.model.User;
import com.freudas.dev.model.WorkQuestion;
import com.freudas.dev.service.IBigFiveService;
import com.freudas.dev.service.IChatService;
import com.freudas.dev.service.IUserService;
import com.freudas.dev.service.IWorkQuestionService;
import com.freudas.dev.util.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    final private IWorkQuestionService workQuestionService;
    final private IUserService userService;
    final private IChatService chatService;
    final private IBigFiveService bigFiveService;

    public SchedulerConfig(IWorkQuestionService workQuestionService, IUserService userService, IChatService chatService, IBigFiveService bigFiveService) {
        this.workQuestionService = workQuestionService;
        this.userService = userService;
        this.chatService = chatService;
        this.bigFiveService = bigFiveService;
    }
    @Scheduled(fixedDelay = 7 * 86400000, initialDelay = 1000)
    public void addWeeklyBigFive() {
        this.userService.getAllUsers().forEach(this.bigFiveService::addQuestionsToUser);
    }

//    7 days = 7 * 86400000 milliseconds
    @Scheduled(fixedDelay = 7 * 86400000, initialDelay = 1000)
    public void scheduleQuestions() {
        List<WorkQuestion> availableQuestions = this.workQuestionService.getAvailableWorkQuestions();
        List<User> workUsers = this.userService.getAllWorkUsers();

        workUsers.forEach(user -> {
            int unansweredQues = this.workQuestionService.countUnansweredQuestions(user);
            if(unansweredQues > 10) {
                System.out.println("User with id "+user.getId()+" has passed question limit!");
            } else {
                this.getRandomElement(availableQuestions).forEach(workQuestion -> {
                    this.workQuestionService.addNewQuestionForUser(workQuestion, user);
                });
            }
        });
        System.out.println("Added new questions on: "+ ZonedDateTime.now());
    }
    //    7 days = 7 * 86400000 milliseconds
//        @Scheduled(fixedDelay = 86400000, initialDelay = 1000)
    public void doAnalysis() {
        LocalDateTime timeStamp = LocalDateTime.now();
        this.userService.getAllWorkUsers().forEach(user -> {
            this.chatService.doAnalysisFor7Days(user, timeStamp);
        });
    }
    private List<WorkQuestion> getRandomElement(List<WorkQuestion> list) {
        Random rand = new Random();
        List<WorkQuestion> newList = new ArrayList<>();
        for (int i = 0; i < Constants.NUM_DAILY_QUESTIONS; i++) {
            int randomIndex = rand.nextInt(list.size());
            newList.add(list.get(randomIndex));
            list.remove(randomIndex);
        }
        return newList;
    }

}
