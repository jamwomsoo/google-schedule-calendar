package com.larry.fc.finalproject.api.dto;

import com.larry.fc.finalproject.core.exception.CalendarException;
import com.larry.fc.finalproject.core.exception.ErrorCode;
import com.larry.fc.finalproject.core.util.TimeUnit;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.larry.fc.finalproject.core.util.TimeUnit.*;

@Data
public class NotificationCreateReq {
    private final String title;
    private final LocalDateTime notifyAt;
    private final RepeatInfo repeatInfo;

    public List<LocalDateTime> getRepeatTimes() {
        if(repeatInfo == null){
            return Collections.singletonList(notifyAt);
        }
        return IntStream.range(0, repeatInfo.times)
                .mapToObj(i -> {
                    long increment = (long) repeatInfo.interval.intervalValue * i;
                    switch (repeatInfo.interval.timeUnit){
                        case DAY:
                            return notifyAt.plusDays(increment);
                        case WEEK:
                            return notifyAt.plusWeeks(increment);
                        case MONTH:
                            return notifyAt.plusMonths(increment);
                        case YEAR:
                            return notifyAt.plusYears(increment);
                        default:
                            throw new CalendarException(ErrorCode.BAD_REQUEST);
                    }
                })
                .collect(Collectors.toList());
    }

    @Data
    public static  class RepeatInfo{
        private  final Interval interval;
        private final int times;
    }

    @Data
    public static class Interval{
        private final int intervalValue;
        private final TimeUnit timeUnit;
    }
}
