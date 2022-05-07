/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.verneermlab.apps.common.domain.part.calculator.BigDecimalCalculator;

public class PriceTest {

    public PriceTest() {
    }

    @Test
    public void testOf_BigDecimal() {
        var price = Price.of(BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, price.toBigDecimal());
    }

    @Test
    public void testOf_Integer() {
        var price = Price.of(99);
        assertEquals(99, price.toInt());
    }

    @Test
    public void testOf_Long() {
        var price = Price.of(100L);
        assertEquals(100L, price.toLong());
    }

    @Test
    public void testOfNonFormat() {
        var price = Price.ofNonFormat("1000");
        assertEquals(1000, price.toInt());
    }

    @Test
    public void testOfFormatted() {
        var price1 = Price.ofFormatted("2,000");
        assertEquals(2000, price1.toInt());

        var price2 = Price.ofFormatted("2,000,000");
        assertEquals(2000000, price2.toInt());
    }

    @Test
    public void testOfFormattedDecimalFormat() {
        var price1 = Price.ofFormatted("1,2000", new DecimalFormat("#,###0"));
        assertEquals(12000, price1.toInt());
    }

    @Test
    public void testToNonFormat() {
        var price = Price.ofFormatted("2,000");
        assertEquals("2000", price.toNonFormat());
    }

    @Test
    public void testToFormatted() {
        var price = Price.ofNonFormat("1000");
        assertEquals("1,000", price.toFormatted());
    }

    @Test
    public void testToFormattedDecimalFormat() {
        var price = Price.ofNonFormat("10000");
        assertEquals("1,0000", price.toFormatted(new DecimalFormat("#,###0")));
    }

    @Test
    public void testPlus1() {
        var price1 = Price.of(100);
        var price2 = Price.of(200);
        var actual = price1.plus(price2);
        assertEquals(300, actual.toInt());
    }

    @Test
    public void testPlus2() {
        var price1 = Price.of(100);
        var price2 = Price.of(200);
        var actual = price1.plus(price1, price2);
        assertEquals(400, actual.toInt());
    }

    @Test
    public void testPlusAll() {
        var price1 = Price.of(100);
        var price2 = Price.of(300);
        var actual = price1.plusAll(List.of(price1, price2));
        assertEquals(500, actual.toInt());
    }

    @Test
    public void testMinus() {
        var price1 = Price.of(100);
        var price2 = Price.of(200);
        var actual = price1.minus(price2);
        assertEquals(-100, actual.toInt());
    }

    @Test
    public void testMultiply() {
        var price1 = Price.of(100);
        var price2 = Price.of(200);
        var actual = price1.multiply(price2);
        assertEquals(20000, actual.toInt());
    }

    @Test
    public void testMultiplyType() {
        var price1 = Price.of(100);
        var price2 = Price.of(200);
        Price actual = price1.multiply(price2, Price::of);
        assertEquals(20000, actual.toInt());
    }

    @Test
    public void testMultiplyTypeQuantity() {
        var price = Price.of(100);
        var quantity = Quantity.of(200);

        //ここで確認したいのは計算元となるクラスの型を適用しているところ
        Price actual1 = price.multiply(quantity);
        assertEquals(20000, actual1.toInt());

        // 戻り値の型変換がされていない以下の記述はコンパイルエラー
        // Quantity actual2 = price.multiply(quantity);
        Quantity actual2 = price.multiply(quantity, Quantity::of);
        assertEquals(20000, actual2.toInt());
    }

    @Test()
    @SuppressWarnings("unchecked")
    public void testParseException() {
        assertThrows(NumberFormatException.class,
                () -> Price.ofFormatted("1", new DecimalFormat("z"))
        );
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
    public void testCoverage() {
        var obj = Price.of(1);
        obj.hashCode();
        assertEquals(obj, obj);
        assertNotEquals(obj, null);
        assertNotEquals(obj, "");
    }

}
