package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.function.Function;
import org.verneermlab.base.domain.type.numeric.NullableNumberType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 除算計算.
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Divide<T extends NullableNumberType<T>> extends NullableNumberType<T>, InstanceCreator<T> {

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは自インスタンス（this）で定義されたものを適用します.
   * </p>
   * 計算に使用するScaleと丸めは自インスタンス（this）で定義されたものを使用します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 除算の分母となるインスタンス
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   * @throws ArithmeticException 割り切れない場合
   */
  default <U extends NullableNumberType<U>> T divide(U other) {
    return this.divide(other, this.getScale(), this.getRoundingMode());
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは自インスタンス（this）で定義されたものを適用します.
   * </p>
   * 計算に使用する丸めは自インスタンス（this）で定義されたものを使用します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 除算の分母となるインスタンス
   * @param scale 小数点以下の有効桁数
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   * @throws ArithmeticException 割り切れない場合
   */
  default <U extends NullableNumberType<U>> T divide(U other, int scale) {
    return this.divide(other, scale, this.getRoundingMode());
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは自インスタンス（this）で定義されたものを適用します.
   * </p>
   * 計算に使用する丸めは自インスタンス（this）で定義されたものを使用します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 除算の分母となるインスタンス
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   * @throws ArithmeticException 割り切れない場合
   */
  default <U extends NullableNumberType<U>> T divide(U other, RoundingMode roundingMode) {
    return this.divide(other, this.getScale(), roundingMode);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは自インスタンス（this）で定義されたものを適用します.
   * </p>
   * 計算に使用する丸めは自インスタンス（this）で定義されたものを使用します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 除算の分母となるインスタンス
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   * @throws ArithmeticException 割り切れない場合
   */
  default <U extends Number> T divide(U other, RoundingMode roundingMode) {
    return this.divide(other, this.getScale(), roundingMode);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは自インスタンス（this）で定義されたものを適用します.
   * </p>
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 除算の分母となるインスタンス
   * @param scale 小数点以下の有効桁数
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends NullableNumberType<U>> T divide(U other, int scale, RoundingMode roundingMode) {
    if (other.isEmpty()) {
      return this.newInstanceFromThis();
    }

    return this.divide(other.getOrZero(), scale, roundingMode);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは自インスタンス（this）で定義されたものを適用します.
   * </p>
   *
   * @param <U> 引数の数値の型
   * @param otherValue 計算に使用する数値. 直接数値を指定することを想定しているため、Nullを許容しません.
   * @param scale 小数点以下の有効桁数
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends Number> T divide(U otherValue, int scale, RoundingMode roundingMode) {
    if (Objects.isNull(otherValue)) {
      throw new NullPointerException();
    }

    var other = BigDecimal.valueOf(otherValue.doubleValue());

    if (other.compareTo(BigDecimal.ZERO) == 0) {
      return this.newInstanceFromThis(BigDecimal.ZERO);
    }

    BigDecimal result = this.getOrZero().divide(other, scale, roundingMode);
    return this.newInstanceFromThis(result);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは関数で生成するクラスの定義を適用します.
   * </p>
   * 計算に使用するScaleと丸めは計算に使用するインスタンス（other）で定義されたものを使用します.
   *
   * @param <U> 引数の型, 除算は自身と異なる型のクラスを指定することもできます.
   * @param <R> 新たなインスタンスを生成する関数の戻り値の型
   * @param other 計算するインスタンス
   * @param funcNewInstance 任意のクラスを生成するコンストラクタもしくはFactoryメソッド
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   * @throws ArithmeticException 割り切れない場合
   */
  default <U extends NullableNumberType<U>, R extends NullableNumberType<R>> R divide(
          U other, Function<BigDecimal, R> funcNewInstance) {
    return this.divide(other, other.getScale(), other.getRoundingMode(), funcNewInstance);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは関数で生成するクラスの定義を適用します.
   * </p>
   * 計算に使用する丸めは計算に使用するインスタンス（other）で定義されたものを使用します.
   *
   * @param <U> 引数の型, 除算は自身と異なる型のクラスを指定することもできます.
   * @param <R> 新たなインスタンスを生成する関数の戻り値の型
   * @param other 計算するインスタンス
   * @param scale 小数点以下の有効桁数
   * @param funcNewInstance 任意のクラスを生成するコンストラクタもしくはFactoryメソッド
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   * @throws ArithmeticException 割り切れない場合
   */
  default <U extends NullableNumberType<U>, R extends NullableNumberType<R>> R divide(
          U other, int scale, Function<BigDecimal, R> funcNewInstance) {
    return this.divide(other, scale, other.getRoundingMode(), funcNewInstance);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは関数で生成するクラスの定義を適用します.
   * </p>
   * 計算に使用する丸めは計算に使用するインスタンス（other）で定義されたものを使用します.
   *
   * @param <U> 引数の型, 除算は自身と異なる型のクラスを指定することもできます.
   * @param <R> 新たなインスタンスを生成する関数の戻り値の型
   * @param other 計算するインスタンス
   * @param roundingMode 丸めモード
   * @param funcNewInstance 任意のクラスを生成するコンストラクタもしくはFactoryメソッド
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends NullableNumberType<U>, R extends NullableNumberType<R>> R divide(
          U other, RoundingMode roundingMode, Function<BigDecimal, R> funcNewInstance) {
    int scale = other.getOrZero().scale();
    return this.divide(other, scale, roundingMode, funcNewInstance);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 計算後のインスタンスのScaleと丸めは関数で生成するクラスの定義を適用します.
   * </p>
   *
   * @param <U> 引数の型, 除算は自身と異なる型のクラスを指定することもできます.
   * @param <R> 新たなインスタンスを生成する関数の戻り値の型
   * @param other 計算に使用するインスタンス
   * @param scale 小数点以下の有効桁数
   * @param roundingMode 丸めモード
   * @param funcNewInstance 任意のクラスを生成する関数
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends NullableNumberType<U>, R extends NullableNumberType<R>> R divide(
          U other, int scale, RoundingMode roundingMode, Function<BigDecimal, R> funcNewInstance) {

    if (other.getOrZero().compareTo(BigDecimal.ZERO) == 0) {
      return funcNewInstance.apply(BigDecimal.ZERO);
    }

    var result = this.getOrZero().divide(other.getOrZero(), scale, roundingMode);
    return funcNewInstance.apply(result);
  }
}
