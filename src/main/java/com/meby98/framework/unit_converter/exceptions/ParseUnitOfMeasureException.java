package com.meby98.framework.unit_converter.exceptions;

import com.mercadona.framework.cna.commons.domain.MercadonaBusinessException;

public class ParseUnitOfMeasureException extends MercadonaBusinessException {
    public ParseUnitOfMeasureException(final String message) {
        super(message);
    }
}
