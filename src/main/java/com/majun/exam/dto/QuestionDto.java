package com.majun.exam.dto;

import com.majun.exam.base.util.CacheDataUtil;
import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;

import java.util.List;

/**
 * Created by majun on 2018/7/24.
 */
public class QuestionDto {

    private Question question;

    private List<Option> options;

    private Integer chooseOptionId;


    public QuestionDto(Question question,List<Option> options) {
        this.options = options;
        this.question = question;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Question getQuestion() {
        return question;
    }

    public String getDescription(){
        return question==null?null:question.getDescription();
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getChooseOptionId() {
        return chooseOptionId;
    }

    public void setChooseOptionId(Integer chooseOptionId) {
        this.chooseOptionId = chooseOptionId;
    }

    public boolean isRight(){
        if(chooseOptionId == null){
            return true;
        }
        Option rightOption = CacheDataUtil.getRightOption(question.getRowId());
        if(chooseOptionId == rightOption.getRowId()){
            return true;
        }
        return false;
    }

}
