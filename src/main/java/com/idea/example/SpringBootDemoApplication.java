package com.idea.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.idea.example.repository.dao")//指定dao接口包进行扫描
@EnableScheduling//开启这个module(项目)定时任务,在这个module下方法上加@Scheduled直接定时执行
public class SpringBootDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
