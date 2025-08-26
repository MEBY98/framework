package com.meby98.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ApplicationException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorDetailCode;

    public ApplicationException(final HttpStatus httpStatus, final String errorCode, final String errorDetailCode) {
        super(errorCode);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorDetailCode = errorDetailCode;
    }
}
