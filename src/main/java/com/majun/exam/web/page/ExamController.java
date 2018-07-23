package com.majun.exam.web.page;

import com.majun.exam.base.util.RequestUtils;
import com.majun.exam.dto.QuestionDto;
import com.majun.exam.pojo.User;
import com.majun.exam.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

/**
 * Created by majun on 2018/7/23.
 */
@Controller
public class ExamController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(){
        User user = RequestUtils.getUser();
        return new ModelAndView("list");
    }

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public ModelAndView test(){
        List<QuestionDto> questions = questionService.queryQuestions();
        return new ModelAndView("test","questions",questions);
    }

}
