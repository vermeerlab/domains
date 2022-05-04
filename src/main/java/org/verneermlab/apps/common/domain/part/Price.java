package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Objects;
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
    private final BigDecimal value;

    public Price(BigDecimal value) {
        this.value = value;
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
    public BigDecimal toBigDecimal() {
        return this.value;
    }

    public String toNonFormat() {
        return this.value.toPlainString();
    }

    public String toFormatted() {
        return this.toFormatted(decimalFormat);
    }

    public String toFormatted(DecimalFormat decimalFormat) {
        return decimalFormat.format(this.value);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.value);
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
        return this.value.compareTo(other.value) == 0;
    }

}
