package com.meby98.framework.specification.exceptions;

import com.mercadona.framework.cna.commons.domain.MercadonaBusinessException;

public class DateOperatorWrongException extends MercadonaBusinessException {
  public DateOperatorWrongException(final String message, final String errorCode) {
    super(message, errorCode);
  }

  public DateOperatorWrongException(final String message) {
    super(message);
  }
}
