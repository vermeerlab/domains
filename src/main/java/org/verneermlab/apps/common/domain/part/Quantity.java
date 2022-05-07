package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.verneermlab.apps.common.domain.part.calculator.BigDecimalCalculator;
import org.verneermlab.apps.common.domain.part.calculator.CalculatorBase;
import org.verneermlab.apps.common.domain.part.calculator.Divide;
import org.verneermlab.apps.common.domain.part.calculator.Minus;
import org.verneermlab.apps.common.domain.part.calculator.Multiply;
import org.verneermlab.apps.common.domain.part.calculator.Plus;

/**
 * 数量.
 *
 * @author Yamashita.Takahiro
 */
public class Quantity implements Plus<Quantity>, Minus<Quantity>, Multiply<Quantity>, Divide<Quantity> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    private final Integer DEFAULT_SCALE = 0;
    private final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;

    private final BigDecimalCalculator calculator;

    private Quantity(BigDecimalCalculator calculator) {
        this.calculator = calculator;
    }

    private Quantity(BigDecimal value) {
        this(value, null, null);
    }

    private Quantity(BigDecimal value, Integer scale, RoundingMode roundingMode) {
        var s = Objects.isNull(scale) ? DEFAULT_SCALE : scale;
        var r = Objects.isNull(roundingMode) ? DEFAULT_ROUND_MODE : roundingMode;
        this.calculator = BigDecimalCalculator.builder(value)
                .scale(s).roundingMode(r).build();
    }

    public static Quantity of(BigDecimal value) {
        return new Quantity(value);
    }

    public static Quantity of(Integer value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return Quantity.of(bigDecimal);
    }

    public static Quantity of(Long value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return Quantity.of(bigDecimal);
    }

    static Quantity from(BigDecimalCalculator calculator) {
        return new Quantity(calculator);
    }

    public static Quantity ofNonFormat(String value) {
        var bigDecimal = new BigDecimal(value);
        return Quantity.of(bigDecimal);
    }

    public static Quantity ofFormatted(String value) {
        return ofFormatted(value, decimalFormat);
    }

    public static Quantity ofFormatted(String value, DecimalFormat decimalFormat) {
        try {
            var number = decimalFormat.parse(value);
            var bigDecimal = new BigDecimal(number.toString());
            return Quantity.of(bigDecimal);
        } catch (ParseException ex) {
            throw new NumberFormatException("Quantity could not parse value = " + value);
        }
    }

    @Override
    public Quantity plus(Quantity... other) {
        return Quantity.from(this.calculator.plus(other));
    }

    @Override
    public Quantity plusAll(List<Quantity> others) {
        return Quantity.from(this.calculator.plusAll(others));
    }

    @Override
    public Quantity minus(Quantity... other) {
        return Quantity.from(this.calculator.minus(other));
    }

    @Override
    public <U extends Multiply<U>> Quantity multiply(U other) {
        return Quantity.from(this.calculator.multiply(other));
    }

    @Override
    public <U extends Multiply<U>, R extends CalculatorBase> R multiply(U other, Function<BigDecimal, R> newInstance) {
        var calc = this.calculator.multiply(other);
        return newInstance.apply(calc.getCalcValue());
    }

    @Override
    public <U extends Divide<U>> Quantity divide(U other, int scale, RoundingMode roundingMode) {
        return Quantity.from(this.calculator.divide(other, scale, roundingMode));
    }

    @Override
    public <U extends Divide<U>, R extends CalculatorBase> R divide(
            U other, int scale, RoundingMode roundingMode, Function<BigDecimal, R> newInstance) {
        var calc = Quantity.from(this.calculator.divide(other, scale, roundingMode));
        return newInstance.apply(calc.getCalcValue());
    }

    public <U extends Divide<U>> Percentage divideToPercentage(U other) {
        return this.divideToPercentage(other, this.getScale(), this.getRoundingMode());
    }

    public <U extends Divide<U>> Percentage divideToPercentage(U other, RoundingMode roundingMode) {
        return this.divideToPercentage(other, this.getScale(), roundingMode);
    }

    public <U extends Divide<U>> Percentage divideToPercentage(U other, int scale, RoundingMode roundingMode) {
        var value = this.calculator.divide(other, scale, roundingMode).getCalcValue();
        return Percentage.of(value.multiply(BigDecimal.valueOf(100)));
    }

    @Override
    public Integer getScale() {
        return this.calculator.getScale();
    }

    @Override
    public RoundingMode getRoundingMode() {
        return this.calculator.getRoundingMode();
    }

    @Override
    public BigDecimal toBigDecimal() {
        return this.calculator.toBigDecimal();
    }

    public String toNonFormat() {
        return this.calculator.toBigDecimal().toPlainString();
    }

    public String toFormatted() {
        return this.toFormatted(decimalFormat);
    }

    public String toFormatted(DecimalFormat decimalFormat) {
        return decimalFormat.format(this.toBigDecimal());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.toBigDecimal());
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
        final Quantity other = (Quantity) obj;
        return this.calculator.equals(other.calculator);
    }
}
