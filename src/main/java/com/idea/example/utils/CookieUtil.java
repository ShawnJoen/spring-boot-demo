package com.idea.example.utils;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Slf4j
public class CookieUtil {

    public static Cookie[] getCookies() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie[] c = request.getCookies();
        return c;
    }

    public static void showCookie() {
        Cookie[] c = getCookies();
        for (int i = 0; i < (c == null ? 0 : c.length); i++) {
            log.info("name: {}, value: {}", c[i].getName(), c[i].getValue());
        }
    }

    public static void saveCookie(Cookie cookie) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = attributes.getResponse();
        response.addCookie(cookie);
    }

    /**
     * 添加 cookie
     */
    public static void addCookie(String name, Object object) {
        try {
            String v = URLEncoder.encode(new Gson().toJson(object), "UTF-8");
            Cookie cookie = new Cookie(name, v);
            cookie.setPath("/");
            cookie.setMaxAge(Integer.MAX_VALUE);
            saveCookie(cookie);
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }

    /**
     * 获取 cookie
     */
    public static <T> T getCookie(String name, Class<T> clazz) {
        try {
            Cookie[] cookies = getCookies();
            String v = null;
            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {
                    v = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
            }
            if (v != null) {
                return new Gson().fromJson(v, clazz);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取 cookie
     */
    public static String getCookie(String name) {
        try {
            Cookie[] cookies = getCookies();
            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {
                    return URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                }
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
        return null;
    }

    /**
     * 删除 cookie
     */
    public static void removeCookie(String name) {
        try {
            Cookie[] cookies = getCookies();
            for (int i = 0; i < (cookies == null ? 0 : cookies.length); i++) {
                if ((name).equalsIgnoreCase(cookies[i].getName())) {
                    Cookie cookie = new Cookie(name, "");
                    cookie.setPath("/");
                    cookie.setMaxAge(0);
                    saveCookie(cookie);
                }
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }

}
