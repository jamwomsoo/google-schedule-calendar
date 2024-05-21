package com.larry.fc.finalproject.core.domain;

import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Event {
    private Schedule schedule;
    public Event(com.larry.fc.finalproject.core.domain.entity.Schedule schedule){
        this.schedule = schedule;
    }

    public User getWriter(){
        return this.schedule.getWriter();
    }
}
