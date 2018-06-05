package com.majun;

import com.majun.exam.base.util.BaseMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.spring.annotation.MapperScan;

@RestController
@SpringBootApplication
@MapperScan(basePackages = "com.majun.exam.dao", markerInterface = BaseMapper.class)
public class ExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamApplication.class, args);
	}

	@RequestMapping("/")
	public String index(){
		return "test";
	}

}
