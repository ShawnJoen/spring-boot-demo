package com.idea.example.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${spring.message.queue.exchange.topicNo1Exchange}")
    private String topicNo1Exchange;

    @Value("${spring.message.queue.exchange.fanoutNo1Exchange}")
    private String fanoutNo1Exchange;
    /*
    * 单生产者和单消费者: 生产者
    * */
    public void sendMessageNoExchange(String message) {
        //第一个参数:设定Queue方法名称 内部指定(Queue Key)实现
        //第二个参数:消息
        this.rabbitTemplate.convertAndSend("queue.No1", message);

        System.out.println("-----------单生产者和单消费者 发送消息:" + message);
    }
    /*
    * 发布/订阅: 发布 转发器为 Topic
    * */
    public void sendMessageUsingTopicNo1Exchange(String message) {
        //第一个参数:设定转换器方法名称 内部指定(Exchange Key)实现Exchange
        //第二个参数:路由KEY
        //第三个参数:消息
        this.rabbitTemplate.convertAndSend(topicNo1Exchange, "topicQueue.No1", message);

        System.out.println("-----------发布 转发器为 topicQueue.No1 发送消息:" + message);
    }
    public void sendMessageUsingTopicStartExchange(String message) {
        //第一个参数:设定转换器方法名称 内部指定(Exchange Key)实现Exchange
        //第二个参数:路由KEY
        //第三个参数:消息
        this.rabbitTemplate.convertAndSend(topicNo1Exchange, "topicQueue.No2", message);

        System.out.println("-----------发布 转发器为 topicQueue.No2 发送消息:" + message);
    }
    /*
    * 发布/订阅: 发布 转发器为 Fanout
    * */
    public void sendMessageUsingFanoutNo1Exchange(String message) {
        //第一个参数:设定转换器方法名称 内部指定(Exchange Key)实现Exchange
        //第二个参数:路由KEY
        //第三个参数:消息
        this.rabbitTemplate.convertAndSend(fanoutNo1Exchange, "xxxxxxx", message);

        System.out.println("-----------发布 转发器为 Fanout xxxxxxx 发送消息:" + message);
    }

}
