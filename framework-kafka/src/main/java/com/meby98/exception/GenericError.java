package com.meby98.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericError {
    private final String errorCode;
    private String errorMessage;
    private String errorDetail;

    public GenericError(final ApplicationException applicationException) {
        this.errorCode = applicationException.getErrorCode();
        this.errorMessage = "";
        this.errorDetail = "";
    }
}
