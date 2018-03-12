package com.idea.example.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {
    private String code;
    private String msg;
    private String debug;
    private T data;
}
