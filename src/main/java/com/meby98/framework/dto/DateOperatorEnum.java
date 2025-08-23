package com.meby98.framework.dto;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DateOperatorEnum {
  LT("LT"),
  LET("LET"),
  E("E"),
  GET("GET"),
  GT("GT");

  private final String value;

  private DateOperatorEnum(final String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public static DateOperatorEnum fromValue(String value) {
    for (final DateOperatorEnum dateOperatorEnum: values()) {
      if (dateOperatorEnum.value.equals(value)) {
        return dateOperatorEnum;
      }
    }
    throw new IllegalArgumentException("NOT VALID VALUE ENUM '" + value + "'");
  }
}
