package com.meby98.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnProperties({
        @ConditionalOnProperty(prefix = "meby98.fwk.message-source", name = "enabled", havingValue = "true",
                matchIfMissing = true),
        @ConditionalOnProperty(prefix = "meby98.fwk.rest.controller-advicer", name = "enabled", havingValue = "true")
})
public class MessageService {

    private final MessageSource messageSource;

    public String getMessage(final String code, final String... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
