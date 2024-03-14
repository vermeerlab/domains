package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateYearsRangeTest {

  @Test
  public void testRangeMonth_GenericType() {
    var before = new DateYearsRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateYearsRangeImpl(LocalDate.of(2026, Month.APRIL, 10));

    Assertions.assertEquals(2, before.rangeYears(after));
    Assertions.assertEquals(-2, after.rangeYears(before));

    var nullImpl = new DateYearsRangeImpl(null);
    Assertions.assertEquals(0, nullImpl.rangeYears(after));
    Assertions.assertEquals(0, before.rangeYears(nullImpl));

  }

  @Test
  public void testRangeMonth_GenericType_Function() {
    var before = new DateYearsRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateYearsRangeImpl(LocalDate.of(2026, Month.APRIL, 10));

    Assertions.assertEquals(new Years(2), before.rangeYears(after, Years::new).get());
    Assertions.assertEquals(new Years(-2), after.rangeYears(before, Years::new).get());

    var nullImpl = new DateYearsRangeImpl(null);
    Assertions.assertEquals(Optional.empty(), nullImpl.rangeYears(after, Years::new));
    Assertions.assertEquals(Optional.empty(), before.rangeYears(nullImpl, Years::new));
  }

  @Test
  public void testRangeMonth_3args() {
    var before = new DateYearsRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateYearsRangeImpl(LocalDate.of(2026, Month.APRIL, 10));

    Assertions.assertEquals(new Years(2), before.rangeYears(after, () -> new Years(0), Years::new));
    Assertions.assertEquals(new Years(-2), after.rangeYears(before, () -> new Years(0), Years::new));

    var nullImpl = new DateYearsRangeImpl(null);
    Assertions.assertEquals(new Years(0), nullImpl.rangeYears(after, () -> new Years(0), Years::new));
    Assertions.assertEquals(new Years(0), before.rangeYears(nullImpl, () -> new Years(0), Years::new));
  }

  public static class DateYearsRangeImpl implements DateYearsRange<DateYearsRangeImpl> {

    private final LocalDate value;

    public DateYearsRangeImpl(LocalDate value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDate> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

  public static class Years {

    private final long value;

    public Years(long value) {
      this.value = value;
    }

    public long getValue() {
      return value;
    }

    @Override
    public int hashCode() {
      int hash = 7;
      hash = 29 * hash + (int) (this.value ^ (this.value >>> 32));
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
      final Years other = (Years) obj;
      if (this.value != other.value) {
        return false;
      }
      return true;
    }

  }

}
