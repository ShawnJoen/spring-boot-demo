package com.idea.example.controller;

import com.idea.example.mq.RabbitMqSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Date;

/*lombok
* @Slf4j 内置日志门面 可以直接通过变量log使用
* */
@Slf4j
@Controller
@RequestMapping("/")
public class RabbitMqController {

    @Autowired
    private RabbitMqSender rabbitMqSender;
//http://localhost:8080/mq/rabbit/sendMessageNoExchange
    @RequestMapping(value = "mq/rabbit/sendMessageNoExchange", method = RequestMethod.GET)
    public void sendMessageNoExchange() {

        String message = "-- send Message No Exchange --" + new Date().toString();
        rabbitMqSender.sendMessageNoExchange(message);
    }
//http://localhost:8080/mq/rabbit/sendMessageUsingTopicNo1Exchange
    @RequestMapping(value = "mq/rabbit/sendMessageUsingTopicNo1Exchange", method = RequestMethod.GET)
    public void sendMessageUsingTopicNo1Exchange() {

        String message = "-- send Message Using Topic No1 Exchange --" + new Date().toString();
        rabbitMqSender.sendMessageUsingTopicNo1Exchange(message);
    }
//http://localhost:8080/mq/rabbit/sendMessageUsingTopicStartExchange
    @RequestMapping(value = "mq/rabbit/sendMessageUsingTopicStartExchange", method = RequestMethod.GET)
    public void sendMessageUsingTopicStartExchange() {

        String message = "-- send Message Using Topic No2 Exchange --" + new Date().toString();
        rabbitMqSender.sendMessageUsingTopicStartExchange(message);
    }
//http://localhost:8080/mq/rabbit/sendMessageUsingFanoutNo1Exchange
    @RequestMapping(value = "mq/rabbit/sendMessageUsingFanoutNo1Exchange", method = RequestMethod.GET)
    public void sendMessageUsingFanoutNo1Exchange() {

        String message = "-- send Message Using Fanout No1 Exchange --" + new Date().toString();
        rabbitMqSender.sendMessageUsingFanoutNo1Exchange(message);
    }

}
