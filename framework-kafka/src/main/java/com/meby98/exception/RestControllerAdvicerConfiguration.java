package com.meby98.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "meby98.fwk.rest.controller-advicer", name = "enabled", havingValue = "true")
public class RestControllerAdvicerConfiguration {

    private final MessageService messageService;

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<GenericError> handleApplicationException(final ApplicationException applicationException) {
        return ResponseEntity.status(applicationException.getHttpStatus()).body(buildGenericError(applicationException));
    }

    private GenericError buildGenericError(final ApplicationException applicationException) {
        final var genericError = new GenericError(applicationException);
        setLocalizedErrorMessage(applicationException, genericError);
        return genericError;
    }

    private void setLocalizedErrorMessage(final ApplicationException applicationException, final GenericError genericError) {
        genericError.setErrorMessage(this.messageService.getMessage(applicationException.getErrorCode()));
        genericError.setErrorDetail(this.messageService.getMessage(applicationException.getErrorDetailCode()));
    }
}
