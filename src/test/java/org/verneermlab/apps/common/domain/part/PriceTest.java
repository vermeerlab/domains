/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
    public void testEquals() {
        var price1 = Price.ofFormatted("2,000");
        var price2 = Price.ofFormatted("2,000");
        assertEquals(price1, price2);

        var price3 = Price.ofFormatted("3,000");
        assertNotEquals(price1, price3);

    }

}
