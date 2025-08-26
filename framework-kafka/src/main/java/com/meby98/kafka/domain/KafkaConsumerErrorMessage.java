package com.meby98.kafka.domain;

import lombok.Getter;

@Getter
public class KafkaConsumerErrorMessage<K, V> extends KafkaConsumerMessage<K, V> {
  private final String exception;

  public KafkaConsumerErrorMessage (final KafkaConsumerErrorMessageBuilder<K, V> builder) {
    super(builder);
    this.exception = builder.exception;
  }

  public static <K, V> KafkaConsumerErrorMessageBuilder<K, V> builder() {
    return new KafkaConsumerErrorMessageBuilder<>();
  }

  public static class KafkaConsumerErrorMessageBuilder<K, V> extends KafkaConsumerMessageBuilder<K, V> {
    private String exception;

    public KafkaConsumerErrorMessageBuilder<K, V> exception(final String exception) {
      this.exception = exception;
      return this;
    }

    @Override
    public KafkaConsumerErrorMessage<K, V> build() {
      return new KafkaConsumerErrorMessage<>(this);
    }
  }
}
