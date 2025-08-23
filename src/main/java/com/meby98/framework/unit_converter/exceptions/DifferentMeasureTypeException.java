package com.meby98.framework.unit_converter.exceptions;

import com.mercadona.framework.cna.commons.exception.MercadonaRuntimeException;

public class DifferentMeasureTypeException extends MercadonaRuntimeException {

    public DifferentMeasureTypeException(final String message) {
        super(message);
    }
}
