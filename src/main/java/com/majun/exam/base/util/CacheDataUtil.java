package com.majun.exam.base.util;

import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by majun on 2018/7/24.
 */
public class CacheDataUtil {

    public final static Map<Integer,Question> QUESTION_MAP = new HashMap<>();

    public final static List<Question> QUESTION_LIST = new ArrayList<>();

    public final static Map<Integer,List<Option>> OPTION_MAP = new HashMap<>();

    public static List<Option> getOptionsByQuestionId(Integer questionId){
        return OPTION_MAP.get(questionId);
    }

    public static Question getQuestionById(Integer id){
        return QUESTION_MAP.get(id);
    }

    public static List<Question> getQuestionsByIds(List<Integer> ids){
        List<Question> list = new ArrayList<>();
        for(Integer id : ids){
            list.add(getQuestionById(id));
        }
        return list;
    }

    public static Option getRightOption(Integer questionId){
        List<Option> options = getOptionsByQuestionId(questionId);
        if(CollectionUtils.isEmpty(options)){
            return null;
        }
        for(Option option : options){
            if(option.getAnswer()){
                return option;
            }
        }
        return null;
    }

    public static List<Question> getRandomQuestions(int size){
        List<Question> list = QUESTION_LIST;
        List<Question> randomList = new ArrayList<>(size);
        Set<Integer> numbers = getRandomNumber(size,list.size());
        numbers.forEach(number->randomList.add(list.get(number)));
        return randomList;
    }

    private static Set<Integer> getRandomNumber(int size,int range){
        Set<Integer> numbers = new LinkedHashSet<>();
        Random random = new Random();
        int i = 0;
        while (numbers.size()<size && i<1000000){
            numbers.add(random.nextInt(range));
            i++;
        }
        return numbers;
    }

    public static void main(String[] args) {
        System.out.println(getRandomNumber(50,100));
    }

}
