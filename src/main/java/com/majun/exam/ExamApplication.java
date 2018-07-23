package com.majun.exam;

import com.majun.exam.base.util.BaseMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.spring.annotation.MapperScan;

@RestController
@SpringBootApplication
@MapperScan(basePackages = "com.majun.exam.dao", markerInterface = BaseMapper.class)
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@RequestMapping("/")
	public ModelAndView index(){
		if(!SecurityUtils.getSubject().isAuthenticated()){
			return new ModelAndView("index");
		}
		return new ModelAndView("redirect:/list");
	}

}
