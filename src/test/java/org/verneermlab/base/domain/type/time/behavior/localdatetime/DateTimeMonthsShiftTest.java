package org.verneermlab.base.domain.type.time.behavior.localdatetime;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Yamashita.Takahiro
 */
public class DateTimeMonthsShiftTest {

  public DateTimeMonthsShiftTest() {
  }

  @Test
  public void testBeginMonth() {
    DateTimeMonthsShiftImpl actual = new DateTimeMonthsShiftImpl(LocalDateTime.of(2024, Month.MARCH, 3, 1, 2, 3)).beginMonth();
    Assertions.assertEquals(new DateTimeMonthsShiftImpl(LocalDateTime.of(2024, Month.MARCH, 1, 0, 0, 0)), actual);

    DateTimeMonthsShiftImpl actualNull = new DateTimeMonthsShiftImpl().beginMonth();
    Assertions.assertEquals(new DateTimeMonthsShiftImpl(), actualNull);
  }

  @Test
  public void testEndMonth() {
    DateTimeMonthsShiftImpl actual = new DateTimeMonthsShiftImpl(LocalDateTime.of(2024, Month.MARCH, 3, 1, 2, 3)).endMonth();
    Assertions.assertEquals(new DateTimeMonthsShiftImpl(LocalDateTime.of(2024, Month.MARCH, 31, 23, 59, 59, 999999999)), actual);

    DateTimeMonthsShiftImpl actualNull = new DateTimeMonthsShiftImpl().endMonth();
    Assertions.assertEquals(new DateTimeMonthsShiftImpl(), actualNull);
  }

  public static class DateTimeMonthsShiftImpl implements DateTimeMonthsShift<DateTimeMonthsShiftImpl> {

    private final LocalDateTime value;

    public DateTimeMonthsShiftImpl() {
      this.value = null;
    }

    public DateTimeMonthsShiftImpl(LocalDateTime value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDateTime> getNullableValue() {
      return Optional.ofNullable(value);
    }

    @Override
    public int hashCode() {
      int hash = 5;
      hash = 59 * hash + Objects.hashCode(this.value);
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
      final DateTimeMonthsShiftImpl other = (DateTimeMonthsShiftImpl) obj;
      if (!Objects.equals(this.value, other.value)) {
        return false;
      }
      return true;
    }

  }

}
