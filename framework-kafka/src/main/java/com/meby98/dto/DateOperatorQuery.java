package com.meby98.dto;

import lombok.Getter;

import java.time.*;
import java.time.temporal.Temporal;

@Getter
public class DateOperatorQuery<T extends Temporal> {

    private final T date;
    private final DateOperatorEnum dateOperator;

    public DateOperatorQuery(final DateOperatorQueryBuilder<T> builder) {
        if (builder.date instanceof LocalDate
                || builder.date instanceof LocalDateTime
                || builder.date instanceof OffsetDateTime
                || builder.date instanceof ZonedDateTime
                || builder.date instanceof Instant) {
            this.date = builder.date;
            this.dateOperator = builder.dateOperator;
        } else {
            throw new IllegalArgumentException("DATE SHOULD BE [LocalDate, LocalDateTime, OffsetDateTime, " +
                    "ZonedDateTime, Instant] FROM PACKAGE java.time");
        }
    }

    public static <T extends Temporal> DateOperatorQueryBuilder<T> builder() {
        return new DateOperatorQueryBuilder<>();
    }

    public static class DateOperatorQueryBuilder<T extends Temporal> {

        private T date;
        private DateOperatorEnum dateOperator;

        public DateOperatorQueryBuilder<T> date(final T date) {
            this.date = date;
            return this;
        }

        public DateOperatorQueryBuilder<T> dateOperator(final DateOperatorEnum dateOperator) {
            this.dateOperator = dateOperator;
            return this;
        }

        public DateOperatorQuery<T> build() {
            return new DateOperatorQuery<>(this);
        }
    }
}
