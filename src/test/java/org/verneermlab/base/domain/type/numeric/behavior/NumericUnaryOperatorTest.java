package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NumericUnaryOperatorTest {

  @Test
  public void testApply() {

    var impl = new NumericUnaryOperatorImpl(BigDecimal.ONE);
    var actual1 = impl.apply(value -> {
      return value;
    });

    Assertions.assertEquals(impl.getNullableValue().get(), actual1.getNullableValue().get());
    Assertions.assertTrue(impl != actual1);

    var actual2 = impl.apply(value -> {
      return value.add(BigDecimal.ONE);
    });

    Assertions.assertNotEquals(impl.getNullableValue().get(), actual2.getNullableValue().get());
    Assertions.assertEquals(2, actual2.getNullableValue().get().intValue());

  }

  public static class NumericUnaryOperatorImpl implements NumericUnaryOperator<NumericUnaryOperatorImpl> {

    private final BigDecimal value;

    public NumericUnaryOperatorImpl(BigDecimal value) {
      this.value = value;
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
