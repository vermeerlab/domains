package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalCalculatorBuilder {

    private BigDecimal value;
    private int scale;
    private RoundingMode roundingMode;

    public BigDecimalCalculatorBuilder(BigDecimal value) {
        this.value = value;
    }

    public BigDecimalCalculatorBuilder(BigDecimal value, int scale, RoundingMode roundingMode) {
        this.value = value;
        this.scale = scale;
        this.roundingMode = roundingMode;
    }

    public BigDecimalCalculatorBuilder scale(int scale) {
        this.scale = scale;
        return this;
    }

    public BigDecimalCalculatorBuilder roundingMode(RoundingMode roundingMode) {
        this.roundingMode = roundingMode;
        return this;
    }

    public BigDecimalCalculator build() {
        return new BigDecimalCalculator(value, scale, roundingMode);
    }

}
