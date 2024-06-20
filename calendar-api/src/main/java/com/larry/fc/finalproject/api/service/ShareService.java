package com.larry.fc.finalproject.api.service;

import com.larry.fc.finalproject.api.dto.AuthUser;
import com.larry.fc.finalproject.api.dto.CreateShareReq;
import com.larry.fc.finalproject.api.dto.SharedScheduleDto;
import com.larry.fc.finalproject.core.domain.RequestReplyType;
import com.larry.fc.finalproject.core.domain.RequestStatus;
import com.larry.fc.finalproject.core.domain.entity.Share;
import com.larry.fc.finalproject.core.domain.entity.User;
import com.larry.fc.finalproject.core.domain.entity.repository.ShareRepository;
import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import com.larry.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShareService {
    private final UserService userService;
    private final ShareRepository shareRepository;
    private EmailService emailService;

    @Transactional
    public void createShare(AuthUser authUser, CreateShareReq req) {
        final User toUser = userService.findByUserId(authUser.getId());
        final User fromUser = userService.findByUserId(req.getToUserId());
        shareRepository.save(Share.builder()
                        .fromUserId(fromUser.getId())
                        .toUserId(toUser.getId())
                        .direction(req.getDirection())
                        .requestStatus(RequestStatus.REQUESTED)
                        .build());
        emailService.sendShareRequestMail(toUser.getEmail(), fromUser.getName(), req.getDirection());
    }

    @Transactional
    public void replyToShareRequest(Long shareId, AuthUser toAuthUser, RequestReplyType type) {
        shareRepository.findById(shareId)
                .filter(share -> share.getToUserId().equals(toAuthUser.getId()))
                .filter(share -> share.getRequestStatus() == RequestStatus.REQUESTED)
                .map(share -> share.reply(type))
                .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST));
    }

    @Transactional
    public List<Long> findSharedUserIdsByUser(AuthUser authUser) {
        final Stream<Long> biDirectionShares = shareRepository.findAllByDirection(
                    authUser.getId()
                    ,RequestStatus.ACCEPTED
                    ,Share.Direction.BI_DIRECTION
                ).stream()
                .map(s -> s.getToUserId().equals(authUser.getId()) ? s.getFromUserId() : s.getToUserId());

        final Stream<Long> uniDirectionShares = shareRepository.findAllByToUserIdAndRequestAndDirection(authUser.getId(),
                    RequestStatus.ACCEPTED,
                    Share.Direction.UNI_DIRECTION).stream()
                .map(s -> s.getFromUserId());

        return Stream.concat(biDirectionShares, uniDirectionShares) .collect(Collectors.toList());
    }
}
