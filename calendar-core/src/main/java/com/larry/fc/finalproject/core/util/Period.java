package com.larry.fc.finalproject.core.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Period {
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public Period(LocalDateTime startAt, LocalDateTime endAt){
        this.startAt =startAt;
        if (endAt == null){
            this.endAt = startAt;
        }else {
            this.endAt = endAt;
        }
    }

    public static Period of(LocalDateTime startAt, LocalDateTime endAt){
        return new Period(startAt, endAt == null ? startAt : endAt);
    }

    public static Period of(LocalDate startDate, LocalDate endDate){
        return new Period(startDate.atStartOfDay(), LocalDateTime.of(endDate, LocalTime.of(23,59,59,999999999)));
    }

    public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
        return this.startAt.isBefore(endAt) && startAt.isBefore(this.endAt);
    }

    public boolean isOverlapped(LocalDate date) {
        return isOverlapped(date.atStartOfDay(), LocalDateTime.of(date, LocalTime.of(23,59,59,999999999)));
    }

    public boolean isOverlapped(Period period) {
        return isOverlapped(period.startAt, period.endAt);
    }
    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

}
