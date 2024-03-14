package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateMonthsRangeTest {

  @Test
  public void testRangeMonth_GenericType() {
    var before = new DateMonthsRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateMonthsRangeImpl(LocalDate.of(2024, Month.APRIL, 10));

    Assertions.assertEquals(1, before.rangeMonths(after));
    Assertions.assertEquals(-1, after.rangeMonths(before));

    var nullImpl = new DateMonthsRangeImpl(null);
    Assertions.assertEquals(0, nullImpl.rangeMonths(after));
    Assertions.assertEquals(0, before.rangeMonths(nullImpl));

  }

  @Test
  public void testRangeMonth_GenericType_Function() {
    var before = new DateMonthsRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateMonthsRangeImpl(LocalDate.of(2024, Month.APRIL, 10));

    Assertions.assertEquals(new Months(1), before.rangeMonths(after, Months::new).get());
    Assertions.assertEquals(new Months(-1), after.rangeMonths(before, Months::new).get());

    var nullImpl = new DateMonthsRangeImpl(null);
    Assertions.assertEquals(Optional.empty(), nullImpl.rangeMonths(after, Months::new));
    Assertions.assertEquals(Optional.empty(), before.rangeMonths(nullImpl, Months::new));
  }

  @Test
  public void testRangeMonth_3args() {
    var before = new DateMonthsRangeImpl(LocalDate.of(2024, Month.MARCH, 1));
    var after = new DateMonthsRangeImpl(LocalDate.of(2024, Month.APRIL, 10));

    Assertions.assertEquals(new Months(1), before.rangeMonths(after, () -> new Months(0), Months::new));
    Assertions.assertEquals(new Months(-1), after.rangeMonths(before, () -> new Months(0), Months::new));

    var nullImpl = new DateMonthsRangeImpl(null);
    Assertions.assertEquals(new Months(0), nullImpl.rangeMonths(after, () -> new Months(0), Months::new));
    Assertions.assertEquals(new Months(0), before.rangeMonths(nullImpl, () -> new Months(0), Months::new));
  }

  @Test
  public void testRangeMonthsHalfUp_GenericType() {
    var actual = new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 1));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 1))));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 2))));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 3))));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 4))));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 5))));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 6))));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 7))));
    Assertions.assertEquals(new BigDecimal(0), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 8))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 9))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 10))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 11))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 12))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 13))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 14))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 15))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 16))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 17))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 18))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 19))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 20))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 21))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 22))));
    Assertions.assertEquals(new BigDecimal(0.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 23))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 24))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 25))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 26))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 27))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 28))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 29))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 30))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 31))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 1))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 2))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 3))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 4))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 5))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 6))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 7))));
    Assertions.assertEquals(new BigDecimal(1), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 8))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 9))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 10))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 11))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 12))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 13))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 14))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 15))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 16))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 17))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 18))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 19))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 20))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 21))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 22))));
    Assertions.assertEquals(new BigDecimal(1.5), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 23))));
    Assertions.assertEquals(new BigDecimal(2), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 24))));
    Assertions.assertEquals(new BigDecimal(2), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 25))));
    Assertions.assertEquals(new BigDecimal(2), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 26))));
    Assertions.assertEquals(new BigDecimal(2), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 27))));
    Assertions.assertEquals(new BigDecimal(2), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 28))));
    Assertions.assertEquals(new BigDecimal(2), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 29))));

    var nullImpl = new DateMonthsRangeImpl(null);
    Assertions.assertEquals(BigDecimal.ZERO, nullImpl.rangeMonthsHalfUp(actual));
    Assertions.assertEquals(BigDecimal.ZERO, actual.rangeMonthsHalfUp(nullImpl));
  }

  @Test
  public void testRangeMonthsHalfUp_GenericType_Function() {
    var actual = new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 1));

    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(0))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 1)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(0))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 8)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(0.5))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 9)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(0.5))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 23)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(1))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 24)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(1))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 8)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(1.5))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 9)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(1.5))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 23)), Month2::new));
    Assertions.assertEquals(Optional.of(new Month2(new BigDecimal(2))), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 24)), Month2::new));

    var nullImpl = new DateMonthsRangeImpl(null);
    Assertions.assertEquals(Optional.empty(), nullImpl.rangeMonthsHalfUp(actual, Month2::new));
    Assertions.assertEquals(Optional.empty(), actual.rangeMonthsHalfUp(nullImpl, Month2::new));
  }

  @Test
  public void testRangeMonthHalfUp_3args() {
    var actual = new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 1));

    Assertions.assertEquals(new Month2(new BigDecimal(0)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 1)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(0)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 8)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(0.5)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 9)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(0.5)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 23)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(1)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.JANUARY, 24)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(1)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 8)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(1.5)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 9)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(1.5)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 23)), () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(new BigDecimal(2)), actual.rangeMonthsHalfUp(new DateMonthsRangeImpl(LocalDate.of(2000, Month.FEBRUARY, 24)), () -> new Month2(BigDecimal.ZERO), Month2::new));

    var nullImpl = new DateMonthsRangeImpl(null);
    Assertions.assertEquals(new Month2(BigDecimal.ZERO), nullImpl.rangeMonthsHalfUp(actual, () -> new Month2(BigDecimal.ZERO), Month2::new));
    Assertions.assertEquals(new Month2(BigDecimal.ZERO), actual.rangeMonthsHalfUp(nullImpl, () -> new Month2(BigDecimal.ZERO), Month2::new));
  }

  public static class DateMonthsRangeImpl implements DateMonthsRange<DateMonthsRangeImpl> {

    private final LocalDate value;

    public DateMonthsRangeImpl(LocalDate value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDate> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

  public static class Months {

    private final long value;

    public Months(long value) {
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
      final Months other = (Months) obj;
      if (this.value != other.value) {
        return false;
      }
      return true;
    }

  }

  public static class Month2 {

    private final BigDecimal value;

    public Month2(BigDecimal value) {
      this.value = value;
    }

    public BigDecimal getValue() {
      return value;
    }

    @Override
    public int hashCode() {
      int hash = 3;
      hash = 67 * hash + Objects.hashCode(this.value);
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
      final Month2 other = (Month2) obj;
      if (!Objects.equals(this.value, other.value)) {
        return false;
      }
      return true;
    }

  }

}
