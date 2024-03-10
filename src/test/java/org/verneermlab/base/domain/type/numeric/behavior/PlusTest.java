package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class PlusTest {

  @Test
  public void testPlus() {
    var value1 = new PlusImpl(new BigDecimal(10));
    var value2 = new PlusImpl(new BigDecimal(20));
    var actual1 = value1.plus(value2);
    assertEquals(30, actual1.getOrZero().intValue());

    var actual2 = value2.plus(value1, value1);
    assertEquals(40, actual2.getOrZero().intValue());
  }

  @Test
  public void testPlusAll() {
    var value1 = new PlusImpl(new BigDecimal(10));
    var value2 = new PlusImpl(new BigDecimal(20));
    var list = List.of(value1, value2);
    var actual1 = value1.plusAll(list);
    assertEquals(40, actual1.getOrZero().intValue());
  }

  static class PlusImpl implements Plus<PlusImpl> {

    private final BigDecimal value;

    public PlusImpl() {
      this.value = null;
    }

    public PlusImpl(BigDecimal value) {
      this.value = value;
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
