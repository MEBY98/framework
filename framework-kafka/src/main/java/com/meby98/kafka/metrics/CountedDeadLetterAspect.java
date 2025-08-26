package com.meby98.kafka.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CountedDeadLetterAspect {
  private final MeterRegistry meterRegistry;

  @Autowired
  public CountedDeadLetterAspect(MeterRegistry meterRegistry) {
    this.meterRegistry = meterRegistry;
  }

  @Around("@annotation(countedDeadLetter)")
  public Object countMetric(ProceedingJoinPoint joinPoint, CountedDeadLetter countedDeadLetter)
      throws Throwable {
    var counter = getCounter(countedDeadLetter.value(), countedDeadLetter.extraTags());
    counter.increment();
    return joinPoint.proceed();
  }

  private Counter getCounter(String name, String... tags) {
    return meterRegistry.counter(name, tags);
  }
}
