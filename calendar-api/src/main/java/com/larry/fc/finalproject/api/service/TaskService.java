package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.TaskCreateReq;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.larry.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;

    public void create(TaskCreateReq req, AuthUser authUser) {
        final Schedule taskSchedule = Schedule.task(req.getTitle(), req.getDescription(), req.getTaskAt(), userService.findByUserId(authUser.getId()));
    }
}
