package org.verneermlab.apps.common.domain.part.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Yamashita.Takahiro
 */
public class PercentageTest {

  public PercentageTest() {
  }

  @Test
  public void testOf_Optional() {
    var empty = Optional.empty();
    var instance0 = Percentage.of(empty);
    assertTrue(instance0.isEmpty());

    var opValue1 = Optional.of(new BigDecimal("10.01"));
    var instance1 = Percentage.of(opValue1);
    assertFalse(instance1.isEmpty());

    var opValue2 = Optional.of(1);
    var instance2 = Percentage.of(opValue2);
    assertFalse(instance2.isEmpty());

    var opValue3 = Optional.of(1L);
    var instance3 = Percentage.of(opValue3);
    assertFalse(instance3.isEmpty());

    var opValue4 = Optional.of("1");
    assertThrowsExactly(UnsupportedOperationException.class,
            () -> Percentage.of(opValue4)
    );

    var instance4 = Percentage.ofPercentage(opValue4);
    assertFalse(instance4.isEmpty());

    var opValue5 = Optional.of("1");
    var instance5 = Percentage.ofDecimal(opValue5);
    assertFalse(instance5.isEmpty());
  }

  @Test
  public void testOf_BigDecimal() {
    var percentage = Percentage.of(new BigDecimal("10.01"));
    assertEquals(new BigDecimal("10.0100"), percentage.toBigDecimal());
  }

  @Test
  public void testOf_Integer() {
    var percentage = Percentage.of(99);
    assertEquals(99, percentage.toInt());
  }

  @Test
  public void testOf_Long() {
    var percentage = Percentage.of(10L);
    assertEquals(10L, percentage.toLong());
  }

  @Test
  public void testOfPercentage() {
    var percentage = Percentage.ofPercentage("10.01");

    assertEquals("0.1001", percentage.toDecimal());

    assertEquals("10.0100", percentage.toNonFormat());

    assertEquals("10.010", percentage.toFormatted());
    assertEquals("10.01", percentage.toFormatted(new DecimalFormat("#,##0.00")));
  }

  @Test
  public void testOfDecimal() {
    var percentage = Percentage.ofDecimal("0.1001");
    assertEquals("0.1001", percentage.toDecimal());
  }

  @Test
  public void testPlus1() {
    var percentage1 = Percentage.of(10);
    var percentage2 = Percentage.of(20);
    var actual = percentage1.plus(percentage2);
    assertEquals(30, actual.toInt());

    // 型の異なるモデルは加算不可（コンパイルエラーになる）
    // percentage1.plus(Price.of(2));
  }

  @Test
  public void testPlus2() {
    var percentage1 = Percentage.of(10);
    var percentage2 = Percentage.of(20);
    var actual = percentage1.plus(percentage1, percentage2);
    assertEquals(40, actual.toInt());
  }

  @Test
  public void testPlusAll() {
    var percentage1 = Percentage.of(10);
    var percentage2 = Percentage.of(30);
    var actual = percentage1.plusAll(List.of(percentage1, percentage2));
    assertEquals(50, actual.toInt());
  }

  @Test
  public void testMinus() {
    var percentage1 = Percentage.of(10);
    var percentage2 = Percentage.of(20);
    var actual = percentage1.minus(percentage2);
    assertEquals(-10, actual.toInt());
  }

  @Test
  public void testMultiply() {
    var percentage1 = Percentage.of(10);
    var percentage2 = Percentage.of(20);
    var actual = percentage1.multiply(percentage2);
    assertEquals(2, actual.toInt());
  }

  @Test
  public void testMultiplyType() {
    var price = Price.ofNonFormat("20");
    var percentage = Percentage.ofPercentage("10.01");

    // 型の異なるモデルも乗算可（コンパイルエラーにならない）
    var multipledPrice = price.multiply(percentage);
    assertEquals("2", multipledPrice.toFormatted());

  }

  @Test
  public void testMultiplyTypePercentage() {
    var price = Price.of(100);
    var percentage = Percentage.of(200);

    // ここで確認したいのは計算元となるクラスの型を適用しているところ
    // 計算結果としては、使い物にはならないことを確認
    var actual1 = percentage.multiply(price);
    assertEquals(20000, actual1.toInt());

    // 戻り値の型変換がされていない以下の記述はコンパイルエラー
    // Price actual2 = percentage.multiply(price);
    Price actual2 = percentage.multiply(price, Price::of);

    // 戻り値の型を指定すると、率計算結果としては妥当になることが確認できる
    // 単価(100)×率(200%)=単価（200）
    assertEquals(200, actual2.toInt());
  }

  @Test
  public void testDivide() {
    var percentage1 = Percentage.ofPercentage("10");
    var percentage2 = Percentage.ofPercentage("2");

    var actual1 = percentage1.divide(percentage2);
    assertEquals(Percentage.of(500), actual1);
  }

  @Test
  public void testDivideRoundingMode() {
    var percentage1 = Percentage.ofPercentage("5");
    var percentage2 = Percentage.ofPercentage("10");

    var actual1 = percentage1.divide(percentage2, RoundingMode.UP);
    assertEquals(Percentage.of(50), actual1);

    var percentage3 = Percentage.ofPercentage("0.3");

    // 0.3 / 5 = 16.666666
    var actual2 = percentage1.divide(percentage3, RoundingMode.UP);
    assertEquals(Percentage.ofPercentage("1666.667"), actual2);
  }

  @Test
  public void testDivideRoundingModeFunc() {
    var percentage1 = Percentage.ofPercentage("5");
    var percentage2 = Percentage.ofPercentage("10");

    var actual = percentage1.divide(percentage2, RoundingMode.UP, Price::of);

    // 5 / 10 = 0.5 戻り値の型がPercentageではないので、100％表記にせず、そのまま出力
    // 率 / 率 = 単価になることは通常ないが、型変換と計算の関連の把握のためのサンプルとして
    assertEquals(Price.ofNonFormat("0.5"), actual);
  }

  @Test
  public void testDivideScaleRoundingMode() {
    var percentage1 = Percentage.ofPercentage("5");
    var percentage2 = Percentage.ofPercentage("10");

    var actual1 = percentage1.divide(percentage2, 1, RoundingMode.UP);
    assertEquals(Percentage.ofPercentage("50"), actual1);

    var percentage3 = Percentage.ofPercentage("20");
    var actual2 = percentage1.divide(percentage3, 1, RoundingMode.UP);

    // 5 / 20 = 0.25 -> 0.3 -> 30%
    assertEquals(Percentage.ofPercentage("30"), actual2);

  }

  @Test
  public void testQuantityPercentage() {
    var percentage = Percentage.of(10);
    var quantity = Quantity.of(20);

    //百分率なので、％表記
    assertEquals(200, percentage.multiply(quantity).toInt());
    assertEquals(Percentage.of(200), percentage.multiply(quantity));

    //量は％表記の乗算
    assertEquals(2, quantity.multiply(percentage).toInt());
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
    var actual4 = Percentage.ofPercentage(value4);
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
    assertEquals("1.11", actual1.toString());

    var actual2 = Percentage.of(Optional.empty());
    assertEquals(null, actual2.toString());
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
