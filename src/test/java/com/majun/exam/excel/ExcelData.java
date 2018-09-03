package com.majun.exam.excel;

import com.majun.exam.dto.QuestionDto;
import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by majun on 2018/7/25.
 */
public class ExcelData {

    private String index;

    private String question;

    private String optionA;

    private String optionB;

    private String optionC;

    private String rightAnswer;

    public QuestionDto toQuestion(){
        Question q = new Question();
        q.setCreateTime(new Date());
        q.setDeleteFlag(false);
        q.setDescription(question);
        q.setRowId(Integer.parseInt(index));

        List<Option> options = new ArrayList<>();
        List<String> strs = getOptions();
        for(int i=1;i<=strs.size();i++){
            String str = strs.get(i-1);
            Option o = new Option();
            o.setCreateTime(new Date());
            o.setDeleteFlag(false);
            o.setDescription(str);
            o.setQuestionId(Integer.parseInt(index));
            o.setAnswer(i==getRightOption());
            options.add(o);
        }

        return new QuestionDto(q,options);
    }

    private Option creatOption(String str){
        Option option = new Option();
        option.setCreateTime(new Date());
        option.setDeleteFlag(false);
        option.setDescription(str);
        option.setQuestionId(Integer.parseInt(index));
        option.setAnswer(false);
        return option;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<String> getOptions(){
        List<String> list = new ArrayList<>();
        list.add(optionA);
        list.add(optionB);
        list.add(optionC);
        return list;
    }

    public Integer getRightOption(){
        if(isEmpty(question)){
            System.out.println("题干不存在:"+this);
            return null;
        }
        if(isEmpty(optionA)){
            System.out.println("选项A不存在:"+this);
            return null;
        }
        if(isEmpty(optionA)){
            System.out.println("选项B不存在:"+this);
            return null;
        }
        if(isEmpty(optionA)){
            System.out.println("选项C不存在:"+this);
            return null;
        }
        if(isEmpty(rightAnswer)){
            System.out.println("答案不存在:"+this);
            return null;
        }
        if(question.equals("题干")){
            System.out.println("该行为说明行:"+this);
            return null;
        }
        /*if(!optionA.trim().equals(rightAnswer)
                && !optionB.trim().equals(rightAnswer)
                && !optionC.trim().equals(rightAnswer)
                ){
            System.out.println("任何选项与答案都不符合:"+this);
            return  false;
        }*/
        if(isNumber(rightAnswer.trim())){
            double answer = Double.parseDouble(rightAnswer);
            String str = String.valueOf(answer);
            int num = str.split("\\.")[1].length();
            double a = getDouble(optionA,num);
            double b = getDouble(optionB,num);
            double c = getDouble(optionC,num);
            if(a == answer){
                return 1;
            }
            if(b == answer){
                return 2;
            }
            if(c == answer){
                return 3;
            }

        }else if("A".equals(rightAnswer.toUpperCase())){
            return 1;
        }else if("B".equals(rightAnswer.toUpperCase())){
            return 2;
        }else if("C".equals(rightAnswer.toUpperCase())){
            return 1;
        }else{
           if(optionA.trim().equals(rightAnswer.trim())){
               return 1;
           }
            if(optionB.trim().equals(rightAnswer.trim())){
                return 2;
            }
            if(optionC.trim().equals(rightAnswer.trim())){
                return 3;
            }
        }

        System.out.println("任何选项与答案都不符合:"+this);
        return null;
    }

    private boolean isEmpty(String str){
        if(StringUtils.isEmpty(str)){
            return true;
        }else{
            return str.trim().equals("null");
        }
    }

    private double getDouble(String str){
        try{
            double value = Double.parseDouble(str);
            return value;
        }catch (Exception e){
            return -11111111111d;
        }
    }

    private double getDouble(String str,int num){
        try{
            BigDecimal b = new BigDecimal(str).setScale(num,BigDecimal.ROUND_HALF_UP);
            return b.doubleValue();
        }catch (Exception e){
            return -11111111111d;
        }
    }

    private boolean isNumber(String str){
        try{
            Double.parseDouble(str);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "index='" + index + '\'' +
                ", question='" + question + '\'' +
                ", optionA='" + optionA + '\'' +
                ", optionB='" + optionB + '\'' +
                ", optionC='" + optionC + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                '}';
    }
}
