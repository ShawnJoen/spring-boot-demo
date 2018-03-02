package com.idea.example.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitTopicQueueListener {

    @RabbitListener(queues = "topicQueue.No1")
    public void onRecieve1(String message) {

        System.out.println("Receiver (topicQueue.No1): " + message);
    }

    @RabbitListener(queues = "topicQueue.No2")
    public void onRecieve2(String message) {

        System.out.println("Receiver (topicQueue.No2): " + message);
    }
}
