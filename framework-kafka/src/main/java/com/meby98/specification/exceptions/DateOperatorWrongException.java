package com.meby98.specification.exceptions;

public class DateOperatorWrongException extends RuntimeException {
  public DateOperatorWrongException(final String message, final String errorCode) {
    super(message, errorCode);
  }

  public DateOperatorWrongException(final String message) {
    super(message);
  }
}
