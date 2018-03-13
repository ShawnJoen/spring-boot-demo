package com.idea.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.idea.example"})//可以同时加多个包配置环境
@EnableTransactionManagement//开启事物环境 然后 Service方法 添加注解 @Transactional
@MapperScan("com.idea.example.repository.dao")//指定dao接口包进行扫描
@EnableScheduling//开启这个module(项目)定时任务,在这个module下方法上加@Scheduled直接定时执行
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
