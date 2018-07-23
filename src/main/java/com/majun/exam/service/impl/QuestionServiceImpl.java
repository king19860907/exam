package com.majun.exam.service.impl;

import com.majun.exam.base.util.CacheDataUtil;
import com.majun.exam.dao.expand.QuestionExpandMapper;
import com.majun.exam.dto.QuestionDto;
import com.majun.exam.pojo.Question;
import com.majun.exam.service.BaseService;
import com.majun.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        List<QuestionDto> questionDtos = questions.stream().map(question -> {
            QuestionDto dto = new QuestionDto(question, CacheDataUtil.getOptionsByQuestionId(question.getRowId()));
            return dto;
        }).collect(Collectors.toList());

        return questionDtos;
    }
}
