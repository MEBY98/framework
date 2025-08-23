package com.meby98.framework.date;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class TemporalItemLocalDateTime implements TemporalItem<LocalDateTime> {

    @Override
    public LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    @Override
    public boolean isActive() {
        return DateUtils.isBetween(getNow(), getStartDate(), getEndDate());
    }

    @Override
    public boolean isActiveAtDate(final LocalDateTime date) {
        return DateUtils.isBetween(date, getStartDate(), getEndDate());
    }

    @Override
    public boolean isActiveOrWillBeActive() {
        return isActive() || willBeActive();
    }

    @Override
    public boolean willBeActive() {
        return getStartDate().isAfter(getNow());
    }

    @Override
    public boolean isOld() {
        return getEndDate().isBefore(getNow());
    }
}
