package com.majun.exam.service;

import com.majun.exam.base.dto.InfoDto;
import com.majun.exam.dto.AnswerDetailDto;
import com.majun.exam.dto.AnswerDto;
import com.majun.exam.dto.QuestionDto;
import com.majun.exam.dto.SaveAnswerDto;
import com.majun.exam.pojo.Question;

import java.util.List;

/**
 * Created by majun on 2018/7/24.
 */
public interface QuestionService extends IService<Question>   {

    List<QuestionDto> queryQuestions();

    InfoDto saveAnswers(SaveAnswerDto saveAnswerDto);

    List<AnswerDto> answers();

    AnswerDetailDto detail(Integer id);

}
