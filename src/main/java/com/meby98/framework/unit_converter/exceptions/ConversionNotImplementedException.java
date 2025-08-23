package com.meby98.framework.unit_converter.exceptions;

import com.mercadona.framework.cna.commons.exception.MercadonaRuntimeException;

public class ConversionNotImplementedException extends MercadonaRuntimeException {
    public ConversionNotImplementedException(final String message) {
        super(message);
    }
}
