package com.meby98.exception;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSourceProperties {

    @Value("${meby98.fwk.message-source.base-name}")
    private String baseName = "classpath:messages";
    @Value("${meby98.fwk.message-source.encoding}")
    private String encoding = "UTF-8";
    @Value("${meby98.fwk.message-source.cache}")
    private Integer cache = 3600;

}
