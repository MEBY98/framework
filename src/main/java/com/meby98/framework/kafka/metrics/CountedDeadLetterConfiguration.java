package com.meby98.framework.kafka.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountedDeadLetterConfiguration {
  @Bean
  @Autowired
  public CountedDeadLetterAspect countedAspect(MeterRegistry meterRegistry) {
    return new CountedDeadLetterAspect(meterRegistry);
  }

  @Bean
  @Autowired
  InitializingBean forcePrometheusPostProcessor(
      BeanPostProcessor meterRegistryPostProcessor, MeterRegistry registry) {
    return () -> meterRegistryPostProcessor.postProcessAfterInitialization(registry, "");
  }
}
