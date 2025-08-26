package com.meby98.date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.springframework.lang.NonNull;

import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;

@UtilityClass
public final class DateConverterUtils {

    @AllArgsConstructor
    @Getter
    public enum FormatDatesEnum {
        YYYY_MM_DD_HH_MM_SS_S("yyyy-MM-dd HH:mm:ss.S"),
        YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss"),
        YYYY_MM_DD("yyyy-MM-dd"),
        YYYY_MM_DD_T_HH_MM_SS_S("yyyy-MM-dd'T'HH:mm:ss.S"),
        YYYY_MM_DD_T_HH_MM_SS_SS("yyyy-MM-dd'T'HH:mm:ss.SS"),
        YYYY_MM_DD_T_HH_MM_SS_SSS("yyyy-MM-dd'T'HH:mm:ss.SSS"),
        YYYY_MM_DD_T_HH_MM_SS_SSSS("yyyy-MM-dd'T'HH:mm:ss.SSSS"),
        YYYY_MM_DD_T_HH_MM_SS_SSSSS("yyyy-MM-dd'T'HH:mm:ss.SSSSS"),
        YYYY_MM_DD_T_HH_MM_SS_SSSSSS("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"),
        YYYY_MM_DD_T_HH_MM_SS_SSSSSSXXX("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX"),
        YYYY_MM_DD_T_HH_MM_SS_SSS_Z("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

        private final String pattern;
    }

    public static LocalDate parseLocalDate(final String date, @NonNull final FormatDatesEnum format) {
        if ( date == null || date.isEmpty() || date.isBlank()) {
            return null;
        } else {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern(format.getPattern()));
        }
    }

    public static LocalDateTime parseLocalDateTime(final String date, @NonNull final FormatDatesEnum format) {
        if ( date == null || date.isEmpty() || date.isBlank()) {
            return null;
        } else {
            return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format.getPattern()));
        }
    }

    public static OffsetDateTime parseOffsetDateTime(final String date, @NonNull final FormatDatesEnum format) {
        if ( date == null || date.isEmpty() || date.isBlank()) {
            return null;
        } else {
            return OffsetDateTime.parse(date, DateTimeFormatter.ofPattern(format.getPattern()));
        }
    }

    public static String formatLocalDate(@NonNull final LocalDate localDate, @NonNull final FormatDatesEnum format) {
        return localDate.format(DateTimeFormatter.ofPattern(format.getPattern()));
    }

    public static String formatLocalDateTime(@NonNull final LocalDateTime localDateTime, @NonNull final FormatDatesEnum format) {
        return localDateTime.format(DateTimeFormatter.ofPattern(format.getPattern()));
    }

    public static String formatOffsetDateTime(@NonNull final OffsetDateTime offsetDateTime, @NonNull final FormatDatesEnum format) {
        return offsetDateTime.format(DateTimeFormatter.ofPattern(format.getPattern()));
    }

    public static LocalDateTime fromLocalDateToLocalDateTime(@NonNull final LocalDate localDate) {
        return localDate.atTime(0,0,0);
    }

    public static LocalDateTime fromLocalDateToLocalDateTime(@NonNull final LocalDate localDate, @NonNull final int hour,
                                                             @NonNull final int minutes, @NonNull final int seconds) {
        return localDate.atTime(hour, minutes, seconds);
    }

    public static OffsetDateTime fromLocalDateToOffsetDateTime(@NonNull final LocalDate localDate, @NonNull final int hour,
                                                               @NonNull final int minutes, @NonNull final int seconds) {
        final LocalDateTime localDateTime = fromLocalDateToLocalDateTime(localDate, hour, minutes, seconds);
        return fromLocalDateTimeToOffsetDateTime(localDateTime);
    }

    public static OffsetDateTime fromLocalDateToOffsetDateTime(@NonNull final LocalDate localDate, @NonNull final int hour,
                                                               @NonNull final int minutes, @NonNull final int seconds,
                                                               @NonNull final ZoneOffset zoneOffset) {
        final LocalDateTime localDateTime = fromLocalDateToLocalDateTime(localDate, hour, minutes, seconds);
        return fromLocalDateTimeToOffsetDateTime(localDateTime, zoneOffset);
    }

    public static OffsetDateTime fromLocalDateToOffsetDateTime(@NonNull final LocalDate localDate) {
        final LocalDateTime localDateTime = fromLocalDateToLocalDateTime(localDate);
        return fromLocalDateTimeToOffsetDateTime(localDateTime);
    }

    public static LocalDate fromLocalDateTimeToLocalDate(@NonNull final LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    public static LocalDateTime fromOffsetDateTimeToLocalDateTime(@NonNull final OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDateTime();
    }

    public static LocalDate fromOffsetDateTimeToLocalDate(@NonNull final OffsetDateTime offsetDateTime) {
        return offsetDateTime.toLocalDate();
    }

    public static OffsetDateTime fromLocalDateTimeToOffsetDateTime (@NonNull final LocalDateTime localDateTime) {
        return localDateTime.atOffset(ZoneId.systemDefault().getRules().getOffset(localDateTime));
    }

    public static OffsetDateTime fromLocalDateTimeToOffsetDateTime (@NonNull final LocalDateTime localDateTime, @NonNull final ZoneOffset zoneOffset) {
        return localDateTime.atOffset(zoneOffset);
    }

    public static LocalDate fromSqlDateToLocalDate(@NonNull final Date date) {
        return date.toLocalDate();
    }

    public static LocalDate fromDateToLocalDate(@NonNull final java.util.Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime fromDateToLocalDateTime(@NonNull final java.util.Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static OffsetDateTime fromDateToOffsetDateTime(@NonNull final java.util.Date date) {
        return date.toInstant().atOffset(ZoneId.systemDefault().getRules().getOffset(date.toInstant()));
    }
}

