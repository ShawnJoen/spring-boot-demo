package com.idea.example.service;

import com.google.common.collect.Lists;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;
import lombok.extern.slf4j.Slf4j;
import it.ozimov.springboot.mail.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UmsService {

    @Value("${spring.mail.username}")
    public String ADMIN_EMAIL;

    @Value("${base.url}")
    public String BASE_URL;

    private final EmailService emailService;

    @Autowired
    public UmsService(EmailService emailService) {
        this.emailService = emailService;
    }

    public MimeMessage sendEmail(String toEmail) throws UnsupportedEncodingException, CannotSendEmailException {
        final Email email = DefaultEmail.builder()
                .from(new InternetAddress(ADMIN_EMAIL, "From Shawn"))
                .to(Lists.newArrayList(new InternetAddress(toEmail, "to " + toEmail)))
                .subject("注册邮件")
                .body("")
                .encoding("UTF-8")
                .build();

        final Map<String, Object> model = new HashMap<>();
        model.put("confirm_url", BASE_URL);
        return emailService.send(email, "email/test_template", model);
    }

}
