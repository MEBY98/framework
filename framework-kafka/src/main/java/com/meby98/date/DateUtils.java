package com.meby98.date;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.tuple.Pair;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.requireNonNull;

@UtilityClass
public final class DateUtils {

    public static <T extends Temporal & Comparable<? super T>> boolean isBeforeOrEqual(T date1, T date2) {
        return date1.compareTo(date2) <= 0;
    }

    public static <T extends Temporal & Comparable<? super T>> T getLowestDate(final List<T> dates) {
        if (dates == null || dates.isEmpty()) {
            return null;
        }

        return dates.stream()
                .min(T::compareTo)
                .orElse(null);
    }

    public static <T extends Temporal & Comparable<? super T>> T getGreatestDate(final List<T> dates) {
        if (dates == null || dates.isEmpty()) {
            return null;
        }

        return dates.stream()
                .max(T::compareTo)
                .orElse(null);
    }

    public static <T extends Temporal & Comparable<? super T>> Boolean hasEmptyPeriodTime(final List<Pair<T, T>> dates) {
        if (dates == null || dates.isEmpty()) {
            return false;
        }

        if (dates.size() == 1) {
            return false;
        }

        boolean hasNext;
        int i = indexOfLowestDate(dates.stream().map(Pair::getLeft).toList());

        do {
            hasNext = false;

            final Pair<T, T> pairDatesI = dates.get(i);

            final T endDate = pairDatesI.getRight();

            for (int j = 0; j < dates.size(); j++) {
                final Pair<T, T> pairDatesJ = dates.get(j);

                if (endDate.plus(1L, ChronoUnit.DAYS).equals(pairDatesJ.getLeft())) {
                    i = j;
                    hasNext = true;
                    break;
                }
            }

        } while (hasNext);

        final T greatestEndDate = requireNonNull(getGreatestDate(dates.stream().map(Pair::getRight).toList()));

        if (greatestEndDate.equals(dates.get(i).getRight())) {
            return FALSE;
        } else {
            return TRUE;
        }
    }

    public static <T extends Temporal & Comparable<? super T>> Boolean hasMergedDates(final List<Pair<T, T>> dates) {
        if (dates == null || dates.isEmpty()) {
            return false;
        }

        if (dates.size() == 1) {
            return false;
        }

        for (int i = 0; i < dates.size(); i++) {
            final Pair<T, T> pairDatesI = dates.get(i);

            final T startDate = pairDatesI.getLeft();
            final T endDate = pairDatesI.getRight();

            for (int j = 0; j < dates.size(); j++) {
                if (i != j) {
                    final Pair<T, T> pairDatesJ = dates.get(j);

                    if (((startDate.compareTo(pairDatesJ.getLeft()) >= 0) && (startDate.compareTo(pairDatesJ.getRight()) <= 0))
                            || ((endDate.compareTo(pairDatesJ.getLeft()) >= 0) && (endDate.compareTo(pairDatesJ.getRight()) <= 0))) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static <T extends Temporal & Comparable<T>> boolean isBetween(final T date, final T startDate,
                                                                         final T endDate) {
        if (startDate == null || endDate == null || date == null) {
            return false;
        }
        return (date.compareTo(startDate) >= 0) && (date.compareTo(endDate) <= 0);
    }

    public static <T extends Temporal & Comparable<? super T>> int indexOfLowestDate(final List<T> dates) {
        if (dates == null || dates.isEmpty()) {
            return -1;
        }

        if (dates.size() == 1) {
            return 0;
        }

        int indexResult = 0;
        T lowestDate = dates.get(0);

        for (int i = 0; i < dates.size(); i++) {
            final T localDate = dates.get(i);

            if (localDate.compareTo(lowestDate) <= 0) {
                indexResult = i;
            }
        }

        return indexResult;
    }
}
