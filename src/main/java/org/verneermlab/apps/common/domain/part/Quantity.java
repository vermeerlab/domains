package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Objects;
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
    private final Integer DEFAULT_SCALE = 2;
    private final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;

    private final BigDecimal value;
    private final Integer scale;
    private final RoundingMode roundingMode;

    public Quantity(BigDecimal value) {
        this(value, null, null);
    }

    public Quantity(BigDecimal value, Integer scale, RoundingMode roundingMode) {
        this.value = value;
        this.scale = Objects.isNull(scale) ? DEFAULT_SCALE : scale;
        this.roundingMode = Objects.isNull(roundingMode) ? DEFAULT_ROUND_MODE : roundingMode;
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
        final Quantity other = (Quantity) obj;
        return this.value.compareTo(other.value) == 0;
    }
}
