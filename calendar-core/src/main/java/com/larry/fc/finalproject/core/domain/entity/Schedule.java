package com.larry.fc.finalproject.core.domain.entity;

import com.larry.fc.finalproject.core.domain.Event;
import com.larry.fc.finalproject.core.domain.Notification;
import com.larry.fc.finalproject.core.domain.ScheduleType;
import com.larry.fc.finalproject.core.domain.Task;
import com.larry.fc.finalproject.core.util.Period;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder(access = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "schedules")
public class Schedule extends BaseEntity{

    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "writer_id")
    private User writer;

    @Enumerated
    private ScheduleType scheduleType;

    public static Schedule event(String title, String description, LocalDateTime startAt, LocalDateTime endAt, User writer){
        return Schedule.builder()
                .startAt(startAt)
                .endAt(endAt)
                .title(title)
                .description(description)
                .writer(writer)
                .scheduleType(ScheduleType.EVENT)
                .build();
    }

    public static Schedule task(String title, String description,LocalDateTime taskAt, User writer){
        return Schedule.builder()
                .startAt(taskAt)
                .title(title)
                .description(description)
                .writer(writer)
                .scheduleType(ScheduleType.TASK)
                .build();
    }

    public static Schedule notification(String title, LocalDateTime notificationAt, User writer){
        return Schedule.builder()
                .startAt(notificationAt)
                .title(title)
                .writer(writer)
                .scheduleType(ScheduleType.NOTIFICATION)
                .build();
    }

    public Task toTask(){
        return new Task(this);
    }

    public Event toEvent(){
        return new Event(this);
    }

    public Notification toNotification(){
        return new Notification(this);
    }

    public boolean isOverlapped(LocalDate date){
        return Period.of(getStartAt(), getEndAt()).isOverlapped(date);
    }

    public boolean isOverlapped(Period period) {
        return Period.of(getStartAt(), getEndAt()).isOverlapped(period);
    }
}
