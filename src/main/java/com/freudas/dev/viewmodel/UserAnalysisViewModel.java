package com.freudas.dev.viewmodel;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAnalysisViewModel {
    private long userId;
    private List<UserScoreViewModel> scores;
    private LocalDateTime toDate;
    private LocalDateTime fromDate;

    public LocalDateTime getToDate() {
        return toDate;
    }

    public void setToDate(LocalDateTime toDate) {
        this.toDate = toDate;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public List<UserScoreViewModel> getScores() {
        return scores;
    }

    public void setScores(List<UserScoreViewModel> scores) {
        this.scores = scores;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
