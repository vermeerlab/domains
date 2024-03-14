package org.verneermlab.apps.common.domain.part.unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.numeric.behavior.Minus;
import org.verneermlab.base.domain.type.numeric.behavior.Multiply;
import org.verneermlab.base.domain.type.numeric.behavior.Plus;

/**
 * 率.
 * <p>
 * プロパティ値が<code>null</code>の場合、ZEROと同じ扱いで計算をします.
 * </p>
 * プロパティ値でNullを許容することで、WebAPIやDBにてNullを設定している場合でも本クラスで扱うことで、永続化するタイミング以外のロジック各所でNullチェックが不要になります.
 *
 * @author Yamashita.Takahiro
 */
public class Percentage implements Plus<Percentage>, Minus<Percentage>, Multiply<Percentage> {

  /**
   * プロパティは少数値で保持するため、百分率を左に２シフトを含むSCALEの指定をします.
   */
  private static final int DEFAULT_SCALE = 5;
  private static final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;

  /**
   * プロパティはパーセント表記で保持します. 30.01%の場合は, 30.01
   */
  private final BigDecimal value;

  private Percentage() {
    this.value = null;
  }

  private Percentage(BigDecimal value) {
    this.value = value.setScale(DEFAULT_SCALE, DEFAULT_ROUND_MODE);
  }

  /**
   * 百分率の値からインスタンスを生成します.
   *
   * @param value 値（百分率）
   * @return 生成したインスタンス
   */
  public static Percentage of(Number value) {
    if (Objects.isNull(value)) {
      return new Percentage();
    }

    var bigDecimal = BigDecimal.valueOf(value.doubleValue()).movePointLeft(2);
    return new Percentage(bigDecimal);
  }

  /**
   * 百分率表記からインスタンスを生成します.
   *
   * @param value 百分率表記の文字列
   * @return 生成したインスタンス
   */
  public static Percentage of(String value) {
    if (Objects.isNull(value) || Objects.equals(value, "")) {
      return new Percentage();
    }
    var bigDecimal = new BigDecimal(value).movePointLeft(2);
    return new Percentage(bigDecimal);
  }

  /**
   * 小数点表記からインスタンスを生成します.
   * <p>
   * 30%の場合は、{@code Percentage.ofDecimal("0.3") } と記述します.
   * </p>
   *
   * @param value 小数点表記の文字列
   * @return 生成したインスタンス
   */
  public static Percentage ofDecimal(Number value) {
    if (Objects.isNull(value)) {
      return new Percentage();
    }
    var bigDecimal = BigDecimal.valueOf(value.doubleValue());
    return new Percentage(bigDecimal);
  }

  /**
   * 小数点表記からインスタンスを生成します.
   * <p>
   * 30%の場合は、{@code Percentage.ofDecimal("0.3") } と記述します.
   * </p>
   *
   * @param value 小数点表記の文字列
   * @return 生成したインスタンス
   */
  public static Percentage ofDecimal(String value) {
    if (Objects.isNull(value) || Objects.equals(value, "")) {
      return new Percentage();
    }
    var bigDecimal = new BigDecimal(value);
    return new Percentage(bigDecimal);
  }

  @Override
  public Optional<BigDecimal> getNullableValue() {
    return Optional.ofNullable(this.value);
  }

  /**
   * プロパティ値を百分率表記にして返却します.
   *
   * @return 百分率表記にした値（保持している値が "0.30123"の場合は, "30.123"%
   */
  public BigDecimal toPercent() {
    return this.getOrZero().movePointRight(2);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 59 * hash + Objects.hashCode(this.getOrZero());
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
    return Objects.equals(this.getOrZero(), other.getOrZero());
  }

  @Override
  public String toString() {
    return Objects.toString(value);
  }

}
