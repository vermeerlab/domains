package org.verneermlab.base.domain.type.time.behavior;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateTimeUnaryOperatorTest {

  @Test
  public void testApply() {
    var impl = new DateTimeUnaryOperatorImpl(LocalDateTime.of(2024, 1, 1, 2, 3));
    var actual1 = impl.apply(value -> {
      return value;
    });

    Assertions.assertEquals(impl.getNullableValue().get(), actual1.getNullableValue().get());
    Assertions.assertTrue(impl != actual1);

    var actual2 = impl.apply(value -> {
      return value.plusDays(1);
    });

    Assertions.assertNotEquals(impl.getNullableValue().get(), actual2.getNullableValue().get());
    Assertions.assertEquals(2, actual2.getNullableValue().get().getDayOfMonth());
  }

  public static class DateTimeUnaryOperatorImpl implements DateTimeUnaryOperator<DateTimeUnaryOperatorImpl> {

    private LocalDateTime value;

    public DateTimeUnaryOperatorImpl(LocalDateTime value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDateTime> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
