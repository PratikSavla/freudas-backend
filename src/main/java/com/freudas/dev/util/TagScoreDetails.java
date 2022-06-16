package com.freudas.dev.util;

public class TagScoreDetails {
    private Double score = 0.0;
    private Integer count = 0;
    private Double avg = 0.0;

    public void setScore(Double score) {
        this.score = score;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    private final String tag;

    public TagScoreDetails(String tag) {
        this.tag = tag;
    }

    public void addScore(Double score) {
        this.count++;
        this.score += score;
    }

    public Integer getCount() {
        return count;
    }

    public String getTag() {
        return tag;
    }

    public Double getScore() {
        return score;
    }
}
