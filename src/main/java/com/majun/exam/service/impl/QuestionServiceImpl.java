package com.majun.exam.service.impl;

import com.majun.exam.base.dto.InfoDto;
import com.majun.exam.base.util.CacheDataUtil;
import com.majun.exam.base.util.RequestUtils;
import com.majun.exam.dao.AnswerDetailMapper;
import com.majun.exam.dao.AnswerMapper;
import com.majun.exam.dao.expand.QuestionExpandMapper;
import com.majun.exam.dto.AnswerDetailDto;
import com.majun.exam.dto.AnswerDto;
import com.majun.exam.dto.QuestionDto;
import com.majun.exam.dto.SaveAnswerDto;
import com.majun.exam.pojo.Answer;
import com.majun.exam.pojo.AnswerDetail;
import com.majun.exam.pojo.Option;
import com.majun.exam.pojo.Question;
import com.majun.exam.service.BaseService;
import com.majun.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by majun on 2018/7/24.
 */
@Service("questionService")
public class QuestionServiceImpl extends BaseService<Question> implements QuestionService {

    @Autowired
    private QuestionExpandMapper questionExpandMapper;

    @Autowired
    private AnswerMapper answerMapper;

    @Autowired
    private AnswerDetailMapper answerDetailMapper;

    @Value("${question.num}")
    private int randomQuestionNum;

    @Override
    public List<QuestionDto> queryQuestions() {

        //查询题目
        List<Question> questions = CacheDataUtil.getRandomQuestions(randomQuestionNum);

        List<QuestionDto> questionDtos = questions.stream().map(question -> {
            QuestionDto dto = new QuestionDto(question, CacheDataUtil.getOptionsByQuestionId(question.getRowId()));
            return dto;
        }).collect(Collectors.toList());

        return questionDtos;
    }

    @Override
    public InfoDto saveAnswers(SaveAnswerDto saveAnswerDto) {
        List<SaveAnswerDto.AnswerDto> answerDtos = saveAnswerDto.getAnswers();

        List<AnswerDetail> details = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        Answer answer = new Answer();
        answer.setCreateTime(new Date());
        answer.setQuestionIdStr(null);
        answer.setScore(null);
        answer.setUserId(RequestUtils.getUser().getRowId());
        answerMapper.insert(answer);

        for(int i=0;i<answerDtos.size();i++){
            SaveAnswerDto.AnswerDto answerDto = answerDtos.get(i);
            Option rightOption = CacheDataUtil.getRightOption(answerDto.getQuestionId());
            //回答错误
            if(rightOption.getRowId().intValue() != answerDto.getOptionId().intValue()){
                AnswerDetail detail = new AnswerDetail();
                detail.setAnswerId(answer.getRowId());
                detail.setChooseAnswerId(answerDto.getOptionId());
                detail.setRightAnswerId(rightOption.getRowId());
                detail.setQuestionId(answerDto.getQuestionId());
                details.add(detail);
            }

            sb.append(answerDto.getQuestionId()).append(",");
        }

        if(!CollectionUtils.isEmpty(details)){
            answerDetailMapper.insertList(details);
        }

        //总分=总答题数量-错题的数量
        answer.setScore(answerDtos.size()-details.size());
        answer.setQuestionIdStr(sb.toString());
        answerMapper.updateByPrimaryKey(answer);

        return InfoDto.defaultSuccess(answer.getRowId());
    }

    @Override
    public List<AnswerDto> answers() {
        Integer userId = RequestUtils.getUser().getRowId();
        return questionExpandMapper.queryAnswers(userId);
    }

    @Override
    public AnswerDetailDto detail(Integer id) {
        Answer answer = answerMapper.selectByPrimaryKey(id);

        List<AnswerDetail> details = questionExpandMapper.queryAnswerDetails(id);
        Map<Integer,AnswerDetail> questionIdDetailMap = new HashMap<>();
        for(AnswerDetail detail : details){
            questionIdDetailMap.put(detail.getQuestionId(),detail);
        }

        String questionIdStr = answer.getQuestionIdStr();
        List<QuestionDto> questions = Arrays.asList(questionIdStr.split(",")).stream().filter(idStr->{
            return !StringUtils.isEmpty(idStr);
        }).map(idStr->{
            Integer questionId = Integer.parseInt(idStr);
            Question question = CacheDataUtil.getQuestionById(questionId);
            List<Option> options = CacheDataUtil.getOptionsByQuestionId(question.getRowId());
            QuestionDto questionDto = new QuestionDto(question,options);
            if(questionIdDetailMap.get(questionId)!=null){
                questionDto.setChooseOptionId(questionIdDetailMap.get(questionId).getChooseAnswerId());
            }
            return questionDto;
        }).collect(Collectors.toList());

        AnswerDetailDto detailDto = new AnswerDetailDto();
        detailDto.setAnswer(answer);
        detailDto.setQuestions(questions);
        return detailDto;
    }
}
