package org.verneermlab.apps.common.domain.part.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.verneermlab.apps.common.domain.part.calculator.BigDecimalCalculator;
import org.verneermlab.apps.common.domain.part.calculator.Calculator;
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
  private static final Integer DEFAULT_SCALE = 0;
  private static final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;

  private final BigDecimalCalculator calculator;

  private Quantity() {
    Integer value = null;
    this.calculator = BigDecimalCalculator.builder(value)
            .scale(DEFAULT_SCALE).roundingMode(DEFAULT_ROUND_MODE).build();
  }

  private Quantity(BigDecimalCalculator calculator) {
    this.calculator = calculator;
  }

  private Quantity(BigDecimal value) {
    this.calculator = BigDecimalCalculator.builder(value)
            .scale(DEFAULT_SCALE).roundingMode(DEFAULT_ROUND_MODE).build();
  }

  /**
   * インスタンスを生成します.
   * <p>
   * フロントや永続化時に<code>null</code>を設定されている場合に使用することを想定しています.
   * </p>
   *
   * @param valueOp 値
   * @return 生成したインスタンス
   */
  public static Quantity of(Optional<?> valueOp) {
    if (valueOp.isEmpty()) {
      return new Quantity();
    }

    var obj = valueOp.get();

    if (obj instanceof BigDecimal) {
      var casted = (BigDecimal) obj;
      return Quantity.of(casted);

    } else if (obj instanceof Integer) {
      var casted = (Integer) obj;
      return Quantity.of(casted);

    } else if (obj instanceof Long) {
      var casted = (Long) obj;
      return Quantity.of(casted);

    }

    throw new UnsupportedOperationException("Not supported yet.");

  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Quantity of(BigDecimal value) {
    return new Quantity(value);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Quantity of(Integer value) {
    if (Objects.isNull(value)) {
      return new Quantity();
    }

    var bigDecimal = BigDecimal.valueOf(value);
    return Quantity.of(bigDecimal);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Quantity of(Long value) {
    if (Objects.isNull(value)) {
      return new Quantity();
    }
    var bigDecimal = BigDecimal.valueOf(value);
    return Quantity.of(bigDecimal);
  }

  static Quantity from(BigDecimalCalculator calculator) {
    return new Quantity(calculator);
  }

  /**
   * 文字列からモデルを生成します.
   * <p>
   * フロントや永続化時に<code>null</code>を設定されている場合に使用することを想定しています.
   * </p>
   *
   * @param valueOp 値
   * @return 生成したインスタンス
   */
  public static Quantity ofNonFormat(Optional<String> valueOp) {
    return Quantity.ofNonFormat(valueOp.orElse(null));
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Quantity ofNonFormat(String value) {
    if (Objects.isNull(value)) {
      return new Quantity();
    }
    var bigDecimal = new BigDecimal(value);
    return Quantity.of(bigDecimal);
  }

  /**
   * 文字列からモデルを生成します.
   * <p>
   * フロントや永続化時に<code>null</code>を設定されている場合に使用することを想定しています.
   * </p>
   *
   * @param valueOp 変換元の文字列
   * @return 生成したインスタンス
   */
  public static Quantity ofFormatted(Optional<String> valueOp) {
    return Quantity.ofFormatted(valueOp.orElse(null));
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 変換元の文字列
   * @return 生成したインスタンス
   */
  public static Quantity ofFormatted(String value) {
    if (Objects.isNull(value)) {
      return new Quantity();
    }
    return ofFormatted(value, decimalFormat);
  }

  /**
   * 文字列からモデルを生成します.
   * <p>
   * フロントや永続化時に<code>null</code>を設定されている場合に使用することを想定しています.
   * </p>
   *
   * @param valueOp 変換元の文字列
   * @param decimalFormat 変換書式
   * @return 生成したインスタンス
   */
  public static Quantity ofFormatted(Optional<String> valueOp, DecimalFormat decimalFormat) {
    return Quantity.ofFormatted(valueOp.orElse(null), decimalFormat);
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 変換元の文字列
   * @param decimalFormat 変換書式
   * @return 生成したインスタンス
   */
  public static Quantity ofFormatted(String value, DecimalFormat decimalFormat) {
    if (Objects.isNull(value)) {
      return new Quantity();
    }
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
  public <U extends Multiply<U>, R extends Calculator> R multiply(U other, Function<BigDecimal, R> newInstance) {
    var calc = this.calculator.multiply(other);
    return newInstance.apply(calc.getCalcValue());
  }

  @Override
  public <U extends Divide<U>> Quantity divide(U other, int scale, RoundingMode roundingMode) {
    return Quantity.from(this.calculator.divide(other, scale, roundingMode));
  }

  @Override
  public <U extends Divide<U>, R extends Calculator> R divide(
          U other, int scale, RoundingMode roundingMode, Function<BigDecimal, R> newInstance) {
    var calc = Quantity.from(this.calculator.divide(other, scale, roundingMode));
    return newInstance.apply(calc.getCalcValue());
  }

  /**
   * 数量で除算して割合を導出します.
   *
   * @param <U> 引数の型
   * @param other 計算するインスタンス
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public <U extends Divide<U>> Percentage divideToPercentage(U other) {
    return this.divideToPercentage(other, this.getScale(), this.getRoundingMode());
  }

  /**
   * 数量で除算して割合を導出します.
   *
   * @param <U> 引数の型
   * @param other 計算するインスタンス
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public <U extends Divide<U>> Percentage divideToPercentage(U other, RoundingMode roundingMode) {
    return this.divideToPercentage(other, this.getScale(), roundingMode);
  }

  /**
   * 数量で除算して割合を導出します.
   *
   * @param <U> 引数の型
   * @param other 計算するインスタンス
   * @param scale 小数点以下の有効桁数
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public <U extends Divide<U>> Percentage divideToPercentage(U other, int scale, RoundingMode roundingMode) {
    var calc = this.calculator.divide(other, scale, roundingMode);
    var converted = calc.multiply(BigDecimalCalculator.builder(100).build());
    return Percentage.of(converted.getCalcValue());
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
  public boolean isEmpty() {
    return this.calculator.isEmpty();
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

  @Override
  public String toString() {
    return this.calculator.toString();
  }

}
