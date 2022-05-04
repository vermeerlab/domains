/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * BigDecimalの計算を行います.
 * <br>
 * 主に金額計算など業務に用いることを想定した限定したものです.
 *
 * @author Yamashita.Takahiro
 */
public class BigDecimalCalculator implements Calculator<BigDecimalCalculator> {

    private final Integer DEFAULT_SCALE = 0;
    private final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.DOWN;

    private final BigDecimal value;
    private final Integer scale;
    private final RoundingMode roundingMode;

    BigDecimalCalculator(BigDecimal value, Integer scale, RoundingMode roundingMode) {
        this.value = value;
        this.scale = Objects.isNull(scale) ? DEFAULT_SCALE : scale;
        this.roundingMode = Objects.isNull(roundingMode) ? DEFAULT_ROUND_MODE : roundingMode;
    }

    public static BigDecimalCalculatorBuilder builder(BigDecimal value) {
        return new BigDecimalCalculatorBuilder(value);
    }

    public static BigDecimalCalculatorBuilder builder(Integer value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return new BigDecimalCalculatorBuilder(bigDecimal);
    }

    public static BigDecimalCalculatorBuilder builder(Long value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return new BigDecimalCalculatorBuilder(bigDecimal);
    }

    public static BigDecimalCalculatorBuilder builder(String value) {
        var bigDecimal = new BigDecimal(value);
        return new BigDecimalCalculatorBuilder(bigDecimal);
    }

    @Override
    public Integer getScale() {
        return this.scale;
    }

    @Override
    public RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    @Override
    public BigDecimal toBigDecimal() {
        return this.value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BigDecimalCalculator other = (BigDecimalCalculator) obj;
        return this.value.compareTo(other.value) == 0;
    }

}
