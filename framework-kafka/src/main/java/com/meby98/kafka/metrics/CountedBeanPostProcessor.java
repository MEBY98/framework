package com.meby98.kafka.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Slf4j
@Component
public class CountedBeanPostProcessor implements BeanPostProcessor {
  public static final String COUNTER_DESCRIPTION = "Error while consuming events from Kafka. For more details check e_dead_letter.";

  private final MeterRegistry meterRegistry;

  @Autowired
  public CountedBeanPostProcessor(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    for (Method method : bean.getClass().getDeclaredMethods()) {
      if (method.isAnnotationPresent(CountedDeadLetter.class)) {
        CountedDeadLetter counted = method.getAnnotation(CountedDeadLetter.class);
        createCounter(
            counted.value(),
            counted.description(),
            counted.extraTags().length == 0 ? new String[] {} : counted.extraTags());
      }
    }
    return bean;
  }

  private void createCounter(String name, String description, String... tags) {
    var metricDescription =
        Objects.equals(description.trim(), "")
            ? COUNTER_DESCRIPTION
            : description;
    Counter.builder(name).description(metricDescription).tags(tags).register(meterRegistry);
    log.info(
        "Created counter metric: {} with description: {} and tags: {}.",
        name,
        metricDescription,
        tags);
  }
}
