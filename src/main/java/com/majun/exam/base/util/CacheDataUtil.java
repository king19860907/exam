package com.majun.exam.base.util;

import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;

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

}
