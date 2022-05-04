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
public class PercentageTest {

    public PercentageTest() {
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
    public void testEquals() {
        var percentage1 = Percentage.ofDecimal("0.1001");
        var percentage2 = Percentage.ofDecimal("0.1001");
        assertEquals(percentage1, percentage2);

        var percentage3 = Percentage.ofDecimal("0.1002");
        assertNotEquals(percentage1, percentage3);

    }

    @Test
    public void testQuantityPercentage() {
        var percentage = Percentage.of(10);
        var quantity = Quantity.of(20);

        assertEquals(2, percentage.multiply(quantity).toInt());
        assertEquals(Percentage.of(2), percentage.multiply(quantity));

        assertEquals(2, quantity.multiply(percentage).toInt());
        assertEquals(Quantity.of(2), quantity.multiply(percentage));
    }

}
