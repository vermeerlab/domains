package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class MultiplyTest {

  @Test
  public void testMultiply() {

    var value1 = new MultiplyImpl1(new BigDecimal("10.122"));
    var value2 = new MultiplyImpl1(new BigDecimal("20.2"));
    MultiplyImpl1 actual1 = value1.multiply(value2);
    assertEquals("204.464", actual1.getOrZero().toPlainString());
    assertNotEquals("204.4644", actual1.getOrZero().toPlainString());

    var value3 = new MultiplyImpl1(new BigDecimal("10.1225"));
    var value4 = new MultiplyImpl1(new BigDecimal("20.2"));
    var actual2 = value3.multiply(value4);
    assertEquals("204.485", actual2.getOrZero().toPlainString());
    assertNotEquals("204.4846", actual2.getOrZero().toPlainString());

    Integer nullInt = null;
    assertThrows(NullPointerException.class, () -> new MultiplyImpl1(BigDecimal.ZERO).multiply(nullInt));

  }

  @Test
  public void testMultiply_Function() {
    var value1 = new MultiplyImpl1(new BigDecimal("10.122"));
    var value2 = new MultiplyImpl2(new BigDecimal("20.2"));
    MultiplyImpl2 actual1 = value1.multiply(value2, value -> new MultiplyImpl2(value));
    assertEquals("204.464", actual1.getOrZero().toPlainString());
  }

  @Test
  public void testMultiply_2args_1() {
  }

  @Test
  public void testMultiply_2args_2() {
  }

  static class MultiplyImpl1 implements Multiply<MultiplyImpl1> {

    private final BigDecimal value;

    public MultiplyImpl1() {
      this.value = null;
    }

    public MultiplyImpl1(BigDecimal value) {
      this.value = value.setScale(3, RoundingMode.HALF_UP);
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

  static class MultiplyImpl2 implements Multiply<MultiplyImpl2> {

    private final BigDecimal value;

    public MultiplyImpl2() {
      this.value = null;
    }

    public MultiplyImpl2(BigDecimal value) {
      this.value = value.setScale(3, RoundingMode.HALF_UP);
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }
}
