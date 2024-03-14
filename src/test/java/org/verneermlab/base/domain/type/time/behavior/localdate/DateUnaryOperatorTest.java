package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateUnaryOperatorTest {

  @Test
  public void testApply() {
    var impl = new DateUnaryOperatorImpl(LocalDate.of(2024, 1, 1));
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

  public static class DateUnaryOperatorImpl implements DateUnaryOperator<DateUnaryOperatorImpl> {

    private LocalDate value;

    public DateUnaryOperatorImpl(LocalDate value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDate> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
