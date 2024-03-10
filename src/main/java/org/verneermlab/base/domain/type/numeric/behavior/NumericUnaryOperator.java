package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.util.function.UnaryOperator;
import org.verneermlab.base.domain.type.numeric.NullableNumberType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 保持している数値を編集.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface NumericUnaryOperator<T> extends NullableNumberType<T>, InstanceCreator<T> {

  /**
   * callbackを用いて保持している値を編集して新しいインスタンスを生成します.
   *
   * @param callback コールバック関数
   * @return 編集後の新しいインスタンス.
   */
  default T apply(UnaryOperator<BigDecimal> callback) {
    BigDecimal updated = callback.apply(this.getNullableValue().orElse(null));
    return newInstanceFromThis(updated);
  }
}
