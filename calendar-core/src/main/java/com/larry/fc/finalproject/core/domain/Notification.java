package com.larry.fc.finalproject.core.domain;

import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public class Notification {

    private Schedule schedule;
    public Notification(Schedule schedule){
        this.schedule = schedule;
    }

    public User getWriter(){
        return this.schedule.getWriter();
    }
}
