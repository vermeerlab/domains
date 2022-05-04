/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    public void testMultiplyPrice() {
        var price = Price.ofNonFormat("20");
        var quantity = Quantity.ofNonFormat("10.01");

        var multipledPrice = price.multiply(quantity);
        assertEquals("200", multipledPrice.toFormatted());

        //ここで確認したいのは計算元となるクラスの型を適用するということ
        var multipledQuantity = quantity.multiply(price);
        assertEquals("200.20", multipledQuantity.toFormatted());

    }

    @Test
    public void testDivide() {
        var quantity1 = Quantity.ofNonFormat("10");
        var quantity2 = Quantity.ofNonFormat("2");

        var actual1 = quantity1.divide(quantity2);
        assertEquals(Quantity.ofNonFormat("5"), actual1);

        var actual2 = quantity2.divide(quantity1);
        assertEquals(Quantity.ofNonFormat("0.2"), actual2);

    }

    @Test
    public void testEquals() {
        var quantity1 = Quantity.ofFormatted("2,000");
        var quantity2 = Quantity.ofFormatted("2,000");
        assertEquals(quantity1, quantity2);

        var quantity3 = Quantity.ofFormatted("3,000");
        assertNotEquals(quantity1, quantity3);

    }

}
