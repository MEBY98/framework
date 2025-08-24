package com.meby98;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "meby98.fwk.kafka.admin.ssl")
@Getter
@Setter
@AllArgsConstructor
public class SslProperties {
}
