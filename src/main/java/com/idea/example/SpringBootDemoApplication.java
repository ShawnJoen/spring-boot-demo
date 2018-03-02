package com.idea.example;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootDemoApplication {

	/*
	* Rabbit Key 重复生成会出错/更改需要 删除
	* 	http://localhost:15672
	* */
	@Value("${spring.message.queue.exchange.topicNo1Exchange}")
	private String topicNo1Exchange;

	@Value("${spring.message.queue.exchange.fanoutNo1Exchange}")
	private String fanoutNo1Exchange;

	//===============未使用 Exchange 生产者/消费者 消息队列
	@Bean
	public Queue mqNo1Queue() {
		return new Queue("queue.No1", true); //true表示持久化该队列
	}
	//===============使用 Exchange转发器 topic 发布/订阅 消息队列
	@Bean
	public Queue topicNo1Queue() {
		return new Queue("topicQueue.No1", false);
	}
	@Bean
	public Queue topicNo2Queue() {
		return new Queue("topicQueue.No2", false);
	}
	//===============使用 Exchange转发器 fanout 发布/订阅 消息队列
	@Bean
	public Queue fanoutNo1Queue() {
		return new Queue("fanoutQueue.No1", false);
	}
	//===============定义 topic 转发器生成(方法)
	@Bean
	TopicExchange topic1Exchange() {
		return new TopicExchange(topicNo1Exchange);
	}
	//===============定义 fanout 转发器生成(方法)
	@Bean
	FanoutExchange fanoutExchange() {
		return new FanoutExchange(fanoutNo1Exchange);
	}
	//===============绑定 匹配的 Topic转发器
	//将队列topic.tNo1与 exchange绑定 binding_key 完全匹配 topic.tNo1
	/*
	* Topic的binding匹配规则:
	* 1. with(没有通配符的前提) bind参数和with参数必须一起匹配就能 监听到消息
	* 2. with(有通配符的前提) bind参数匹配 或 被通配符匹配（只要匹配其中一个就能 监听到消息）
	* */
	@Bean
	Binding bindingTopicNo1ExchangeMessage() {
		return BindingBuilder.bind(topicNo1Queue()).to(topic1Exchange()).with("topicQueue.No1");//topic,direct转发器可以指定 路由KEY
	}
	//将队列topic.tNo1与 exchange绑定 binding_key 模糊匹配 topic.#
	@Bean
	Binding bindingTopicStartExchangeMessage() {
		return BindingBuilder.bind(topicNo2Queue()).to(topic1Exchange()).with("topicQueue.*");
	}
	//给转发器 fanout exchange绑定 无需匹配binding_key, 只需匹配exchange
	@Bean
	Binding bindingFanoutExchangeA() {
		return BindingBuilder.bind(fanoutNo1Queue()).to(fanoutExchange());//fanout转发器无 路由KEY
	}

	public static void main(String[] args) {

		SpringApplication.run(SpringBootDemoApplication.class, args);
	}

}
