package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.verneermlab.apps.common.domain.part.calculator.BigDecimalCalculator;
import org.verneermlab.apps.common.domain.part.calculator.CalculatorBase;
import org.verneermlab.apps.common.domain.part.calculator.Minus;
import org.verneermlab.apps.common.domain.part.calculator.Multiply;
import org.verneermlab.apps.common.domain.part.calculator.Plus;

/**
 * 単価.
 *
 * @author Yamashita.Takahiro
 */
public class Price implements Plus<Price>, Minus<Price>, Multiply<Price> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0");
    private final BigDecimalCalculator calculator;

    private Price(BigDecimalCalculator calculator) {
        this.calculator = calculator;
    }

    private Price(BigDecimal value) {
        this.calculator = BigDecimalCalculator.builder(value).build();
    }

    public static Price of(BigDecimal value) {
        return new Price(value);
    }

    public static Price of(Integer value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return Price.of(bigDecimal);
    }

    public static Price of(Long value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return Price.of(bigDecimal);
    }

    static Price from(BigDecimalCalculator calculator) {
        return new Price(calculator);
    }

    public static Price ofNonFormat(String value) {
        var bigDecimal = new BigDecimal(value);
        return Price.of(bigDecimal);
    }

    public static Price ofFormatted(String value) {
        return ofFormatted(value, decimalFormat);
    }

    public static Price ofFormatted(String value, DecimalFormat decimalFormat) {
        try {
            var number = decimalFormat.parse(value);
            var bigDecimal = new BigDecimal(number.toString());
            return Price.of(bigDecimal);
        } catch (ParseException ex) {
            throw new NumberFormatException("Price could not parse value = " + value);
        }
    }

    @Override
    @SafeVarargs
    public final Price plus(Price... other) {
        return Price.from(this.calculator.plus(other));
    }

    @Override
    public Price plusAll(List<Price> others) {
        return Price.from(this.calculator.plusAll(others));
    }

    @Override
    public Price minus(Price... other) {
        return Price.from(this.calculator.minus(other));
    }

    @Override
    public <U extends Multiply<U>> Price multiply(U other) {
        return Price.from(this.calculator.multiply(other));
    }

    @Override
    public <U extends Multiply<U>, R extends CalculatorBase> R multiply(U other, Function<BigDecimal, R> newInstance) {
        var calc = this.calculator.multiply(other);
        return newInstance.apply(calc.getCalcValue());
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
        hash = 41 * hash + Objects.hashCode(this.toBigDecimal());
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
        final Price other = (Price) obj;
        return this.calculator.equals(other.calculator);
    }

}
