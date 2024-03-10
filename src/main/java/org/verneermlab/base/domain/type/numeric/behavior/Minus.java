package org.verneermlab.base.domain.type.numeric.behavior;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.verneermlab.base.domain.type.numeric.NullableNumberType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 減算計算.
 * <p>
 * 減算の型を同一とすることで単位が相違しないことを強制します. <br>
 * </p>
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Minus<T extends NullableNumberType<T>> extends NullableNumberType<T>, InstanceCreator<T> {

  /**
   * 減算したインスタンスを返却します.
   *
   * @param other 計算するインスタンス
   * @return 計算後のインスタンス
   */
  @SuppressWarnings("unchecked")
  default T minus(T... other) {
    if (other.length == 1) {
      var result = this.getOrZero().subtract(other[0].getOrZero());
      return this.newInstanceFromThis(result);
    }
    BigDecimal result = Stream.of(other)
            .map(T::getOrZero)
            .reduce(this.getOrZero(), BigDecimal::subtract);

    return this.newInstanceFromThis(result);
  }

}
