package org.verneermlab.apps.common.domain.part.unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Yamashita.Takahiro
 */
public class QuantityTest {

  @Test
  public void testOf_BigDecimal() {
    var quantity = Quantity.of(BigDecimal.TEN);
    assertNotEquals(BigDecimal.TEN, quantity.getOrZero());
    assertEquals(new BigDecimal("10.00"), quantity.getOrZero());
  }

  @Test
  public void testOf_Integer() {
    var quantity = Quantity.of(99);
    assertEquals(99, quantity.getOrZero().intValue());
  }

  @Test
  public void testOf_Long() {
    var quantity = Quantity.of(100L);
    assertEquals(100L, quantity.getOrZero().longValue());
  }

  @Test
  public void testOfNonFormat() {
    var quantity = Quantity.of("1000.01");
    assertEquals(1000, quantity.getOrZero().intValue());
    assertEquals(new BigDecimal("1000.01"), quantity.getOrZero());
  }

  @Test
  public void testOfFormatted() {
    var quantity1 = Quantity.ofFormatted("2,000");
    assertEquals(2000, quantity1.getOrZero().intValue());

    var quantity2 = Quantity.ofFormatted("2,000,000");
    assertEquals(2000000, quantity2.getOrZero().intValue());

    assertThrows(NumberFormatException.class, () -> Quantity.ofFormatted("abc"));

  }

  @Test
  public void testToNonFormat() {
    var quantity = Quantity.ofFormatted("2,000.11");
    assertEquals("2000.11", quantity.getOrZero().toPlainString());
  }

  @Test
  public void testToFormatted() {
    var quantity = Quantity.of("1000.01");
    assertEquals("1,000.01", quantity.toFormatted());
  }

  @Test
  public void testPercent() {
    var quantity1 = Quantity.of("10");
    var quantity2 = Quantity.of("2");

    Percentage actual2 = quantity1.percent(quantity2);
    assertEquals(Percentage.of(500), actual2);
  }

  @Test
  public void testPercent_scale() {
    var quantity1 = Quantity.of("2");
    var quantity2 = Quantity.of("10");

    Percentage actual2 = quantity1.percent(quantity2, 2);
    assertEquals(Percentage.of(20.00), actual2);
  }

  @Test
  public void testPercentRoundingMode() {
    var quantity1 = Quantity.of("5");
    var quantity2 = Quantity.of("10");

    Percentage actual = quantity1.percent(quantity2, RoundingMode.UP);
    assertEquals(Percentage.of(50), actual);
  }

  @Test
  public void testPercentScaleRoundingMode() {
    var quantity1 = Quantity.of("5");
    var quantity2 = Quantity.of("10");

    Percentage actual1 = quantity1.percent(quantity2, 1, RoundingMode.UP);
    assertEquals(Percentage.of("50"), actual1);

    var quantity3 = Quantity.of("20");
    Percentage actual2 = quantity1.percent(quantity3, 1, RoundingMode.UP);
    assertEquals(Percentage.of("30"), actual2);
    assertNotEquals(Percentage.of("25"), actual2);

  }

  @Test
  public void testPercentToPercentage() {
    var quantity1 = Quantity.of("10");
    var quantity2 = Quantity.of("2");

    // 戻り値の型 Percentage のためのメソッドを準備
    Percentage actual2 = quantity1.percent(quantity2);
    assertEquals(Percentage.of(500), actual2);
  }

  @Test
  public void testPercentToPercentageRoundingMode() {
    var quantity1 = Quantity.of("2");
    var quantity2 = Quantity.of("5");

    //scaleは計算元のデフォルトが適用される
    Percentage actual = quantity1.percent(quantity2, RoundingMode.UP);
    assertEquals(Percentage.of(40), actual);
  }

  @Test
  public void testPercentToPercentageScaleRoundingMode() {
    var quantity1 = Quantity.of("2");
    var quantity2 = Quantity.of("5");

    Percentage actual = quantity1.percent(quantity2, 2, RoundingMode.UP);
    assertEquals(Percentage.of(40), actual);
  }

  @Test
  public void testIsEmpty() {
    Integer value1 = null;
    var actual1 = Quantity.of(value1);
    assertTrue(actual1.isEmpty());

    Long value2 = null;
    var actual2 = Quantity.of(value2);
    assertTrue(actual2.isEmpty());

    BigDecimal value3 = null;
    var actual3 = Quantity.of(value3);
    assertTrue(actual3.isEmpty());

    String value4 = null;
    var actual4 = Quantity.ofFormatted(value4);
    assertTrue(actual4.isEmpty());

    var actual5 = Quantity.of(value4);
    assertTrue(actual5.isEmpty());

  }

  @Test
  public void testEquals() {
    var quantity1 = Quantity.ofFormatted("2,000");
    var quantity2 = Quantity.ofFormatted("2,000");
    assertEquals(quantity1, quantity2);

    var quantity3 = Quantity.ofFormatted("3,000");
    assertNotEquals(quantity1, quantity3);

  }

  @Test
  public void testToString() {
    var actual1 = Quantity.of(111);
    assertEquals("111.00", actual1.toString());

  }

  @Test
  public void testCoverage() {
    var obj = Quantity.of(1);
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }
}
