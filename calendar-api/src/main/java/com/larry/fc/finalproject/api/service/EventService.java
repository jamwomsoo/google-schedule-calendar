package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.EngagementEmailStuff;
import com.larry.fc.finalproject.api.dto.EventCreateReq;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Engagement;
import com.larry.fc.finalproject.core.domain.entity.Schedule;
import com.larry.fc.finalproject.core.domain.entity.User;
import com.larry.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.larry.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import com.larry.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EngagementRepository engagementRepository;
    private final UserService userService;
    private final ScheduleRepository scheduleRepository;
    private final EmailService emailService;
    @Transactional
    public void create(EventCreateReq eventCreateReq, AuthUser authUser){
        // 이벤트 참여자의 다른 이벤트 중복 불가
        final List<Engagement> engagementList =
                engagementRepository.findAll(); // TODO 개선

        if(engagementList
                .stream()
                .anyMatch(e ->
                e.getEvent().isOverlapped(
                        eventCreateReq.getStartAt(), eventCreateReq.getEndAt())
                        && e.getRequestStatus() == RequestStatus.ACCEPTED)){
            throw new CalendarException(ErrorCode.ALREADY_EXISTS_USER);
        }
        final Schedule eventSchedule = Schedule.event(
                eventCreateReq.getTitle()
                , eventCreateReq.getDescription()
                , eventCreateReq.getStartAt()
                , eventCreateReq.getEndAt()
                , userService.findByUserId(authUser.getId()));

        scheduleRepository.save(eventSchedule);

        final List<User>  attendeeList = eventCreateReq.getAttendeeIds().stream().map(userService::findByUserId).collect(Collectors.toList());

        attendeeList.forEach(
                user -> {
                    final Engagement e = engagementRepository.save(new Engagement(eventSchedule, user));
                    emailService.sendEngagement(
                            new EngagementEmailStuff(
                                    e.getId(),
                                    e.getAttendee().getEmail(),
                                    attendeeList.stream().map(User::getEmail).collect(Collectors.toList()),
                                    e.getEvent().getTitle(),
                                    e.getEvent().getPeriod()
                            )
                    );
                }
        );
    }
}
