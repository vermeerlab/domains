package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimalCalculatorBuilder.
 *
 * @author Yamashita.Takahiro
 */
public class BigDecimalCalculatorBuilder {

  private final BigDecimal value;
  private Integer scale;
  private RoundingMode roundingMode;

  public BigDecimalCalculatorBuilder(BigDecimal value) {
    this.value = value;
  }

  public BigDecimalCalculatorBuilder scale(Integer scale) {
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
