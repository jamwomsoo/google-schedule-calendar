package com.larry.fc.finalproject.api.controller.api;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.TaskCreateReq;
import com.larry.fc.finalproject.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

import static com.larry.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    private final TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<Void> createTask(
            @RequestBody TaskCreateReq taskCreateReq,
            HttpSession httpSession
    ){
        final Long userId = (Long) httpSession.getAttribute(LOGIN_SESSION_KEY);
        if(userId == null){
            throw  new RuntimeException("bad request. no session");
        }
        taskService.create(taskCreateReq, AuthUser.of(userId));
        return ResponseEntity.ok().build();
    }
}
