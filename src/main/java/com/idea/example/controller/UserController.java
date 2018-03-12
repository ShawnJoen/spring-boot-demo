package com.idea.example.controller;

import com.idea.example.domain.Response;
import com.idea.example.enums.CodeEnum;
import com.idea.example.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;
//    @PostMapping("/getAll")
//    public Response<ConnectDTO.ResGetAll> getAll(@ModelAttribute User user, @Valid @RequestBody ConnectDTO.ReqGetAll request) {
//        return Response.<ConnectDTO.ResGetAll>builder()
//                .code(CodeEnum.SUCCESS.getCode())
//                .msg(CodeEnum.SUCCESS.getMessage())
//                .data(botConnectSettingService.getAll(user, request))
//                .build();
/*    @RequestMapping("/logout")
    public ModelAndView logout() {
        ModelAndView mvn = new ModelAndView("landing/logout");
        return mvn;
    }
    @RequestMapping("/emailConfirm")
    public ModelAndView emailConfirm(@RequestParam("hash") String hash, @RequestParam("code") String code) {
        ModelAndView mvn = new ModelAndView("common/emailConfirm");
        User.ResEmailConfirm resEmailConfirm = userService.emailConfirm(hash, code);
        mvn.addObject("title", resEmailConfirm.getTitle());
        mvn.addObject("msg", resEmailConfirm.getMsg());
        mvn.addObject("url", resEmailConfirm.getUrl());
        mvn.addObject("urlTitle", resEmailConfirm.getUrlTitle());
        return mvn;
    }POST http://127.0.0.1:8080/j_spring_security_check HTTP/1.1
* */

//http://127.0.0.1:8080/user/dashboard
    @RequestMapping(value="dashboard")
    public String dashboard() {
        return "user/dashboard";
    }
//http://127.0.0.1:8080/user/login
    @RequestMapping("/login")
    public String login() {

        return "user/login";
    }
//http://127.0.0.1:8080/user/register
    @RequestMapping("/register")
    public String register() {

        return "user/register";
    }
//http://127.0.0.1:8080/user/doRegister
    @RequestMapping("/doRegister")
    public @ResponseBody Response doRegister(@RequestParam("email") String email, @RequestParam("pw") String pw,
                               @RequestParam("fingerprint") String fingerprint) {

        CodeEnum codeEnum = userService.saveUser(email, pw, fingerprint);

        return Response.builder()
                .code(codeEnum.getCode())
                .msg(codeEnum.getMessage())
                .build();
    }
}
