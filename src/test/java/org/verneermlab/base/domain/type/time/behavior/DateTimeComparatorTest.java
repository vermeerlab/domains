package org.verneermlab.base.domain.type.time.behavior;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateTimeComparatorTest {

  @Test
  public void testEq() {

    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .eq(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(null)
                    .eq(new DateTimeComparatorImpl(null)));
  }

  @Test
  public void testNe() {
    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))
                    .ne(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .ne(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(null)
                    .ne(new DateTimeComparatorImpl(null)));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))
                    .ne(new DateTimeComparatorImpl(null)));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(null)
                    .ne(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))));

  }

  @Test
  public void testLt() {
    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 2))
                    .lt(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .lt(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))
                    .lt(new DateTimeComparatorImpl(null)));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(null)
                    .lt(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))));

  }

  @Test
  public void testLe() {
    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 2))
                    .le(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))
                    .le(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .le(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))
                    .le(new DateTimeComparatorImpl(null)));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(null)
                    .le(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))));
  }

  @Test
  public void testGt() {
    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .gt(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 2))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 2))
                    .gt(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .gt(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))
                    .gt(new DateTimeComparatorImpl(null)));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(null)
                    .gt(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))));
  }

  @Test
  public void testGe() {
    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .ge(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 2))));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 2))
                    .ge(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))
                    .ge(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3))));

    Assertions.assertTrue(
            new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))
                    .ge(new DateTimeComparatorImpl(null)));

    Assertions.assertFalse(
            new DateTimeComparatorImpl(null)
                    .ge(new DateTimeComparatorImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 4))));
  }

  public static class DateTimeComparatorImpl implements DateTimeComparator<DateTimeComparatorImpl> {

    private LocalDateTime value;

    public DateTimeComparatorImpl(LocalDateTime value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDateTime> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
