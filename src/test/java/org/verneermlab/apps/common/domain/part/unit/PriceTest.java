package org.verneermlab.apps.common.domain.part.unit;

import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.verneermlab.apps.common.domain.part.numeric.NullableNumber;

public class PriceTest {

  @Test
  public void testOf_BigDecimal() {
    var price1 = Price.of(BigDecimal.TEN);
    assertEquals(BigDecimal.TEN, price1.getOrZero());

    var price2 = Price.of("10.11");
    assertEquals(BigDecimal.TEN, price2.getOrZero());
  }

  @Test
  public void testOf_Integer() {
    var price = Price.of(99);
    assertEquals(99, price.getOrZero().intValue());
  }

  @Test
  public void testOf_Long() {
    var price = Price.of(100L);
    assertEquals(100L, price.getOrZero().longValue());
  }

  @Test
  public void testOf_Float() {
    var price = Price.of(1.11);
    assertEquals(1.0, price.getOrZero().floatValue());
    assertEquals(1, price.getOrZero().intValue());
  }

  @Test
  public void testOf_Double() {
    var price = Price.of(Double.valueOf("2.22"));
    assertNotEquals(Double.valueOf("2.22"), price.getOrZero().doubleValue());
    assertEquals(Double.valueOf("2.0"), price.getOrZero().doubleValue());
  }

  @Test
  public void testOfNonFormat() {
    var price1 = Price.of("1000");
    assertEquals(1000, price1.getOrZero().intValue());
    assertThrows(NumberFormatException.class, () -> Price.of("1,000"));
  }

  @Test
  public void testOfFormatted() {
    var price1 = Price.ofFormatted("2,000");
    assertEquals(2000, price1.getOrZero().intValue());

    var price2 = Price.ofFormatted("2,000,000");
    assertEquals(2000000, price2.getOrZero().intValue());

    assertThrows(NumberFormatException.class, () -> Price.ofFormatted("abc"));

  }

  @Test
  public void testToFormatted() {
    var price1 = Price.of("1000");
    assertEquals("1,000", price1.toFormatted());
    var price2 = Price.of("1000000");
    assertEquals("1,000,000", price2.toFormatted());
  }

  @Test
  public void testMultiply() {
    var price = Price.of("20");
    var quantity = Quantity.of("10.01");

    NullableNumber result = price.multiply(quantity);
    assertEquals("200", result.getOrZero().toPlainString());

  }

  @Test
  public void testDiscount() {
    var price = Price.of("20");
    var percentage = Percentage.of("10.01");

    Price multipledPrice = price.discount(percentage);
    assertEquals("18", multipledPrice.toFormatted());

  }

  @Test
  public void testIsEmpty() {
    Integer value1 = null;
    var actual1 = Price.of(value1);
    assertTrue(actual1.isEmpty());

    Long value2 = null;
    var actual2 = Price.of(value2);
    assertTrue(actual2.isEmpty());

    BigDecimal value3 = null;
    var actual3 = Price.of(value3);
    assertTrue(actual3.isEmpty());

    String value4 = null;
    var actual4 = Price.ofFormatted(value4);
    assertTrue(actual4.isEmpty());

    var actual5 = Price.of(value4);
    assertTrue(actual5.isEmpty());
  }

  @Test
  public void testEquals() {
    var price1 = Price.ofFormatted("2,000");
    var price2 = Price.ofFormatted("2,000");
    assertEquals(price1, price2);

    var price3 = Price.ofFormatted("3,000");
    assertNotEquals(price1, price3);

  }

  @Test
  public void testToString() {
    var actual1 = Price.of(111);
    assertEquals("111", actual1.toString());
  }

  @Test
  public void testCoverage() {
    var obj = Price.of(1);
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }

}
