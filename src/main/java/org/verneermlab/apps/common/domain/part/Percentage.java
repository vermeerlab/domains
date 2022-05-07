package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
 * 率.
 * <br>
 * 税率のように百分率を基準として小数点表記（例：4.75%）をすることを想定して情報を保持します.
 *
 * @author Yamashita.Takahiro
 */
public class Percentage implements Plus<Percentage>, Minus<Percentage>, Multiply<Percentage>, Divide<Percentage> {

    private static final DecimalFormat decimalFormat = new DecimalFormat("#0.000");
    private final Integer DEFAULT_SCALE = 3;
    private final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;

    // 計算しやすいように少数値で保持
    private final BigDecimalCalculator calculator;

    private Percentage(BigDecimalCalculator calculator) {
        this.calculator = calculator;
    }

    public Percentage(BigDecimal value) {
        this(value, null, null);
    }

    public Percentage(BigDecimal value, Integer scale, RoundingMode roundingMode) {
        var decimal = value.divide(BigDecimal.valueOf(100));

        var s = Objects.isNull(scale) ? DEFAULT_SCALE : scale;
        var r = Objects.isNull(roundingMode) ? DEFAULT_ROUND_MODE : roundingMode;
        this.calculator = BigDecimalCalculator.builder(decimal)
                .scale(s).roundingMode(r).build();
    }

    /**
     * 百分率の値からインスタンスを生成します.
     *
     * @param value 値（百分率）
     * @return 生成したインスタンス
     */
    public static Percentage of(BigDecimal value) {
        return new Percentage(value);
    }

    /**
     * 百分率の値からインスタンスを生成します.
     *
     * @param value 値（百分率）
     * @return 生成したインスタンス
     */
    public static Percentage of(Integer value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return Percentage.of(bigDecimal);
    }

    /**
     * 百分率の値からインスタンスを生成します.
     *
     * @param value 値（百分率）
     * @return 生成したインスタンス
     */
    public static Percentage of(Long value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return Percentage.of(bigDecimal);
    }

    static Percentage from(BigDecimalCalculator calculator) {
        return new Percentage(calculator);
    }

    /**
     * 百分率表記からインスタンスを生成します.
     * <br>
     * 税率や利率のように、パーセント表記からインスタンスを生成したい場合に使用します.<br>
     * 例えば、4.75%の場合は,
     * <br> {@code Percentage.ofPercentage("4.75") } と記述します.
     *
     * @param value 百分率表記の文字列
     * @return 生成したインスタンス
     */
    public static Percentage ofPercentage(String value) {
        var bigDecimal = new BigDecimal(value);
        return Percentage.of(bigDecimal);
    }

    /**
     * 小数点表記からインスタンスを生成します.
     * <br>
     * 税率や利率を小数点で扱ったというインスタンスを生成したい場合に使用します. <br>
     * 例えば、30%の場合は,
     * <br> {@code Percentage.ofDecimal("0.3") } と記述します.
     *
     * @param value 小数点表記の文字列
     * @return 生成したインスタンス
     */
    public static Percentage ofDecimal(String value) {
        var bigDecimal = new BigDecimal(value);
        return Percentage.of(bigDecimal.multiply(BigDecimal.valueOf(100)));
    }

    @Override
    public Percentage plus(Percentage... other) {
        return Percentage.from(this.calculator.plus(other));
    }

    @Override
    public Percentage plusAll(List<Percentage> others) {
        return Percentage.from(this.calculator.plusAll(others));
    }

    @Override
    public Percentage minus(Percentage... other) {
        return Percentage.from(this.calculator.minus(other));
    }

    @Override
    public <U extends Multiply<U>> Percentage multiply(U other) {
        return Percentage.from(this.calculator.multiply(other));
    }

    @Override
    public <U extends Multiply<U>, R extends CalculatorBase> R multiply(U other, Function<BigDecimal, R> newInstance) {
        var calc = this.calculator.multiply(other);
        return newInstance.apply(calc.getCalcValue());
    }

    @Override
    public <U extends Divide<U>> Percentage divide(U other, int scale, RoundingMode roundingMode) {
        return Percentage.from(this.calculator.divide(other, scale, roundingMode));
    }

    @Override
    public <U extends Divide<U>, R extends CalculatorBase> R divide(U other, int scale, RoundingMode roundingMode, Function<BigDecimal, R> newInstance) {
        var calc = this.calculator.multiply(other);
        return newInstance.apply(calc.getCalcValue());
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
    public BigDecimal getCalcValue() {
        return this.calculator.getCalcValue();
    }

    @Override
    public BigDecimal toBigDecimal() {
        return this.calculator.getCalcValue()
                .multiply(BigDecimal.valueOf(100));
    }

    public String toDecimal() {
        BigDecimal result = this.calculator.getCalcValue();
        return result.toPlainString();
    }

    public String toNonFormat() {
        return this.toBigDecimal().toPlainString();
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
        hash = 59 * hash + Objects.hashCode(this.getCalcValue());
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
        final Percentage other = (Percentage) obj;
        return this.calculator.equals(other.calculator);
    }

}
