package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumericComparatorTest {

  @Test
  public void testEq() {
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ONE).eq(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.ONE).eq(new NumericComparatorImpl(BigDecimal.TEN)));
    Assertions.assertTrue(new NumericComparatorImpl(null).eq(new NumericComparatorImpl(BigDecimal.ZERO)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ZERO).eq(new NumericComparatorImpl(null)));
    Assertions.assertTrue(new NumericComparatorImpl(null).eq(new NumericComparatorImpl(null)));
  }

  @Test
  public void testNe() {
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.ONE).ne(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ONE).ne(new NumericComparatorImpl(BigDecimal.TEN)));
    Assertions.assertFalse(new NumericComparatorImpl(null).ne(new NumericComparatorImpl(BigDecimal.ZERO)));
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.ZERO).ne(new NumericComparatorImpl(null)));
    Assertions.assertFalse(new NumericComparatorImpl(null).ne(new NumericComparatorImpl(null)));
  }

  @Test
  public void testLt() {
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ONE).lt(new NumericComparatorImpl(BigDecimal.TEN)));
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.TEN).lt(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertFalse(new NumericComparatorImpl(null).lt(new NumericComparatorImpl(BigDecimal.ZERO)));
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.ZERO).lt(new NumericComparatorImpl(null)));
    Assertions.assertFalse(new NumericComparatorImpl(null).lt(new NumericComparatorImpl(null)));
  }

  @Test
  public void testLe() {
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ONE).le(new NumericComparatorImpl(BigDecimal.TEN)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ONE).le(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.TEN).le(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertTrue(new NumericComparatorImpl(null).le(new NumericComparatorImpl(BigDecimal.ZERO)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ZERO).le(new NumericComparatorImpl(null)));
    Assertions.assertTrue(new NumericComparatorImpl(null).le(new NumericComparatorImpl(null)));
  }

  @Test
  public void testGt() {
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.ONE).gt(new NumericComparatorImpl(BigDecimal.TEN)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.TEN).gt(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertFalse(new NumericComparatorImpl(null).gt(new NumericComparatorImpl(BigDecimal.ZERO)));
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.ZERO).gt(new NumericComparatorImpl(null)));
    Assertions.assertFalse(new NumericComparatorImpl(null).gt(new NumericComparatorImpl(null)));
  }

  @Test
  public void testGe() {
    Assertions.assertFalse(new NumericComparatorImpl(BigDecimal.ONE).ge(new NumericComparatorImpl(BigDecimal.TEN)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ONE).ge(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.TEN).ge(new NumericComparatorImpl(BigDecimal.ONE)));
    Assertions.assertTrue(new NumericComparatorImpl(null).ge(new NumericComparatorImpl(BigDecimal.ZERO)));
    Assertions.assertTrue(new NumericComparatorImpl(BigDecimal.ZERO).ge(new NumericComparatorImpl(null)));
    Assertions.assertTrue(new NumericComparatorImpl(null).ge(new NumericComparatorImpl(null)));
  }

  public class NumericComparatorImpl implements NumericComparator<NumericComparatorImpl> {

    private final BigDecimal value;

    public NumericComparatorImpl(BigDecimal value) {
      this.value = value;
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(this.value);
    }
  }

}
