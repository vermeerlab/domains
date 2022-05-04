package org.verneermlab.apps.common.domain.part;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
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
    private final BigDecimal value;

    private final Integer scale;
    private final RoundingMode roundingMode;

    public Percentage(BigDecimal value) {
        this(value, null, null);
    }

    public Percentage(BigDecimal value, Integer scale, RoundingMode roundingMode) {
        var decimal = value.divide(BigDecimal.valueOf(100));

        this.value = decimal;
        this.scale = Objects.isNull(scale) ? DEFAULT_SCALE : scale;
        this.roundingMode = Objects.isNull(roundingMode) ? DEFAULT_ROUND_MODE : roundingMode;
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
    public Integer getScale() {
        return this.scale;
    }

    @Override
    public RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    @Override
    public BigDecimal getValue() {
        return this.value;
    }

    @Override
    public BigDecimal toBigDecimal() {
        return this.value.multiply(BigDecimal.valueOf(100));
    }

    public String toDecimal() {
        BigDecimal result = this.value;
        return result.toPlainString();
    }

    public String toNonFormat() {
        return this.value.multiply(BigDecimal.valueOf(100)).toPlainString();
    }

    public String toFormatted() {
        return this.toFormatted(decimalFormat);
    }

    public String toFormatted(DecimalFormat decimalFormat) {
        return decimalFormat.format(this.value.multiply(BigDecimal.valueOf(100)));
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
        final Percentage other = (Percentage) obj;
        return this.value.compareTo(other.value) == 0;
    }
}
