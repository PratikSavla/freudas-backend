package com.freudas.dev.util;

import com.freudas.dev.model.WorkQuestionTag;
import com.freudas.dev.model.WorkUserQuestionAnswer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisUtils {
    public static Map<Long, TagScoreDetails> getAnalysis(List<WorkQuestionTag> tags, List<WorkUserQuestionAnswer> answers) {
        Map<Long, TagScoreDetails> tagMap = new HashMap<>();
        tags.forEach(questionTag -> tagMap.put(questionTag.getId(), new TagScoreDetails(questionTag.getTag())));
        answers.forEach(workUserQuestionAnswer -> workUserQuestionAnswer.getQuestion().getTags().forEach(questionTag -> tagMap.get(questionTag.getId()).addScore(workUserQuestionAnswer.getAnswer())));
        for(long id: tagMap.keySet()) {
            TagScoreDetails tsd = tagMap.get(id);
            tsd.setAvg(tsd.getScore()/(tsd.getCount()>0? tsd.getCount():1.0));
            tagMap.put(id, tsd);
        }
        return tagMap;
    }
}

