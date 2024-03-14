package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateDaysRangeTest {

  @Test
  public void testRangeDays_GenericType() {
    var before = new DateDaysRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateDaysRangeImpl(LocalDate.of(2024, Month.APRIL, 10));

    Assertions.assertEquals(40, before.rangeDays(after));
    Assertions.assertEquals(-40, after.rangeDays(before));

    var nullImpl = new DateDaysRangeImpl(null);
    Assertions.assertEquals(0, nullImpl.rangeDays(after));
    Assertions.assertEquals(0, before.rangeDays(nullImpl));

  }

  @Test
  public void testRangeDays_GenericType_Function() {
    var before = new DateDaysRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateDaysRangeImpl(LocalDate.of(2024, Month.APRIL, 10));

    Assertions.assertEquals(new Days(40), before.rangeDays(after, Days::new).get());
    Assertions.assertEquals(new Days(-40), after.rangeDays(before, Days::new).get());

    var nullImpl = new DateDaysRangeImpl(null);
    Assertions.assertEquals(Optional.empty(), nullImpl.rangeDays(after, Days::new));
    Assertions.assertEquals(Optional.empty(), before.rangeDays(nullImpl, Days::new));

  }

  @Test
  public void testRangeDays_3args() {
    var before = new DateDaysRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateDaysRangeImpl(LocalDate.of(2024, Month.APRIL, 10));

    Assertions.assertEquals(new Days(40), before.rangeDays(after, () -> new Days(0), Days::new));
    Assertions.assertEquals(new Days(-40), after.rangeDays(before, () -> new Days(0), Days::new));

    var nullImpl = new DateDaysRangeImpl(null);
    Assertions.assertEquals(new Days(0), nullImpl.rangeDays(after, () -> new Days(0), Days::new));
    Assertions.assertEquals(new Days(0), before.rangeDays(nullImpl, () -> new Days(0), Days::new));
  }

  public static class DateDaysRangeImpl implements DateDaysRange<DateDaysRangeImpl> {

    private final LocalDate value;

    public DateDaysRangeImpl(LocalDate value) {
      this.value = value;
    }

    @Override
    public int hashCode() {
      int hash = 3;
      hash = 97 * hash + Objects.hashCode(this.value);
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
      final DateDaysRangeImpl other = (DateDaysRangeImpl) obj;
      if (!Objects.equals(this.value, other.value)) {
        return false;
      }
      return true;
    }

    @Override
    public Optional<LocalDate> getNullableValue() {
      return Optional.ofNullable(value);
    }

  }

  public static class Days {

    private final long value;

    public Days(long value) {
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
      final Days other = (Days) obj;
      if (this.value != other.value) {
        return false;
      }
      return true;
    }

  }

}
