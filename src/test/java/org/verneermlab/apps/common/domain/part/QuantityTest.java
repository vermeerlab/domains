/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Yamashita.Takahiro
 */
public class QuantityTest {

    public QuantityTest() {
    }

    @Test
    public void testOf_BigDecimal() {
        var quantity = Quantity.of(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, quantity.toBigDecimal());
    }

    @Test
    public void testOf_Integer() {
        var quantity = Quantity.of(99);
        assertEquals(99, quantity.toInt());
    }

    @Test
    public void testOf_Long() {
        var quantity = Quantity.of(100L);
        assertEquals(100L, quantity.toLong());
    }

    @Test
    public void testOfNonFormat() {
        var quantity = Quantity.ofNonFormat("1000.01");
        assertEquals(1000, quantity.toInt());
        assertEquals(new BigDecimal("1000.01"), quantity.toBigDecimal());
    }

    @Test
    public void testOfFormatted() {
        var quantity1 = Quantity.ofFormatted("2,000");
        assertEquals(2000, quantity1.toInt());

        var quantity2 = Quantity.ofFormatted("2,000,000");
        assertEquals(2000000, quantity2.toInt());
    }

    @Test
    public void testOfFormattedDecimalFormat() {
        var quantity2 = Quantity.ofFormatted("2,000.010", new DecimalFormat("#,##0.00"));
        assertEquals(new BigDecimal("2000.01"), quantity2.toBigDecimal());
    }

    @Test
    public void testToNonFormat() {
        var quantity = Quantity.ofFormatted("2,000.11");
        assertEquals("2000.11", quantity.toNonFormat());
    }

    @Test
    public void testToFormatted() {
        var quantity = Quantity.ofNonFormat("1000.01");
        assertEquals("1,000.01", quantity.toFormatted());
    }

    @Test
    public void testToFormattedDecimalFormat() {
        var quantity = Quantity.ofNonFormat("1000.10");
        assertEquals("1,000.1", quantity.toFormatted(new DecimalFormat("#,##0.0")));
    }

    @Test
    public void testPlus1() {
        var quantity1 = Quantity.of(100);
        var quantity2 = Quantity.of(200);
        var actual = quantity1.plus(quantity2);
        assertEquals(300, actual.toInt());

        // 型の異なるモデルは加算不可（コンパイルエラーになる）
        // quantity1.plus(Price.of(2));
    }

    @Test
    public void testPlus2() {
        var quantity1 = Quantity.of(100);
        var quantity2 = Quantity.of(200);
        var actual = quantity1.plus(quantity1, quantity2);
        assertEquals(400, actual.toInt());
    }

    @Test
    public void testPlusAll() {
        var quantity1 = Quantity.of(100);
        var quantity2 = Quantity.of(300);
        var actual = quantity1.plusAll(List.of(quantity1, quantity2));
        assertEquals(500, actual.toInt());
    }

    @Test
    public void testMinus() {
        var quantity1 = Quantity.of(100);
        var quantity2 = Quantity.of(200);
        var actual = quantity1.minus(quantity2);
        assertEquals(-100, actual.toInt());
    }

    @Test
    public void testMultiply() {
        var quantity1 = Quantity.of(100);
        var quantity2 = Quantity.of(200);
        var actual = quantity1.multiply(quantity2);
        assertEquals(20000, actual.toInt());
    }

    @Test
    public void testMultiplyType() {
        var price = Price.ofNonFormat("20");
        var quantity = Quantity.ofNonFormat("10.01");

        // 型の異なるモデルも乗算可（コンパイルエラーにならない）
        var multipledPrice = price.multiply(quantity);
        assertEquals("200", multipledPrice.toFormatted());

    }

    @Test
    public void testMultiplyTypeQuantity() {
        var price = Price.of(100);
        var quantity = Quantity.of(200);

        //ここで確認したいのは計算元となるクラスの型を適用しているところ
        var actual1 = quantity.multiply(price);
        assertEquals(20000, actual1.toInt());

        // 戻り値の型変換がされていない以下の記述はコンパイルエラー
        // Price actual2 = quantity.multiply(price);
        Price actual2 = quantity.multiply(price, Price::of);
        assertEquals(20000, actual2.toInt());
    }

    @Test
    public void testDivide() {
        var quantity1 = Quantity.ofNonFormat("10");
        var quantity2 = Quantity.ofNonFormat("2");

        var actual1 = quantity1.divide(quantity2);
        assertEquals(Quantity.of(5), actual1);
    }

    @Test
    public void testDivideRoundingMode() {
        var quantity1 = Quantity.ofNonFormat("5");
        var quantity2 = Quantity.ofNonFormat("10");

        var actual = quantity1.divide(quantity2, RoundingMode.UP);
        assertEquals(Quantity.of(1), actual);
    }

    @Test
    public void testDivideRoundingModeFunc() {
        var quantity1 = Quantity.ofNonFormat("5");
        var quantity2 = Quantity.ofNonFormat("10");

        var actual = quantity1.divide(quantity2, RoundingMode.UP, Price::of);
        assertEquals(Price.of(1), actual);
    }

    @Test
    public void testDivideScaleRoundingMode() {
        var quantity1 = Quantity.ofNonFormat("5");
        var quantity2 = Quantity.ofNonFormat("10");

        var actual1 = quantity1.divide(quantity2, 1, RoundingMode.UP);
        assertEquals(Quantity.ofNonFormat("0.5"), actual1);

        var quantity3 = Quantity.ofNonFormat("20");
        var actual2 = quantity1.divide(quantity3, 1, RoundingMode.UP);
        assertEquals(Quantity.ofNonFormat("0.3"), actual2);

    }

    @Test
    public void testDivideToPercentage() {
        var quantity1 = Quantity.ofNonFormat("10");
        var quantity2 = Quantity.ofNonFormat("2");

        // 内部的な数値をラップしただけなので、こうなってしまう。
        // 尺度の違うモデルを扱う場合は、個別にメソッドを準備して対応する方が良い
        var actual1 = quantity1.divide(quantity2, Percentage::of);
        assertEquals(Percentage.of(5), actual1);

        // 戻り値の型 Percentage のためのメソッドを準備
        var actual2 = quantity1.divideToPercentage(quantity2);
        assertEquals(Percentage.of(500), actual2);
    }

    @Test
    public void testDivideToPercentageRoundingMode() {
        var quantity1 = Quantity.ofNonFormat("2");
        var quantity2 = Quantity.ofNonFormat("5");

        //scaleは計算元のデフォルトが適用される
        var actual = quantity1.divideToPercentage(quantity2, RoundingMode.UP);
        assertEquals(Percentage.of(100), actual);
    }

    @Test
    public void testDivideToPercentageScaleRoundingMode() {
        var quantity1 = Quantity.ofNonFormat("2");
        var quantity2 = Quantity.ofNonFormat("5");

        var actual = quantity1.divideToPercentage(quantity2, 2, RoundingMode.UP);
        assertEquals(Percentage.of(40), actual);
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void testParseException() {
        assertThrows(NumberFormatException.class,
                () -> Quantity.ofFormatted("1", new DecimalFormat("z"))
        );
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
    public void testCoverage() {
        var obj = Quantity.of(1);
        obj.hashCode();
        assertEquals(obj, obj);
        assertNotEquals(obj, null);
        assertNotEquals(obj, "");
    }
}
