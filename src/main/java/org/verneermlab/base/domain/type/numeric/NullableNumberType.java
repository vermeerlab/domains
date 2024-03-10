package org.verneermlab.base.domain.type.numeric;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.verneermlab.base.domain.type.SinglePropertyObjectType;

/**
 * Nullを許容する数値の基底型となるインターフェース.
 * <p>
 * Nullableな数値への操作を拡張する機能を提供します.
 * </p>
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface NullableNumberType<T> extends SinglePropertyObjectType<BigDecimal> {

  /**
   * プロパティの値を返却します.
   *
   * @return <code>null</code>の場合はZERO
   */
  default BigDecimal getOrZero() {
    return this.getNullableValue().orElse(BigDecimal.ZERO);
  }

  /**
   * プロパティがNullであるか判定をします.
   *
   * @return nullの場合はtrue
   */
  default boolean isEmpty() {
    return this.getNullableValue().isEmpty();
  }

  /**
   * 保持する値に適用するScaleを返却します.
   *
   * @return 除算に使用する丸め
   */
  default int getScale() {
    return 0;
  }

  /**
   * 計算に使用する丸めを返却します.
   *
   * @return 除算に使用する丸め
   */
  default RoundingMode getRoundingMode() {
    return RoundingMode.UNNECESSARY;
  }

}
