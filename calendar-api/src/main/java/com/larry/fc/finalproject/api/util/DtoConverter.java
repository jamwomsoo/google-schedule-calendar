package com.larry.fc.finalproject.api.util;

import com.larry.fc.finalproject.api.dto.EventDto;
import com.larry.fc.finalproject.api.dto.NotificationDto;
import com.larry.fc.finalproject.api.dto.ScheduleDto;
import com.larry.fc.finalproject.api.dto.TaskDto;
import com.larry.fc.finalproject.core.domain.entity.Schedule;

public abstract class DtoConverter {

    public static ScheduleDto fromSchedule(Schedule schedule){
        switch (schedule.getScheduleType()){
            case EVENT:
                return EventDto.builder()
                        .scheduleId(schedule.getId())
                        .description(schedule.getDescription())
                        .startAt(schedule.getStartAt())
                        .endAt(schedule.getEndAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            case TASK:
                return TaskDto.builder()
                        .scheduleId(schedule.getId())
                        .taskAt(schedule.getStartAt())
                        .description(schedule.getDescription())
                        .writerId(schedule.getWriter().getId())
                        .title(schedule.getTitle())
                        .build();
            case NOTIFICATION:
                return NotificationDto.builder()
                        .scheduleId(schedule.getId())
                        .notifyAt(schedule.getStartAt())
                        .title(schedule.getTitle())
                        .writerId(schedule.getWriter().getId())
                        .build();
            default:
                throw  new RuntimeException("bad request. not matched schedule type");
        }
    }
}
