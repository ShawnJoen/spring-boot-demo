package com.idea.example.controller;

import com.idea.example.socket.AricMessage;
import com.idea.example.socket.AricResponse;
import com.idea.example.socket.login.SocketSessionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/socket")
public class WebSocketController {

    @RequestMapping(value="/pingPong")
    public String pingPong() {
        return "socket/pingPong";
    }

    //URL:/ping 接收信息同时发送给订阅的浏览器
    @MessageMapping("ping")//当浏览器向服务端发送请求时 /ping地址 > .send("/ping"
    @SendTo("/topic/pong")//给订阅 /topic/pong的所有浏览器发送消息  > .subscribe("/topic/pong"
    public AricResponse say(AricMessage message) {

        log.info("服务器接收到消息: {}", message);
        return new AricResponse("Ping: " + message.getName());
    }

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;//通过simpMessagingTemplate向浏览器发送消息(点对点式)

    @Autowired
    SocketSessionRegistry webAgentSessionRegistry;//Session操作类

    @RequestMapping(value="/simpleChat")
    public String simpleChat() {
        return "socket/simpleChat";
    }

    @MessageMapping("sendChatMessage")
    public void sendChatMessage(AricMessage message,
                                MessageHeaders MessageHeaders,
                                @Header("destination") String destination,
                                @Headers Map<String, Object> headers,
                                @Payload String body) {
        //log.info("[test] MessageHeaders: {}", MessageHeaders);//[test] MessageHeaders: {simpMessageType=MESSAGE, stompCommand=SEND, nativeHeaders={destination=[/sendChatMessage], content-length=[12]}, simpSessionAttributes={}, simpHeartbeat=[J@3fd22a73, lookupDestination=/sendChatMessage, simpSessionId=foze2yis, simpDestination=/sendChatMessage}
        //log.info("[test] Header: {}", destination);///sendChatMessage
        //log.info("[test] Headers: {}", headers);//{simpMessageType=MESSAGE, stompCommand=SEND, nativeHeaders={destination=[/sendChatMessage], content-length=[12]}, simpSessionAttributes={}, simpHeartbeat=[J@3fd22a73, lookupDestination=/sendChatMessage, simpSessionId=foze2yis, simpDestination=/sendChatMessage}
        //log.info("[test] Payload: {}", body) ;//Payload: {"name":"2"}
        log.info("服务器接收消息 Message: {}", message);

        //给所有在线用户发消息
        List<String> keys = webAgentSessionRegistry.getAllSessionIds()
                .entrySet()
                .parallelStream()
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        keys.forEach( key -> {
            String sessionId = webAgentSessionRegistry.getSessionIds(key)
                            .parallelStream()
                            .findFirst()
                            .get();
            simpMessagingTemplate.convertAndSendToUser(sessionId,
                    "/simpleChat/chatAll",
                    new AricResponse("Send: " + message.getName()),
                    createHeaders(sessionId));

            log.info("服务器发送消息 sessionId: {}", sessionId);
        });
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
