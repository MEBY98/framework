package com.meby98.exception;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
@EnableConfigurationProperties(MessageSourceProperties.class)
@ConditionalOnProperties({
        @ConditionalOnProperty(prefix = "meby98.fwk.message-source", name = "enabled", havingValue = "true",
                matchIfMissing = true),
        @ConditionalOnProperty(prefix = "meby98.fwk.rest.controller-advicer", name = "enabled", havingValue = "true")
})
public class MessageSourceConfiguration {

    @Bean
    public MessageSource messageSource(final MessageSourceProperties messageSourceProperties) {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(messageSourceProperties.getBaseName());
        messageSource.setDefaultEncoding(messageSourceProperties.getEncoding());
        messageSource.setCacheSeconds(messageSourceProperties.getCache());
        return messageSource;
    }
}
