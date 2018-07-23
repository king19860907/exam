package com.majun.exam.dao.expand;

import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by majun on 2018/7/24.
 */
@Mapper
public interface QuestionExpandMapper {

    List<Question> queryRandomQuestions();

    List<Option> queryOptionsByQuestionIds(@Param("questionIds") List<Integer> questionIds);

}
