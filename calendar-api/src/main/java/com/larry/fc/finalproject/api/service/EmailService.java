package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.controller.api.BatchController;
import com.larry.fc.finalproject.api.dto.EngagementEmailStuff;
import com.larry.fc.finalproject.core.domain.entity.Engagement;

public interface EmailService {
    void sendEngagement(EngagementEmailStuff stuff);
    void sendAlarmMail(BatchController.SendMailBatchReq sendMailBatchReq);
}
