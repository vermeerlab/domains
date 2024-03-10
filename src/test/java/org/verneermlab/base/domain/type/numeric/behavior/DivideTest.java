package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class DivideTest {

  @Test
  public void testDivide_GenericType() {
    var impl1 = new DivideImpl(BigDecimal.TEN);
    var impl2 = new DivideImpl(BigDecimal.TEN);

    DivideImpl result1 = impl1.divide(impl2);
    Assertions.assertEquals(new DivideImpl(BigDecimal.ONE), result1);

    var impl3 = new DivideImpl();
    DivideImpl result2 = impl1.divide(impl3);
    Assertions.assertEquals(new DivideImpl(BigDecimal.ZERO), result2);

    var impl4 = new DivideImpl(new BigDecimal("5"));
    var impl5 = new DivideImpl(new BigDecimal("10"));

    DivideImpl result3 = impl4.divide(impl5);
    Assertions.assertEquals("1", result3.getOrZero().toPlainString());

  }

  @Test
  public void testDivide_scale() {
    var impl1 = new DivideImpl(new BigDecimal("2.5"));
    var impl2 = new DivideImpl(new BigDecimal("10"));

    DivideImpl result1 = impl1.divide(impl2, 2);
    Assertions.assertEquals("0.25", result1.getOrZero().toPlainString());

  }

  @Test
  public void testDivide_RoundingMode() {
    var impl1 = new DivideImpl(new BigDecimal("2.5"));
    var impl2 = new DivideImpl(new BigDecimal("10"));

    DivideImpl result = impl1.divide(impl2, RoundingMode.HALF_DOWN);
    Assertions.assertEquals("0", result.getOrZero().toPlainString());

    DivideImpl result2 = impl1.divide(10, RoundingMode.HALF_DOWN);
    Assertions.assertEquals("0", result2.getOrZero().toPlainString());

  }

  @Test
  public void testDivide_3args_1() {
    var impl1 = new DivideImpl(new BigDecimal("2.5"));
    var impl2 = new DivideImpl(new BigDecimal("10"));

    DivideImpl result = impl1.divide(impl2, 1, RoundingMode.HALF_UP);
    Assertions.assertEquals("0.3", result.getOrZero().toPlainString());
  }

  @Test
  public void testDivide_3args_2() {
    var impl1 = new DivideImpl(new BigDecimal("2.5"));

    DivideImpl result1 = impl1.divide(10, 1, RoundingMode.HALF_UP);
    Assertions.assertEquals("0.3", result1.getOrZero().toPlainString());

    DivideImpl result2 = impl1.divide(0, 1, RoundingMode.HALF_UP);
    Assertions.assertEquals("0", result2.getOrZero().toPlainString());

    Integer intValue = null;
    assertThrows(NullPointerException.class, () -> impl1.divide(intValue, 1, RoundingMode.HALF_UP));

  }

  @Test
  public void testDivide_GenericType_Function() {
    var impl1 = new DivideImpl(BigDecimal.TEN);
    var impl2 = new DivideImpl(BigDecimal.TEN);

    DivideImpl2 result1 = impl1.divide(impl2, DivideImpl2::of);
    Assertions.assertEquals(new DivideImpl2(BigDecimal.ONE), result1);
  }

  @Test
  public void testDivide_scale_Function() {
    var impl1 = new DivideImpl(new BigDecimal("2.5"));
    var impl2 = new DivideImpl(new BigDecimal("10"));

    DivideImpl2 result1 = impl1.divide(impl2, 2, DivideImpl2::of);
    Assertions.assertEquals("0.25", result1.getOrZero().toPlainString());
  }

  @Test
  public void testDivide_RoundingMode_Function() {
    var impl1 = new DivideImpl(new BigDecimal("2.5"));
    var impl2 = new DivideImpl(new BigDecimal("10"));

    DivideImpl2 result = impl1.divide(impl2, RoundingMode.HALF_DOWN, DivideImpl2::of);
    Assertions.assertEquals("0", result.getOrZero().toPlainString());

  }

  @Test
  public void testDivide_4args_Function() {
    var impl1 = new DivideImpl(new BigDecimal("2.5"));
    var impl2 = new DivideImpl(new BigDecimal("10"));

    DivideImpl2 result1 = impl1.divide(impl2, 1, RoundingMode.HALF_UP, DivideImpl2::of);
    Assertions.assertEquals("0.3", result1.getOrZero().toPlainString());

    Integer intValue = null;
    assertThrows(NullPointerException.class, () -> impl1.divide(intValue, 1, RoundingMode.HALF_UP));

    var impl3 = new DivideImpl(new BigDecimal("0"));
    DivideImpl2 result2 = impl1.divide(impl3, 1, RoundingMode.HALF_UP, DivideImpl2::of);
    Assertions.assertEquals("0", result2.getOrZero().toPlainString());

  }

  static class DivideImpl implements Divide<DivideImpl> {

    @Override
    public RoundingMode getRoundingMode() {
      return RoundingMode.HALF_UP;
    }

    private final BigDecimal value;

    DivideImpl() {
      this.value = null;
    }

    DivideImpl(BigDecimal value) {
      this.value = value;
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(value);
    }

    @Override
    public int hashCode() {
      int hash = 7;
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
      final DivideImpl other = (DivideImpl) obj;
      return Objects.equals(this.getOrZero(), other.getOrZero());
    }

  }

  static class DivideImpl2 implements Divide<DivideImpl2> {

    @Override
    public RoundingMode getRoundingMode() {
      return RoundingMode.HALF_UP;
    }

    private final BigDecimal value;

    DivideImpl2() {
      this.value = null;
    }

    DivideImpl2(BigDecimal value) {
      this.value = value;
    }

    static DivideImpl2 of(BigDecimal value) {
      return new DivideImpl2(value);
    }

    @Override
    public Optional<BigDecimal> getNullableValue() {
      return Optional.ofNullable(value);
    }

    @Override
    public int hashCode() {
      int hash = 7;
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
      final DivideImpl2 other = (DivideImpl2) obj;
      return Objects.equals(this.getOrZero(), other.getOrZero());
    }

  }

}
