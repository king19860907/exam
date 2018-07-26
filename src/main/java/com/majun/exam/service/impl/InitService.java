package com.majun.exam.service.impl;

import com.majun.exam.base.dto.InfoDto;
import com.majun.exam.base.util.CacheDataUtil;
import com.majun.exam.dao.expand.QuestionExpandMapper;
import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by majun on 2018/7/24.
 */
@Service("initService")
public class InitService implements InitializingBean {

    @Autowired
    public QuestionExpandMapper questionExpandMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        initQuestions();
        initOptions();
    }

    public InfoDto init() throws Exception {
        afterPropertiesSet();
        Map<String,Integer> map = new HashMap<>();
        map.put("option_map",CacheDataUtil.OPTION_MAP.size());
        map.put("question_map",CacheDataUtil.QUESTION_MAP.size());
        map.put("question_list",CacheDataUtil.QUESTION_LIST.size());
        return InfoDto.defaultSuccess(map);
    }

    private void initQuestions(){
        Map<Integer,Question> map = CacheDataUtil.QUESTION_MAP;
        List<Question> list = CacheDataUtil.QUESTION_LIST;
        List<Question> questions = questionExpandMapper.queryAllQuestions();
        questions.forEach(question -> {
            map.put(question.getRowId(),question);
            list.add(question);
        });
    }

    private void initOptions(){
        //查询选项
        List<Option> options = questionExpandMapper.queryAllOption();

        //选项,题目map <questionId,Option>
        Map<Integer,List<Option>> map = CacheDataUtil.OPTION_MAP;
        for(Option option : options){
            Integer questionId = option.getQuestionId();
            List<Option> list = map.get(questionId);
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(option);
            map.put(questionId,list);
        }
    }


}
