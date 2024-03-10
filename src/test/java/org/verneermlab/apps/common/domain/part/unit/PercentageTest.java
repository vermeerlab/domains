package org.verneermlab.apps.common.domain.part.unit;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PercentageTest {

  @Test
  public void testOf_BigDecimal() {
    var percentage = Percentage.of(new BigDecimal("10.01"));
    assertEquals(new BigDecimal("0.10010"), percentage.getOrZero());
    assertEquals(new BigDecimal("10.010"), percentage.toPercent());
  }

  @Test
  public void testOf_Integer() {
    var percentage = Percentage.of(99);
    assertEquals(0, percentage.getOrZero().intValue());
    assertEquals(new BigDecimal("99.000"), percentage.toPercent());
  }

  @Test
  public void testOf_Long() {
    var percentage = Percentage.of(10L);
    assertEquals(0, percentage.getOrZero().longValue());
    assertEquals(new BigDecimal("10.000"), percentage.toPercent());
  }

  @Test
  public void testOf_float() {
    var percentage = Percentage.of(Float.valueOf("10.11"));
    assertEquals(Float.valueOf("0.1011"), percentage.getOrZero().floatValue());
  }

  @Test
  public void testOf_double() {
    var percentage = Percentage.of(Double.valueOf("10.11"));
    assertEquals(Double.valueOf("0.1011"), percentage.getOrZero().doubleValue());
  }

  @Test
  public void testOf_String() {
    var percentage1 = Percentage.of("10.01");

    assertEquals(new BigDecimal("0.10010"), percentage1.getOrZero());
    assertEquals(new BigDecimal("10.010"), percentage1.toPercent());

    var percentage2 = Percentage.of("");
    assertEquals(BigDecimal.ZERO, percentage2.getOrZero());
    assertTrue(percentage2.isEmpty());

  }

  @Test
  public void testOfDecimal() {
    var percentage1 = Percentage.ofDecimal("0.1001");
    assertEquals("0.10010", percentage1.getOrZero().toPlainString());
    assertEquals("10.010", percentage1.toPercent().toPlainString());

    var percentage2 = Percentage.ofDecimal("0.100145");
    assertEquals("0.10015", percentage2.getOrZero().toPlainString());
    assertEquals("10.015", percentage2.toPercent().toPlainString());

    Integer nullInt = null;
    var percentage3 = Percentage.ofDecimal(nullInt);
    assertTrue(percentage3.isEmpty());

    var percentage4 = Percentage.ofDecimal("");
    assertTrue(percentage4.isEmpty());

    String strNull = null;
    var percentage5 = Percentage.ofDecimal(strNull);
    assertTrue(percentage5.isEmpty());
  }

  @Test
  public void testMultiply() {
    var percentage1 = Percentage.of("10.122");
    var percentage2 = Percentage.of("20.2");
    Percentage actual1 = percentage1.multiply(percentage2);
    assertEquals(Percentage.of("2.044644"), actual1);
    assertNotEquals("204.4644", actual1.getOrZero().toPlainString());

    var percentage3 = Percentage.of("10.1225");
    var percentage4 = Percentage.of("20.2");
    var actual2 = percentage3.multiply(percentage4);
    assertEquals(Percentage.of("2.04485"), actual2);
    assertNotEquals("204.4846", actual2.getOrZero().toPlainString());

    Integer nullInt = null;
    assertThrows(NullPointerException.class, () -> Percentage.of(0).multiply(nullInt));

  }

  @Test
  public void testQuantityPercentage() {
    var percentage = Percentage.of(10);
    var quantity = Quantity.of(20);

    //百分率なので、％表記
    assertEquals(2, percentage.multiply(quantity).getOrZero().intValue());
    assertEquals(Percentage.of(200), percentage.multiply(quantity));

    //量は％表記の乗算
    assertEquals(2, quantity.multiply(percentage).getOrZero().intValue());
    assertEquals(Quantity.of(2), quantity.multiply(percentage));
  }

  @Test
  public void testIsEmpty() {
    Integer value1 = null;
    var actual1 = Percentage.of(value1);
    assertTrue(actual1.isEmpty());

    Long value2 = null;
    var actual2 = Percentage.of(value2);
    assertTrue(actual2.isEmpty());

    BigDecimal value3 = null;
    var actual3 = Percentage.of(value3);
    assertTrue(actual3.isEmpty());

    String value4 = null;
    var actual4 = Percentage.of(value4);
    assertTrue(actual4.isEmpty());

    var actual5 = Percentage.ofDecimal(value4);
    assertTrue(actual5.isEmpty());
  }

  @Test
  public void testEquals() {
    var percentage1 = Percentage.ofDecimal("0.1001");
    var percentage2 = Percentage.ofDecimal("0.1001");
    assertEquals(percentage1, percentage2);

    var percentage3 = Percentage.ofDecimal("0.1002");
    assertNotEquals(percentage1, percentage3);

  }

  @Test
  public void testToString() {
    var actual1 = Percentage.of(111);
    assertEquals("1.11000", actual1.toString());

  }

  @Test
  public void testCoverage() {
    var obj = Percentage.of(1);
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }
}
