package com.majun.exam.base.util;

import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

/**
 * Created by majun on 2018/7/24.
 */
public class CacheDataUtil {

    public final static Map<Integer,Question> QUESTION_MAP = new HashMap<>();

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

}
