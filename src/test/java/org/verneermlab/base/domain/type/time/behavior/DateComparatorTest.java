package org.verneermlab.base.domain.type.time.behavior;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateComparatorTest {

  @Test
  public void testEq() {

    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))
                    .eq(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))));

    Assertions.assertTrue(
            new DateComparatorImpl(null)
                    .eq(new DateComparatorImpl(null)));
  }

  @Test
  public void testNe() {
    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))
                    .ne(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 2))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))
                    .ne(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))));

    Assertions.assertFalse(
            new DateComparatorImpl(null)
                    .ne(new DateComparatorImpl(null)));

    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))
                    .ne(new DateComparatorImpl(null)));

    Assertions.assertTrue(
            new DateComparatorImpl(null)
                    .ne(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))));

  }

  @Test
  public void testLt() {
    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 2))
                    .lt(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))
                    .lt(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))
                    .lt(new DateComparatorImpl(null)));

    Assertions.assertTrue(
            new DateComparatorImpl(null)
                    .lt(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))));

  }

  @Test
  public void testLe() {
    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 2))
                    .le(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 4))
                    .le(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))
                    .le(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))
                    .le(new DateComparatorImpl(null)));

    Assertions.assertTrue(
            new DateComparatorImpl(null)
                    .le(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 1))));
  }

  @Test
  public void testGt() {
    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))
                    .gt(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 2))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 2))
                    .gt(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))
                    .gt(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 4))
                    .gt(new DateComparatorImpl(null)));

    Assertions.assertFalse(
            new DateComparatorImpl(null)
                    .gt(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 4))));
  }

  @Test
  public void testGe() {
    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))
                    .ge(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 2))));

    Assertions.assertFalse(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 2))
                    .ge(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))
                    .ge(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 3))));

    Assertions.assertTrue(
            new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 4))
                    .ge(new DateComparatorImpl(null)));

    Assertions.assertFalse(
            new DateComparatorImpl(null)
                    .ge(new DateComparatorImpl(LocalDate.of(2024, Month.MARCH, 4))));
  }

  public static class DateComparatorImpl implements DateComparator<DateComparatorImpl> {

    private LocalDate value;

    public DateComparatorImpl(LocalDate value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDate> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
