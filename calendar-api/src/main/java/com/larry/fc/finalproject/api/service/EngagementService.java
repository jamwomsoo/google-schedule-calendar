package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.core.domain.RequestReplyType;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EngagementService {
    private final EngagementRepository engagementRepository;
    public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
        //engagement  조회
        // 참석자가 auth user와 같은지 비교
        // requested 상태인지 체크
        // update
        return engagementRepository.findById(engagementId)
                .filter(e -> e.getRequestStatus() == RequestStatus.REQUESTED)
                .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
                .map(e -> e.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
                .getRequestStatus();
    }
}
