package com.larry.fc.finalproject.core.domain.entity;

import com.larry.fc.finalproject.core.domain.Event;
import com.larry.fc.finalproject.core.domain.RequestReplyType;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.ScheduleType;
import com.larry.fc.finalproject.core.util.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity
@Table(name = "engagements")
public class Engagement extends  BaseEntity {

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private User attendee;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    public Engagement(Schedule eventSchedule, User attendee) {
        assert eventSchedule.getScheduleType() == ScheduleType.EVENT;
        this.schedule = eventSchedule;
        this.requestStatus = RequestStatus.REQUESTED;
        this.attendee = attendee;
    }

    public Event getEvent(){
        return schedule.toEvent();
    }

    public User getAttendee() {
        return attendee;
    }

//    public RequestStatus getRequestStatus() {
//        return RequestStatus;
//    }

    public boolean isOverlapped(LocalDate date){
        return this.schedule.isOverlapped(date);
    }

    public boolean isOverlapped(Period period) {
        return this.schedule.isOverlapped(period);
    }

    public Engagement reply(RequestReplyType type) {
        switch (type){
            case ACCEPT:
                this.requestStatus = RequestStatus.ACCEPTED;
                break;
            case REJECT:
                this.requestStatus = RequestStatus.REQUESTED;
                break;
        }
        return this;
    }
}
