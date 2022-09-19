package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class BigDecimalCalculatorTest {

  public BigDecimalCalculatorTest() {
  }

  @Test
  public void testBuilder_BigDecimal() {
    BigDecimal param = BigDecimal.TEN;

    var builder = BigDecimalCalculator.builder(param);
    var calc = builder.build();

    assertEquals(BigDecimal.TEN, calc.toBigDecimal());
  }

  @Test
  public void testBuilder_Integer() {
    Integer param = 99;

    var builder = BigDecimalCalculator.builder(param);
    var calc = builder.build();

    assertEquals(99, calc.toInt());
  }

  @Test
  public void testBuilder_Long() {
    Long param = 100L;

    var builder = BigDecimalCalculator.builder(param);
    var calc = builder.build();

    assertEquals(100L, calc.toLong());
  }

  @Test
  public void testBuilder_String() {
    String param = "200";

    var builder = BigDecimalCalculator.builder(param);
    var calc = builder.build();

    assertEquals(200, calc.toInt());
  }

  @Test
  public void testGetScale() {
    String param = "200";
    var builder = BigDecimalCalculator.builder(param);
    var calc = builder.build();

    assertEquals(0, calc.getScale());
  }

  @Test
  public void testGetScaleDefault() {
    String param = "200";
    var builder = BigDecimalCalculator.builder(param).scale(2);
    var calc = builder.build();

    assertEquals(2, calc.getScale());
  }

  @Test
  public void testGetRoundingMode() {
    String param = "200";
    var builder = BigDecimalCalculator.builder(param);
    var calc = builder.build();

    assertEquals(RoundingMode.DOWN, calc.getRoundingMode());
  }

  @Test
  public void testPlus() {
    String param = "200";
    var builder1 = BigDecimalCalculator.builder(param);
    var calc1 = builder1.build();

    var builder2 = BigDecimalCalculator.builder(param);
    var calc2 = builder2.build();

    var actual = calc1.plus(calc2);
    assertEquals(BigDecimalCalculator.builder("400").build(), actual);

  }

  @Test
  public void testPlusMulti() {
    String param1 = "100";
    var builder1 = BigDecimalCalculator.builder(param1);
    var calc1 = builder1.build();

    String param2 = "200";
    var builder2 = BigDecimalCalculator.builder(param2);
    var calc2 = builder2.build();

    String param3 = "300";
    var builder3 = BigDecimalCalculator.builder(param3);
    var calc3 = builder3.build();

    var actual = calc1.plus(calc2, calc3);
    assertEquals(600, actual.toInt());

  }

  @Test
  public void testMinus() {
    String param = "200";
    var builder1 = BigDecimalCalculator.builder(param);
    var calc1 = builder1.build();

    var builder2 = BigDecimalCalculator.builder(param);
    var calc2 = builder2.build();

    var actual = calc1.minus(calc2);
    assertEquals(BigDecimalCalculator.builder("0").build(), actual);
  }

  @Test
  public void testMinusMulti() {
    String param1 = "100";
    var builder1 = BigDecimalCalculator.builder(param1);
    var calc1 = builder1.build();

    String param2 = "200";
    var builder2 = BigDecimalCalculator.builder(param2);
    var calc2 = builder2.build();

    String param3 = "300";
    var builder3 = BigDecimalCalculator.builder(param3);
    var calc3 = builder3.build();

    var actual = calc1.minus(calc2, calc3);
    assertEquals(-400, actual.toInt());

  }

  @Test
  public void testMultiply() {
    String param = "200";
    var builder1 = BigDecimalCalculator.builder(param);
    var calc1 = builder1.build();

    var builder2 = BigDecimalCalculator.builder(param);
    var calc2 = builder2.build();

    var actual = calc1.multiply(calc2);
    assertEquals(BigDecimalCalculator.builder("40000").build(), actual);
  }

  @Test
  public void testDivide() {
    String param = "200";
    var builder1 = BigDecimalCalculator.builder(param);
    var calc1 = builder1.build();

    var builder2 = BigDecimalCalculator.builder(param);
    var calc2 = builder2.build();

    var actual = calc1.divide(calc2);
    assertEquals(BigDecimalCalculator.builder("1").build(), actual);
  }

  @Test
  public void testZeroDivide() {
    String param = "200";
    var builder1 = BigDecimalCalculator.builder(param);
    var calc1 = builder1.build();

    var builder2 = BigDecimalCalculator.builder("0");
    var calc2 = builder2.build();

    var actual1 = calc1.divide(calc2);
    assertEquals(BigDecimalCalculator.builder("0").build(), actual1);

    var actual2 = calc2.divide(calc1);
    assertEquals(BigDecimalCalculator.builder("0").build(), actual2);
  }

  @Test
  public void testDivideOverrideDefaultScale() {
    String param1 = "100";
    var builder1 = BigDecimalCalculator.builder(param1).scale(1);
    var calc1 = builder1.build();

    String param2 = "200";
    var builder2 = BigDecimalCalculator.builder(param2);
    var calc2 = builder2.build();

    var actual = calc1.divide(calc2);
    assertEquals(BigDecimalCalculator.builder("0.5").build(), actual);
  }

  @Test
  public void testDivideOverrideDefaultScaleNext() {
    String param1 = "100";
    var builder1 = BigDecimalCalculator.builder(param1);
    var calc1 = builder1.build();

    String param2 = "200";
    var builder2 = BigDecimalCalculator.builder(param2).scale(1);
    var calc2 = builder2.build();

    //　計算元となる定義が有効になります
    var actual1 = calc1.divide(calc2);
    assertEquals(BigDecimalCalculator.builder("0").build(), actual1);

    //　計算元となる定義（scale）が有効になります
    var actual2 = calc2.divide(calc1);
    assertEquals(BigDecimalCalculator.builder("2.0").build(), actual2);

  }

  @Test
  public void testDivideRoundingMode() {
    String param1 = "100";
    var builder1 = BigDecimalCalculator.builder(param1);
    var calc1 = builder1.build();

    String param2 = "200";
    var builder2 = BigDecimalCalculator.builder(param2);
    var calc2 = builder2.build();

    var actual = calc1.divide(calc2, RoundingMode.UP);
    assertEquals(BigDecimalCalculator.builder("1").build(), actual);
  }

  @Test
  public void testDivideScale() {
    String param1 = "100";
    var builder1 = BigDecimalCalculator.builder(param1);
    var calc1 = builder1.build();

    String param2 = "200";
    var builder2 = BigDecimalCalculator.builder(param2);
    var calc2 = builder2.build();

    var actual = calc1.divide(calc2, 2, RoundingMode.CEILING);
    assertEquals(BigDecimalCalculator.builder("0.50").build(), actual);
  }

  @Test
  public void testPlusAllList() {
    String param1 = "100";
    var builder1 = BigDecimalCalculator.builder(param1);
    var calc1 = builder1.build();

    String param2 = "200";
    var builder2 = BigDecimalCalculator.builder(param2);
    var calc2 = builder2.build();

    String param3 = "300";
    var builder3 = BigDecimalCalculator.builder(param3);
    var calc3 = builder3.build();

    var actual = calc1.plusAll(List.of(calc2, calc3));
    assertEquals(600, actual.toInt());
  }

  @Test
  public void testIsEmpty() {
    Long value = null;
    var calc1 = BigDecimalCalculator.builder(value).build();
    assertTrue(calc1.isEmpty());
  }

  @Test
  public void testEquals() {
    String param = "200";
    var builder1 = BigDecimalCalculator.builder(param);
    var calc1 = builder1.build();

    var builder2 = BigDecimalCalculator.builder(param);
    var calc2 = builder2.build();
    assertEquals(calc1, calc2);
  }

  @Test
  public void testNotEquals() {
    String param1 = "200";
    var builder1 = BigDecimalCalculator.builder(param1);
    var calc1 = builder1.build();

    String param2 = "100";
    var builder2 = BigDecimalCalculator.builder(param2);
    var calc2 = builder2.build();
    assertNotEquals(calc1, calc2);
  }

  @Test
  public void testCoverage() {
    var calc = BigDecimalCalculator.builder("1").build();
    calc.hashCode();
    assertEquals(calc, calc);
    assertNotEquals(calc, null);
    assertNotEquals(calc, "");

    String nonValue = null;
    var calcNull1 = BigDecimalCalculator.builder(nonValue).build();
    var calcNull2 = BigDecimalCalculator.builder(nonValue).build();
    assertEquals(calcNull1, calcNull2);

  }

}
