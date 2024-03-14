package org.verneermlab.base.domain.type.numeric.behavior;

import org.verneermlab.base.domain.type.numeric.NullableNumberType;

/**
 * 数値比較.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface NumericComparator<T extends NullableNumberType<T>> extends NullableNumberType<T> {

  /**
   * equal to.
   *
   * @param other 比較対象
   * @return {@code this = other} を満たす場合 true（nullはZEROとして比較します）
   */
  default boolean eq(T other) {
    return this.getOrZero().compareTo(other.getOrZero()) == 0;
  }

  /**
   * not equal to.
   *
   * @param other 比較対象
   * @return {@code this != other} を満たす場合 true（nullはZEROとして比較します）
   */
  default boolean ne(T other) {
    return this.getOrZero().compareTo(other.getOrZero()) != 0;
  }

  /**
   * less than.
   *
   * @param other 比較対象
   * @return {@code this < other} を満たす場合 true（nullはZEROとして比較します）
   */
  default boolean lt(T other) {
    return this.getOrZero().compareTo(other.getOrZero()) == -1;
  }

  /**
   * less than or equal to.
   *
   * @param other 比較対象
   * @return {@code this <= other} を満たす場合 true（nullはZEROとして比較します）
   */
  default boolean le(T other) {
    return this.eq(other) || this.lt(other);
  }

  /**
   * greater than.
   *
   * @param other 比較対象
   * @return {@code this > other} を満たす場合 true（nullはZEROとして比較します）
   */
  default boolean gt(T other) {
    return this.getOrZero().compareTo(other.getOrZero()) == 1;
  }

  /**
   * greater than or equal to.
   *
   * @param other 比較対象
   * @return {@code this >= other} を満たす場合 true（nullはZEROとして比較します）
   */
  default boolean ge(T other) {
    return this.eq(other) || this.gt(other);
  }

}
