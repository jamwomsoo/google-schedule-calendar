package com.larry.fc.finalproject.core.service;

import org.springframework.stereotype.Service;
import com.larry.fc.finalproject.api.dto.ScheduleDto;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScheduleQueryService {
    public List<ScheduleDto> getSchduleByDay(com.larry.fc.finalproject.api.dto.AuthUser authUser, LocalDate date) {
    }
}
