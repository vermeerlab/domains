package org.verneermlab.apps.common.domain.part.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import org.verneermlab.apps.common.domain.part.calculator.BigDecimalCalculator;
import org.verneermlab.apps.common.domain.part.calculator.Calculator;
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

  private Price() {
    Integer value = null;
    this.calculator = BigDecimalCalculator.builder(value).build();
  }

  private Price(BigDecimalCalculator calculator) {
    this.calculator = calculator;
  }

  private Price(BigDecimal value) {
    this.calculator = BigDecimalCalculator.builder(value).build();
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
  public static Price of(Optional<?> valueOp) {
    if (valueOp.isEmpty()) {
      return new Price();
    }

    var obj = valueOp.get();

    if (obj instanceof BigDecimal) {
      var casted = (BigDecimal) obj;
      return Price.of(casted);

    } else if (obj instanceof Integer) {
      var casted = (Integer) obj;
      return Price.of(casted);

    } else if (obj instanceof Long) {
      var casted = (Long) obj;
      return Price.of(casted);

    }

    throw new UnsupportedOperationException("Not supported yet.");
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Price of(BigDecimal value) {
    return new Price(value);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Price of(Integer value) {
    if (Objects.isNull(value)) {
      return new Price();
    }
    var bigDecimal = BigDecimal.valueOf(value);
    return Price.of(bigDecimal);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Price of(Long value) {
    if (Objects.isNull(value)) {
      return new Price();
    }

    var bigDecimal = BigDecimal.valueOf(value);
    return Price.of(bigDecimal);
  }

  static Price from(BigDecimalCalculator calculator) {
    return new Price(calculator);
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
  public static Price ofNonFormat(Optional<String> valueOp) {
    return Price.ofNonFormat(valueOp.orElse(null));
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Price ofNonFormat(String value) {
    if (Objects.isNull(value)) {
      return new Price();
    }

    var bigDecimal = new BigDecimal(value);
    return Price.of(bigDecimal);
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
  public static Price ofFormatted(Optional<String> valueOp) {
    return Price.ofFormatted(valueOp.orElse(null));
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 変換元の文字列
   * @return 生成したインスタンス
   */
  public static Price ofFormatted(String value) {
    if (Objects.isNull(value)) {
      return new Price();
    }
    return ofFormatted(value, decimalFormat);
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 変換元の文字列
   * @param decimalFormat 変換書式
   * @return 生成したインスタンス
   */
  public static Price ofFormatted(String value, DecimalFormat decimalFormat) {
    if (Objects.isNull(value)) {
      return new Price();
    }
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
  public <U extends Multiply<U>, R extends Calculator> R multiply(U other, Function<BigDecimal, R> newInstance) {
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
  public boolean isEmpty() {
    return this.calculator.isEmpty();
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

  @Override
  public String toString() {
    return this.calculator.toString();
  }
}
