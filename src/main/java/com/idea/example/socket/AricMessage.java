package com.idea.example.socket;

import lombok.ToString;

/**
 * 浏览器向服务端发送的消息
 */
@ToString
public class AricMessage {
    private String name;

    public String getName() {
        return name;
    }
}
