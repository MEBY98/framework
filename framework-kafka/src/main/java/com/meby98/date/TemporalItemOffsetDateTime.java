package com.meby98.date;

import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
public abstract class TemporalItemOffsetDateTime implements TemporalItem<OffsetDateTime> {

    @Override
    public OffsetDateTime getNow() { return OffsetDateTime.now(); }

    @Override
    public boolean isActive() {
        return DateUtils.isBetween(getNow(), getStartDate(), getEndDate());
    }

    @Override
    public boolean isActiveAtDate(final OffsetDateTime date) {
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
