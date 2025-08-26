package com.meby98.date;

import java.time.temporal.Temporal;

public interface TemporalItem<T extends Temporal & Comparable<? super T>> {
    T getStartDate();
    T getEndDate();
    T getNow();
    boolean isActive();
    boolean isActiveAtDate(T date);
    boolean isActiveOrWillBeActive();
    boolean willBeActive();
    boolean isOld();
}
