package com.majun.exam.service;

import com.majun.exam.dto.QuestionDto;
import com.majun.exam.pojo.Question;
import java.util.List;

/**
 * Created by majun on 2018/7/24.
 */
public interface QuestionService extends IService<Question>   {

    List<QuestionDto> queryQuestions();

}
