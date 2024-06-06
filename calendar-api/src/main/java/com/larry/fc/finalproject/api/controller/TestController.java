package com.larry.fc.finalproject.api.controller;
import lombok.RequiredArgsConstructor;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Larry
 */
@Controller
@RequiredArgsConstructor
public class TestController {
    private final JavaMailSender emailSender;

    @GetMapping("/api/mail")
    public @ResponseBody
    void sendMail() {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo("nenechick9692@mgail.mail");
            helper.setSubject("제목입니당~~");
            helper.setText("테슷흐 메일");
        };
        emailSender.send(preparator);
    }

    @GetMapping("test/template")
    public String testTemplate(Model model){
        final Map<String, Object> props = new HashMap<>();
        props.put("title", "타이틀");
        props.put("subject", "타이틀");
        props.put("calendar", "test@mail.com");
        props.put("period", "언제부터 언제까지");
        props.put("attendees", List.of("test3@mail.com", "test2@mail.com","test1@mail.com"));
        props.put("acceptUrl", "http://localhost:8080");
        props.put("rejectUrl", "http://localhost:8080");
        model.addAllAttributes(props);
        return "engagement-email";


    }

}