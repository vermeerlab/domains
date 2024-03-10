package org.verneermlab.apps.common.domain.part.unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.apps.common.domain.part.numeric.NullableNumber;
import org.verneermlab.base.domain.type.numeric.behavior.Minus;
import org.verneermlab.base.domain.type.numeric.behavior.Multiply;
import org.verneermlab.base.domain.type.numeric.behavior.Plus;

/**
 * 単価.
 * <p>
 * プロパティ値が<code>null</code>の場合、ZEROと同じ扱いで計算をします.
 * </p>
 * プロパティ値でNullを許容することで、WebAPIやDBにてNullを設定している場合でも本クラスで扱うことで、永続化するタイミング以外のロジック各所でNullチェックが不要になります.
 *
 * @author Yamashita.Takahiro
 */
public final class Price implements Plus<Price>, Minus<Price>, Multiply<Price> {

  private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0");
  private static final int DEFAULT_SCALE = 0;
  private static final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;

  private final BigDecimal value;

  private Price() {
    this.value = null;
  }

  private Price(BigDecimal value) {
    this.value = value.setScale(DEFAULT_SCALE, DEFAULT_ROUND_MODE);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Price of(Number value) {
    if (Objects.isNull(value)) {
      return new Price();
    }
    var bigDecimal = BigDecimal.valueOf(value.doubleValue());
    return new Price(bigDecimal);
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Price of(String value) {
    if (Objects.isNull(value)) {
      return new Price();
    }
    var bigDecimal = new BigDecimal(value);
    return new Price(bigDecimal);
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
    try {
      var number = decimalFormat.parse(value);
      var bigDecimal = new BigDecimal(number.toString());
      return new Price(bigDecimal);
    } catch (ParseException ex) {
      throw new NumberFormatException("Price could not parse value = " + value);
    }
  }

  @Override
  public Optional<BigDecimal> getNullableValue() {
    return Optional.ofNullable(this.value);
  }

  /**
   * 書式変換をした文字列を返却します.
   *
   * @return 変換した文字列
   */
  public String toFormatted() {
    return decimalFormat.format(this.getOrZero());
  }

  /**
   * 金額の数値を返却します.
   *
   * @param quantity 量
   * @return 金額
   */
  public NullableNumber multiply(Quantity quantity) {
    return this.multiply(quantity.getOrZero(), NullableNumber::of);
  }

  /**
   * 割引後単価を返却します.
   *
   * @param percentage 割引率
   * @return 割引後単価
   */
  public Price discount(Percentage percentage) {
    return this.multiply(Percentage.of(100).minus(percentage).getOrZero());
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
    return Objects.equals(this.getOrZero(), other.getOrZero());
  }

  @Override
  public String toString() {
    return this.getOrZero().toPlainString();
  }

}
