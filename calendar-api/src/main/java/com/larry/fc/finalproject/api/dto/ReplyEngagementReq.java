package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.domain.RequestReplyType;

public class ReplyEngagementReq {
    private  RequestReplyType type; //REJECT, ACCETP

    public ReplyEngagementReq(RequestReplyType type) {
        this.type = type;
    }

    public ReplyEngagementReq() {
    }

    public RequestReplyType getType() {
        return type;
    }
}
