package com.majun.exam.dto;

import java.util.List;

/**
 * Created by majun on 2018/7/24.
 */
public class SaveAnswerDto {

    private List<AnswerDto> answers;

    public List<AnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDto> answers) {
        this.answers = answers;
    }

    public static class AnswerDto{

        private Integer questionId;

        private Integer optionId;

        public Integer getOptionId() {
            return optionId;
        }

        public void setOptionId(Integer optionId) {
            this.optionId = optionId;
        }

        public Integer getQuestionId() {
            return questionId;
        }

        public void setQuestionId(Integer questionId) {
            this.questionId = questionId;
        }
    }

}

