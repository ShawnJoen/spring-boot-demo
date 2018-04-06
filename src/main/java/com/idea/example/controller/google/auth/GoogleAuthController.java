package com.idea.example.controller.google.auth;

import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Locale;

@Slf4j
@Controller
public class GoogleAuthController {
    @RequestMapping(value = "/googleAuth", method = RequestMethod.GET)
    public String googleAuth(Locale locale, Model model) {

        final GoogleAuthenticator gAuth = new GoogleAuthenticator();
        final GoogleAuthenticatorKey key = gAuth.createCredentials();
        final String secretKey = key.getKey();

        final String userId = "quanchunlin108@163.com";
        model.addAttribute("userId", userId);
        //otpauth://totp/quanchunlin108@163.com?secret=J7XC3LZ7H2F7RM7F&issuer=SHNCode
        model.addAttribute("qrcodeURL", this.getQRBarcodeURL(userId, secretKey));

        return "googleAuth";
    }

    public String getQRBarcodeURL(final String userId, final String secretKey) {
        String format = "otpauth://totp/%s?secret=%s&issuer=SHNCode";
        return String.format(format, userId, secretKey);
    }

}
