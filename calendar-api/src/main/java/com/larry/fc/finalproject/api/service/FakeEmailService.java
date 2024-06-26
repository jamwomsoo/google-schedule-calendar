package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.controller.api.BatchController;
import com.larry.fc.finalproject.api.dto.EngagementEmailStuff;
import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.entity.Share;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class FakeEmailService implements  EmailService{
    @Override
    public void sendEngagement(EngagementEmailStuff stuff) {
        System.out.println("send the email to : " + stuff.getSubject());
    }

    @Override
    public void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq) {
        System.out.println("Send alarm." + sendMailBatchReq.toString());
    }

    @Override
    public void sendShareRequestMail(String email, String name, Share.Direction direction) {
        System.out.println("Send share request mail. " + email + ", " + name + ", " + direction);
    }
}
