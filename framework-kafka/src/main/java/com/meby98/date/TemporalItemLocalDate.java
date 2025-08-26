package com.meby98.date;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public abstract class TemporalItemLocalDate implements TemporalItem<LocalDate> {

    @Override
    public LocalDate getNow() {
        return LocalDate.now();
    }

    @Override
    public boolean isActive() {
        return DateUtils.isBetween(getNow(), getStartDate(), getEndDate());
    }

    @Override
    public boolean isActiveAtDate(final LocalDate date) {
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
