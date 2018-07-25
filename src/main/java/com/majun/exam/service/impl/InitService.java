package com.majun.exam.service.impl;

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
