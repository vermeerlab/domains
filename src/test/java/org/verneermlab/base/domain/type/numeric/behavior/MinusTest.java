package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MinusTest {

  @Test
  public void testMinus() {
    var value1 = new MinusImpl(new BigDecimal(10));
    var value2 = new MinusImpl(new BigDecimal(20));
    var actual1 = value1.minus(value2);
    assertEquals(-10, actual1.getOrZero().intValue());

    var actual2 = value2.minus(value1, value1);
    assertEquals(0, actual2.getOrZero().intValue());
  }

  static class MinusImpl implements Minus<MinusImpl> {

    private final BigDecimal value;

    public MinusImpl() {
      this.value = null;
    }

    public MinusImpl(BigDecimal value) {
      this.value = value;
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
