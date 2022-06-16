package com.freudas.dev.viewmodel;

import com.freudas.dev.util.TagScoreDetails;

import java.time.LocalDateTime;
import java.util.Map;

public class ProviderAnalysisViewModel {

    private Map<Long, TagScoreDetails> analysis;
    private LocalDateTime toDate;
    private LocalDateTime fromDate;

    public ProviderAnalysisViewModel(Map<Long, TagScoreDetails> analysis, LocalDateTime toDate, LocalDateTime fromDate) {
        this.analysis = analysis;
        this.toDate = toDate;
        this.fromDate = fromDate;
    }

    public Map<Long, TagScoreDetails> getAnalysis() {
        return analysis;
    }

    public void setAnalysis(Map<Long, TagScoreDetails> analysis) {
        this.analysis = analysis;
    }

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
}
