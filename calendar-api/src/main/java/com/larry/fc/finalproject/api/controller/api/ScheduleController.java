package com.larry.fc.finalproject.api.controller.api;

import com.larry.fc.finalproject.api.dto.*;
import com.larry.fc.finalproject.api.service.EventService;
import com.larry.fc.finalproject.api.service.NotificationService;
import com.larry.fc.finalproject.api.service.TaskService;
import com.larry.fc.finalproject.api.service.ScheduleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final TaskService taskService;
    private final EventService eventService;
    private final NotificationService notificationService;
    private final ScheduleQueryService scheduleQueryService;

    @PostMapping("/tasks")
    public ResponseEntity<Void> createTask(
            @RequestBody TaskCreateReq taskCreateReq,
            AuthUser authUser
    ){
        taskService.create(taskCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/events")
    public ResponseEntity<Void> createEvent(
            @RequestBody EventCreateReq eventCreateReq,
            AuthUser authUser
    ){
        eventService.create(eventCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notifications")
    public ResponseEntity<Void> createNotification(
            @RequestBody NotificationCreateReq notificationCreateReq,
            AuthUser authUser
    ){
        notificationService.create(notificationCreateReq, authUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/day")
    public List<ScheduleDto> getScheduleByDay(
            AuthUser authUser,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ){
        return scheduleQueryService.getScheduleByDay(authUser, date == null ? LocalDate.now() : date);

    }
}
