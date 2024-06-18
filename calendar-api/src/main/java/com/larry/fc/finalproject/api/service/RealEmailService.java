package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.controller.api.BatchController;
import com.larry.fc.finalproject.api.dto.EngagementEmailStuff;
import com.larry.fc.finalproject.core.domain.entity.Engagement;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RealEmailService implements EmailService{
    private final JavaMailSender emailSender;
    private final SpringTemplateEngine templateEngine;

    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        final MimeMessagePreparator preparator = message -> {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(stuff.getToEmail());
            helper.setSubject(stuff.getSubject());
            helper.setText(
                    templateEngine.process("engagement-email",
                            new Context(Locale.KOREAN, stuff.getProps())),true
            );
        };
        emailSender.send(preparator);
    }

    @Override
    public void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq) {
        System.out.println("send alarm" + sendMailBatchReq.toString());
    }
}
