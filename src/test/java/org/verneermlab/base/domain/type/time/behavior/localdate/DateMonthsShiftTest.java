package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateMonthsShiftTest {

  @Test
  public void testBeginMonth() {
    DateMonthsShiftImpl actual = new DateMonthsShiftImpl(LocalDate.of(2024, Month.MARCH, 3)).beginMonth();
    Assertions.assertEquals(new DateMonthsShiftImpl(LocalDate.of(2024, Month.MARCH, 1)), actual);

    DateMonthsShiftImpl actualNull = new DateMonthsShiftImpl().beginMonth();
    Assertions.assertEquals(new DateMonthsShiftImpl(), actualNull);

  }

  @Test
  public void testEndMonth() {
    DateMonthsShiftImpl actual1 = new DateMonthsShiftImpl(LocalDate.of(2024, Month.MARCH, 3)).endMonth();
    Assertions.assertEquals(new DateMonthsShiftImpl(LocalDate.of(2024, Month.MARCH, 31)), actual1);

    DateMonthsShiftImpl actual2 = new DateMonthsShiftImpl(LocalDate.of(2024, Month.FEBRUARY, 3)).endMonth();
    Assertions.assertEquals(new DateMonthsShiftImpl(LocalDate.of(2024, Month.FEBRUARY, 29)), actual2);

    DateMonthsShiftImpl actualNull = new DateMonthsShiftImpl().endMonth();
    Assertions.assertEquals(new DateMonthsShiftImpl(), actualNull);
  }

  public static class DateMonthsShiftImpl implements DateMonthsShift<DateMonthsShiftImpl> {

    private final LocalDate value;

    public DateMonthsShiftImpl() {
      this.value = null;
    }

    public DateMonthsShiftImpl(LocalDate value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDate> getNullableValue() {
      return Optional.ofNullable(value);
    }

    @Override
    public int hashCode() {
      int hash = 3;
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
      final DateMonthsShiftImpl other = (DateMonthsShiftImpl) obj;
      if (!Objects.equals(this.value, other.value)) {
        return false;
      }
      return true;
    }

  }

}
