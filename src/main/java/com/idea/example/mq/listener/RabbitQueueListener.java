package com.idea.example.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue.No1")
public class RabbitQueueListener {

    @RabbitHandler
    public void onRecieve(String message) {

        System.out.println("Receiver (queue.No1): " + message);
    }
}
