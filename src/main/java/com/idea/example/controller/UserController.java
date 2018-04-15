package com.idea.example.controller;

import com.idea.example.domain.Response;
import com.idea.example.enums.CodeEnum;
import com.idea.example.service.UmsService;
import com.idea.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;

@Slf4j
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

//http://127.0.0.1:8080/user/dashboard
    @RequestMapping(value="dashboard")
    public String dashboard() {
        return "user/dashboard";
    }
//http://127.0.0.1:8080/user/logout (退出登入) Spring Security内带
//http://127.0.0.1:8080/j_spring_security_check (请求登入验证) Spring Security内带
//http://127.0.0.1:8080/user/login
    @RequestMapping("login")
    public String login(@RequestParam(name = "msg", defaultValue = "none") String msg) {

        log.info("-------------login:{}", msg);

        return "user/login";
    }
//http://127.0.0.1:8080/user/register
    @RequestMapping("register")
    public String register(@RequestParam(name = "msg", defaultValue = "none") String msg) {

        log.info("-------------register:", msg);

        return "user/register";
    }
//http://127.0.0.1:8080/user/doRegister
    @RequestMapping("doRegister")
    public String doRegister(@RequestParam("email") String email, @RequestParam("pw") String pw,
                             @RequestParam("fingerprint") String fingerprint) {
        try {
            return userService.saveUser(email, pw, fingerprint);
        } catch (Exception e) {
            return "redirect:/user/register?msg=invalid";
        }
    }
    /*
    * TODO return JsonData
    * */
//    @RequestMapping("doRegister")
//    public @ResponseBody Response doRegister(@RequestParam("email") String email, @RequestParam("pw") String pw,
//                               @RequestParam("fingerprint") String fingerprint) {
//        CodeEnum codeEnum = userService.saveUser(email, pw, fingerprint);
//        return Response.builder()
//                .code(codeEnum.getCode())
//                .msg(codeEnum.getMessage())
//                .build();
//    }

    @Autowired
    UmsService umsService;
//http://127.0.0.1:8080/user/sendEmail?email=83007003@qq.com
    @RequestMapping("sendEmail")
    public @ResponseBody Response<MimeMessage> sendEmail(@RequestParam("email") String email) {

        log.info("-------------email:{}", email);
        try {
            umsService.sendEmail(email);
        } catch (Exception e) {
            log.info("-------------e:{}", e);
        }
        return Response.<MimeMessage>builder()
                .code(CodeEnum.SUCCESS.getCode())
                .msg(CodeEnum.SUCCESS.getMessage())
                //.data(umsService.sendEmail(email))
                .build();
    }
}
