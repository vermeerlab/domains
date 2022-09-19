package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.util.function.Function;

/**
 * 乗算計算.
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Multiply<T extends Multiply<T>> extends Calculator {

  /**
   * 乗算したインスタンスを返却します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param other 計算するインスタンス
   * @return 計算後のインスタンス
   */
  public <U extends Multiply<U>> T multiply(U other);

  /**
   * 乗算したインスタンスを返却します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定できます.
   * @param <R> 戻り値の型
   * @param other 計算するインスタンス
   * @param newInstance 任意のクラスを生成するコンストラクタもしくはFactoryメソッド
   * @return 計算後のインスタンス
   */
  public <U extends Multiply<U>, R extends Calculator> R multiply(U other, Function<BigDecimal, R> newInstance);
}
