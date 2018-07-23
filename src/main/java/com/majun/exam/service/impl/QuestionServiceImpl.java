package com.majun.exam.service.impl;

import com.majun.exam.dao.expand.QuestionExpandMapper;
import com.majun.exam.dto.QuestionDto;
import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import com.majun.exam.service.BaseService;
import com.majun.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by majun on 2018/7/24.
 */
@Service("questionService")
public class QuestionServiceImpl extends BaseService<Question> implements QuestionService {

    @Autowired
    private QuestionExpandMapper questionExpandMapper;

    @Override
    public List<QuestionDto> queryQuestions() {

        //查询题目
        List<Question> questions = questionExpandMapper.queryRandomQuestions();

        List<Integer> questionIds = questions.stream().map(question -> question.getRowId()).collect(Collectors.toList());

        //查询选项
        List<Option> options = questionExpandMapper.queryOptionsByQuestionIds(questionIds);

        //选项,题目map <questionId,Option>
        Map<Integer,List<Option>> questionIdOptionMap = new HashMap<>();
        for(Option option : options){
            Integer questionId = option.getQuestionId();
            List<Option> list = questionIdOptionMap.get(questionId);
            if(list == null){
                list = new ArrayList<>();
            }
            list.add(option);
            questionIdOptionMap.put(questionId,list);
        }

        List<QuestionDto> questionDtos = questions.stream().map(question -> {
            QuestionDto dto = new QuestionDto(question,questionIdOptionMap.get(question.getRowId()));
            return dto;
        }).collect(Collectors.toList());

        return questionDtos;
    }
}
