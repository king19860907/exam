package com.majun.exam.dto;

import com.majun.exam.pojo.Answer;

import java.util.List;

/**
 * Created by majun on 2018/7/24.
 */
public class AnswerDetailDto {

    private Answer answer;

    private List<QuestionDto> questions;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<QuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionDto> questions) {
        this.questions = questions;
    }
}
