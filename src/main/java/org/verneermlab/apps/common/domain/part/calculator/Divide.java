package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

/**
 * 除算計算.
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Divide<T extends Divide<T>> extends Calculator {

  /**
   * インスタンスが保持しているscaleを返却します.
   *
   * @return プロパティで保持している値
   */
  Integer getScale();

  /**
   * インスタンスが保持しているRoundingModeを返却します.
   *
   * @return プロパティで保持している値
   */
  RoundingMode getRoundingMode();

  /**
   * 除算したインスタンスを返却します. 小数点以下の桁数（scale）と丸め（{@code RoundingMode}）はインスタンスのデフォルトを使用します.<br>
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 計算するインスタンス
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends Divide<U>> T divide(U other) {
    return this.divide(other, this.getScale(), this.getRoundingMode());
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 小数点以下の桁数（scale）はインスタンスのデフォルトを使用します.
   * </p>
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 計算するインスタンス
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends Divide<U>> T divide(U other, RoundingMode roundingMode) {
    return this.divide(other, this.getScale(), roundingMode);
  }

  /**
   * 除算したインスタンスを返却します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 計算するインスタンス
   * @param scale 小数点以下の有効桁数
   * @param roundingMode 丸めモード
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public <U extends Divide<U>> T divide(U other, int scale, RoundingMode roundingMode);

  /**
   * 除算したインスタンスを返却します. 小数点以下の桁数（scale）と丸め（{@code RoundingMode}）はインスタンスのデフォルトを使用します.<br>
   *
   * @param <U> 引数の型
   * @param <R> 戻り値の型
   * @param other 計算するインスタンス
   * @param newInstance 任意のクラスを生成するコンストラクタもしくはFactoryメソッド
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends Divide<U>, R extends Calculator> R divide(
          U other, Function<BigDecimal, R> newInstance) {
    return this.divide(other, this.getScale(), this.getRoundingMode(), newInstance);
  }

  /**
   * 除算したインスタンスを返却します.
   * <p>
   * 小数点以下の桁数（scale）はインスタンスのデフォルトを使用します.
   * </p>
   *
   * @param <U> 引数の型
   * @param <R> 戻り値の型
   * @param other 計算するインスタンス
   * @param roundingMode 丸めモード
   * @param newInstance 任意のクラスを生成するコンストラクタもしくはFactoryメソッド
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  default <U extends Divide<U>, R extends Calculator> R divide(
          U other, RoundingMode roundingMode, Function<BigDecimal, R> newInstance) {
    return this.divide(other, this.getScale(), roundingMode, newInstance);
  }

  /**
   * 除算したインスタンスを返却します.
   *
   * @param <U> 引数の型
   * @param <R> 戻り値の型
   * @param other 計算するインスタンス
   * @param scale 小数点以下の有効桁数
   * @param roundingMode 丸めモード
   * @param newInstance 任意のクラスを生成するコンストラクタもしくはFactoryメソッド
   * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
   */
  public <U extends Divide<U>, R extends Calculator> R divide(
          U other, int scale, RoundingMode roundingMode, Function<BigDecimal, R> newInstance);
}
