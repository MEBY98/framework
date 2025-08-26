package com.meby98.mapper;

import com.meby98.date.DateConverterUtils;
import com.meby98.date.DateConverterUtils.FormatDatesEnum;
import org.mapstruct.Named;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public interface BaseMapper {

  @Named("localDateNow")
  default LocalDate localDateNow() {
    return LocalDate.now();
  }

  @Named("localDateTimeNow")
  default LocalDateTime localDateTimeNow() {
    return LocalDateTime.now();
  }

  @Named("offsetDateTimeNow")
  default OffsetDateTime offsetDateTimeNow() {
    return OffsetDateTime.now();
  }

  @Named("parseLocalDate")
  default LocalDate parseLocalDate(final String date, final FormatDatesEnum format) { return DateConverterUtils.parseLocalDate(date, format); }

  @Named("parseLocalDateTime")
  default LocalDateTime parseLocalDateTime(final String date, final FormatDatesEnum format) { return DateConverterUtils.parseLocalDateTime(date, format); }

  @Named("parseOffsetDateTime")
  default OffsetDateTime parseOffsetDateTime(final String date, final FormatDatesEnum format) { return DateConverterUtils.parseOffsetDateTime(date, format); }

  @Named("formatLocalDate")
  default String formatLocalDate(final LocalDate localDate, final FormatDatesEnum format) {
    if (localDate == null) {
      return null;
    }
    return DateConverterUtils.formatLocalDate(localDate, format);
  }

  @Named("formatLocalDateTime")
  default String formatLocalDateTime(final LocalDateTime localDateTime, final FormatDatesEnum format) {
    if (localDateTime == null) {
      return null;
    }
    return DateConverterUtils.formatLocalDateTime(localDateTime, format);
  }

  @Named("formatOffsetDateTime")
  default String formatOffsetDateTime(final OffsetDateTime offsetDateTime, final FormatDatesEnum format) {
    if (offsetDateTime == null) {
      return null;
    }
    return DateConverterUtils.formatOffsetDateTime(offsetDateTime, format);
  }

  @Named("fromLocalDateTimeToLocalDate")
  default LocalDate fromLocalDateTimeToLocalDate(final LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateTimeToLocalDate(localDateTime);
  }

  @Named("fromLocalDateTimeToOffsetDateTime")
  default OffsetDateTime fromLocalDateTimeToOffsetDateTime(final LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateTimeToOffsetDateTime(localDateTime);
  }

  @Named("fromLocalDateTimeToOffsetDateTime")
  default OffsetDateTime fromLocalDateTimeToOffsetDateTime(final LocalDateTime localDateTime, final ZoneOffset zoneOffset) {
    if (localDateTime == null || zoneOffset == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateTimeToOffsetDateTime(localDateTime, zoneOffset);
  }

  @Named("fromOffsetDateTimeToLocalDateTime")
  default LocalDateTime fromOffsetDateTimeToLocalDateTime(final OffsetDateTime offsetDateTime) {
    if (offsetDateTime == null) {
      return null;
    }
    return DateConverterUtils.fromOffsetDateTimeToLocalDateTime(offsetDateTime);
  }

  @Named("fromOffsetDateTimeToLocalDate")
  default LocalDate fromOffsetDateTimeToLocalDate(final OffsetDateTime offsetDateTime) {
    if (offsetDateTime == null) {
      return null;
    }
    return DateConverterUtils.fromOffsetDateTimeToLocalDate(offsetDateTime);
  }

  @Named("fromLocalDateToLocalDateTime")
  default LocalDateTime fromLocalDateToLocalDateTime(final LocalDate localDate) {
    if (localDate == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateToLocalDateTime(localDate);
  }

  @Named("fromLocalDateToLocalDateTime")
  default LocalDateTime fromLocalDateToLocalDateTime(final LocalDate localDate, final Integer hour, final Integer minutes, final Integer seconds) {
    if (localDate == null || hour == null || minutes == null || seconds == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateToLocalDateTime(localDate, hour, minutes, seconds);
  }

  @Named("fromLocalDateToOffsetDateTime")
  default OffsetDateTime fromLocalDateToOffsetDateTime(final LocalDate localDate) {
    if (localDate == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateToOffsetDateTime(localDate);
  }

  @Named("fromLocalDateToOffsetDateTime")
  default OffsetDateTime fromLocalDateToOffsetDateTime(final LocalDate localDate, final Integer hour, final Integer minutes, final Integer seconds) {
    if (localDate == null || hour == null || minutes == null || seconds == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateToOffsetDateTime(localDate, hour, minutes, seconds);
  }

  @Named("fromLocalDateToOffsetDateTime")
  default OffsetDateTime fromLocalDateToOffsetDateTime(final LocalDate localDate, final Integer hour, final Integer minutes, final Integer seconds, final ZoneOffset zoneOffset) {
    if (localDate == null || hour == null || minutes == null || seconds == null || zoneOffset == null) {
      return null;
    }
    return DateConverterUtils.fromLocalDateToOffsetDateTime(localDate, hour, minutes, seconds, zoneOffset);
  }

  @Named("fromSqlDateToLocalDate")
  default LocalDate fromSqlDateToLocalDate(final Date date) {
    if (date == null) {
      return null;
    }
    return DateConverterUtils.fromSqlDateToLocalDate(date);
  }

  @Named("fromDateToLocalDate")
  default LocalDate fromDateToLocalDate(final java.util.Date date) {
    if (date == null) {
      return null;
    }
    return DateConverterUtils.fromDateToLocalDate(date);
  }

  @Named("fromDateToLocalDateTime")
  default LocalDateTime fromDateToLocalDateTime(final java.util.Date date) {
    if (date == null) {
      return null;
    }
    return DateConverterUtils.fromDateToLocalDateTime(date);
  }

  @Named("fromDateToOffsetDateTime")
  default OffsetDateTime fromDateToOffsetDateTime(final java.util.Date date) {
    if (date == null) {
      return null;
    }
    return DateConverterUtils.fromDateToOffsetDateTime(date);
  }
}
