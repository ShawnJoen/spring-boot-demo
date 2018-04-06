package com.idea.example.controller.google.auth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Slf4j
@RestController
public class GoogleAuthRestController {

    @RequestMapping(value = "/googleAuth", method = RequestMethod.POST)
    public Map<String, String> googleAuth(@RequestParam("secretKey") String secretKey,
                                      @RequestParam("authCode") int authCode, Locale locale, Model model) {
        log.info("api googleAuth secretKey: {}, authCode: {}", secretKey, authCode);

        Map<String, String> map = new HashMap<>();

        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        boolean isCodeValid = gAuth.authorize(secretKey, authCode);

        if (isCodeValid) {
            map.put("message", "验证通过");
        } else {
            map.put("message", "验证失败, 验证码不正确或过期");
        }

        return map;
    }

}
