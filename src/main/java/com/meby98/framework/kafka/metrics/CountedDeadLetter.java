package com.meby98.framework.kafka.metrics;


import java.lang.annotation.*;

@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CountedDeadLetter {
    String value();
    String description() default "";
    String[] extraTags() default {};
}
  