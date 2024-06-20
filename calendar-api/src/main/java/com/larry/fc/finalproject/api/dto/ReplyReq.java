package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.domain.RequestReplyType;

public class ReplyReq {
    private  RequestReplyType type; //REJECT, ACCETP

    public ReplyReq(RequestReplyType type) {
        this.type = type;
    }

    public ReplyReq() {
    }

    public RequestReplyType getType() {
        return type;
    }
}
