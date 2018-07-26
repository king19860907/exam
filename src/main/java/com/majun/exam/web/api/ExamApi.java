package com.majun.exam.web.api;

import com.majun.exam.base.dto.InfoDto;
import com.majun.exam.dto.SaveAnswerDto;
import com.majun.exam.service.QuestionService;
import com.majun.exam.service.UserService;
import com.majun.exam.service.impl.InitService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by majun on 04/06/2018.
 */
@RestController
@RequestMapping("/api")
public class ExamApi {

    @Resource
    private QuestionService questionService;

    @Resource
    private InitService initService;

    @RequestMapping(value = "/answers",method = RequestMethod.POST)
    public InfoDto saveAnswer(@RequestBody SaveAnswerDto data){
        return questionService.saveAnswers(data);
    }

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public InfoDto init() throws Exception {
        return initService.init();
    }

}
