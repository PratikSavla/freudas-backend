package com.freudas.dev.viewmodel.bigfive;

import com.freudas.dev.model.BigFiveTags;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class    BigFiveTraitsViewModel {
    private List<Map<BigFiveTags, Double>> month = new ArrayList<>();
    private List<Map<BigFiveTags, Double>> year = new ArrayList<>();

    public List<Map<BigFiveTags, Double>> getMonth() {
        return month;
    }

    public void setMonth(List<Map<BigFiveTags, Double>> month) {
        this.month = month;
    }

    public List<Map<BigFiveTags, Double>> getYear() {
        return year;
    }

    public void setYear(List<Map<BigFiveTags, Double>> year) {
        this.year = year;
    }
}
