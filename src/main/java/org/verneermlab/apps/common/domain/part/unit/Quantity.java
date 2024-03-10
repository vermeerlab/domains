package org.verneermlab.apps.common.domain.part.unit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.numeric.behavior.Divide;
import org.verneermlab.base.domain.type.numeric.behavior.Minus;
import org.verneermlab.base.domain.type.numeric.behavior.Multiply;
import org.verneermlab.base.domain.type.numeric.behavior.Plus;

/**
 * 数量.
 *
 * @author Yamashita.Takahiro
 */
public class Quantity implements Plus<Quantity>, Minus<Quantity>, Multiply<Quantity>, Divide<Quantity> {

  private static final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
  private static final int DEFAULT_SCALE = 2;
  private static final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.HALF_UP;

  private BigDecimal value;

  private Quantity() {
    Integer value = null;
  }

  private Quantity(BigDecimal value) {
    this.value = value.setScale(DEFAULT_SCALE, DEFAULT_ROUND_MODE);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Quantity of(Number value) {
    if (Objects.isNull(value)) {
      return new Quantity();
    }

    var bigDecimal = BigDecimal.valueOf(value.doubleValue());
    return new Quantity(bigDecimal);
  }

  /**
   * 文字列からモデルを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Quantity of(String value) {
    if (Objects.isNull(value)) {
      return new Quantity();
    }
    var bigDecimal = new BigDecimal(value);
    return new Quantity(bigDecimal);
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
    try {
      var number = decimalFormat.parse(value);
      var bigDecimal = new BigDecimal(number.toString());
      return new Quantity(bigDecimal);
    } catch (ParseException ex) {
      throw new NumberFormatException("Quantity could not parse value = " + value);
    }
  }

  @Override
  public Optional<BigDecimal> getNullableValue() {
    return Optional.ofNullable(this.value);
  }

  /**
   * 数量による除算で割合を導出します.
   * <p>
   * 計算時のScaleと丸めは数量クラスに従います.
   * </p>
   *
   * @param quantity 数量
   * @return 割合.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public Percentage percent(Quantity quantity) {
    return this.divide(quantity, Percentage::ofDecimal);
  }

  /**
   * 数量による除算で割合を導出します.
   * <p>
   * 計算時のscaleは数量クラスに従います.
   * </p>
   *
   * @param quantity 数量
   * @param scale 小数点以下の有効桁数
   * @return 割合.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public Percentage percent(Quantity quantity, int scale) {
    return this.divide(quantity, scale, Percentage::ofDecimal);
  }

  /**
   * 数量による除算で割合を導出します.
   * <p>
   * 計算時の丸めは数量クラスに従います.
   * </p>
   *
   * @param quantity 数量
   * @param roundingMode 丸めモード
   * @return 割合.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public Percentage percent(Quantity quantity, RoundingMode roundingMode) {
    return this.divide(quantity, roundingMode, Percentage::ofDecimal);
  }

  /**
   * 数量による除算で割合を導出します.
   *
   * @param quantity 数量
   * @param scale 小数点以下の有効桁数
   * @param roundingMode 丸めモード
   * @return 割合.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public Percentage percent(Quantity quantity, int scale, RoundingMode roundingMode) {
    return this.divide(quantity, scale, roundingMode, Percentage::ofDecimal);
  }

  @Override
  public RoundingMode getRoundingMode() {
    return DEFAULT_ROUND_MODE;
  }

  public String toFormatted() {
    return decimalFormat.format(this.getOrZero());
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 58 * hash + Objects.hashCode(this.value);
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
    return Objects.equals(this.getOrZero(), other.getOrZero());
  }

  @Override
  public String toString() {
    return this.getOrZero().toPlainString();
  }

}
