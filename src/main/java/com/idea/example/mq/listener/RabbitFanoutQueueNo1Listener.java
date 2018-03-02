package com.idea.example.mq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanoutQueue.No1")
public class RabbitFanoutQueueNo1Listener {

    @RabbitHandler
    public void onRecieve(String message) {

        System.out.println("Receiver (fanoutQueue.No1): " + message);
    }
}
