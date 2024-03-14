package org.verneermlab.base.domain.type.time.behavior.localdatetime;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateTimeDaysShiftTest {

  @Test
  public void testBeginDay() {
    var actual1 = new DateTimeDaysShiftImpl(LocalDateTime.of(2024, Month.MARCH, 3, 1, 2, 3)).beginDay();
    Assertions.assertEquals(new DateTimeDaysShiftImpl(LocalDateTime.of(2024, Month.MARCH, 3, 0, 0, 0)), actual1);

    var actual2 = new DateTimeDaysShiftImpl().beginDay();
    Assertions.assertEquals(new DateTimeDaysShiftImpl(), actual2);
  }

  @Test
  public void testEndDay() {
    var actual1 = new DateTimeDaysShiftImpl(LocalDateTime.of(2024, Month.MARCH, 3, 1, 2, 3)).endDay();
    Assertions.assertEquals(new DateTimeDaysShiftImpl(LocalDateTime.of(2024, Month.MARCH, 3, 23, 59, 59, 999999999)), actual1);

    var actual2 = new DateTimeDaysShiftImpl().endDay();
    Assertions.assertEquals(new DateTimeDaysShiftImpl(), actual2);
  }

  public static class DateTimeDaysShiftImpl implements DateTimeDaysShift<DateTimeDaysShiftImpl> {

    @Override
    public int hashCode() {
      int hash = 5;
      hash = 79 * hash + Objects.hashCode(this.value);
      return hash;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      final DateTimeDaysShiftImpl other = (DateTimeDaysShiftImpl) obj;
      if (!Objects.equals(this.value, other.value)) {
        return false;
      }
      return true;
    }

    private final LocalDateTime value;

    public DateTimeDaysShiftImpl() {
      this.value = null;
    }

    public DateTimeDaysShiftImpl(LocalDateTime value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDateTime> getNullableValue() {
      return Optional.ofNullable(value);
    }

  }

}
