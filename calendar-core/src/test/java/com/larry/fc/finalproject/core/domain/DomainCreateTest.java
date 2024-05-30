package com.larry.fc.finalproject.core.domain;

import com.larry.fc.finalproject.core.domain.ScheduleType;
import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.Event;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DomainCreateTest {

    @Test
    void createEvent(){
        final User me = new User("meme", "email@email.com", "pw", LocalDate.now());
        final Schedule taskSchedule = Schedule.task("할일", "청소하기", LocalDateTime.now(), me);

        Assertions.assertEquals(taskSchedule.getScheduleType(), ScheduleType.TASK);
        Assertions.assertEquals(taskSchedule.toTask().getTitle(), "할일");
   }
}
