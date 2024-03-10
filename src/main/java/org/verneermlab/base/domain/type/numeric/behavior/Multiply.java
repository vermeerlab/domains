package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;
import org.verneermlab.base.domain.type.numeric.NullableNumberType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 乗算計算.
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Multiply<T extends NullableNumberType<T>> extends NullableNumberType<T>, InstanceCreator<T> {

  /**
   * 乗算したインスタンスを返却します.
   *
   * @param <U> 引数の型, 乗算は自身と異なるクラスを指定することもできます.
   * @param other 計算に使用するインスタンス
   * @return 計算後のインスタンス（自クラスを単位とします）
   */
  default <U extends NullableNumberType<U>> T multiply(U other) {
    return this.multiply(other.getOrZero());
  }

  /**
   * 乗算したインスタンスを返却します.
   * <p>
   * インスタンスの値を単純な数値により乗数計算します.
   * </p>
   *
   * @param <U> 引数の数値の型
   * @param otherValue 計算に使用する数値. 直接数値を指定することを想定しているため、Nullを許容しません.
   * @return 計算後のインスタンス（自クラスを単位とします）
   * @throws NullPointerException otherがNullの場合
   */
  default <U extends Number> T multiply(U otherValue) {
    if (Objects.isNull(otherValue)) {
      throw new NullPointerException();
    }
    var other = BigDecimal.valueOf(otherValue.doubleValue());
    var result = this.getOrZero().multiply(other);
    return this.newInstanceFromThis(result);
  }

  /**
   * 乗算したインスタンスを返却します.
   *
   * @param <U> 引数の型, 乗算は自身と異なる型のクラスを指定することもできます.
   * @param <R> 新たなインスタンスを生成する関数の戻り値の型（本メソッドのインスタンスの型）
   * @param other 計算に使用するインスタンス
   * @param funcNewInstance 任意のクラスを生成する関数
   * @return 計算後のインスタンス（関数で指定した任意の型）
   */
  default <U extends NullableNumberType<U>, R> R multiply(U other, Function<BigDecimal, R> funcNewInstance) {
    return this.multiply(other.getOrZero(), funcNewInstance);
  }

  /**
   * 乗算したインスタンスを返却します.
   *
   * @param <U> 引数の型, 乗算は自身と異なる型のクラスを指定することもできます.
   * @param <R> 新たなインスタンスを生成する関数の戻り値の型（本メソッドのインスタンスの型）
   * @param otherValue 計算に使用する数値. 直接数値を指定することを想定しているため、Nullを許容しません.
   * @param funcNewInstance 任意のクラスを生成する関数
   * @return 計算後のインスタンス（関数で指定した任意の型）
   */
  default <U extends Number, R> R multiply(U otherValue, Function<BigDecimal, R> funcNewInstance) {
    BigDecimal result = this.multiply(otherValue).getOrZero();
    return funcNewInstance.apply(result);
  }
}
