package com.idea.example.controller;

import com.idea.example.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/cookie")
public class CookieController {

    private static String name = "abc";

    @RequestMapping("/add")
    public String add() {
        final Long time = System.currentTimeMillis();
        CookieUtil.addCookie(name, time);

        log.info("add: {}", time);

        return "cookie";
    }

    @RequestMapping("/get")
    public String get() {
        final String time = CookieUtil.getCookie(name);

        log.info("get: {}", time);

        return "cookie";
    }

    @RequestMapping("/show")
    public String show() {
        CookieUtil.showCookie();

        return "cookie";
    }

    @RequestMapping("/remove")
    public String remove() {
        CookieUtil.removeCookie(name);

        log.info("remove: {}", name);

        return "cookie";
    }
}
