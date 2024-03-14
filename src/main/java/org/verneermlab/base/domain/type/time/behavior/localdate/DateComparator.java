package org.verneermlab.base.domain.type.time.behavior.localdate;

import org.verneermlab.base.domain.type.time.NullableDateType;

/**
 * 日付比較.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface DateComparator<T extends NullableDateType<T>> extends NullableDateType<T> {

  /**
   * equal to.
   * <p>
   * 保持している値がnullの場合は最も小さい日付相当で比較
   * </p>
   *
   * @param other 比較対象
   * @return {@code this = other} を満たす場合 true
   */
  default boolean eq(T other) {
    if (this.getNullableValue().isEmpty() && other.getNullableValue().isEmpty()) {
      return true;
    }

    if (this.getNullableValue().isEmpty() || other.getNullableValue().isEmpty()) {
      return false;
    }

    return this.getNullableValue().get().isEqual(other.getNullableValue().get());
  }

  /**
   * not equal to.
   * <p>
   * 保持している値がnullの場合は最も小さい日付相当で比較
   * </p>
   *
   * @param other 比較対象
   * @return {@code this != other} を満たす場合 true
   */
  default boolean ne(T other) {
    return !this.eq(other);
  }

  /**
   * less than.
   * <p>
   * 保持している値がnullの場合は最も小さい日付相当で比較
   * </p>
   *
   * @param other 比較対象
   * @return {@code this < other} を満たす場合 true
   */
  default boolean lt(T other) {
    if (this.eq(other)) {
      return false;
    }

    if (other.isEmpty()) {
      return false;
    }

    if (this.isEmpty()) {
      return true;
    }

    return this.getNullableValue().get().isBefore(other.getNullableValue().get());
  }

  /**
   * less than or equal to.
   * <p>
   * 保持している値がnullの場合は最も小さい日付相当で比較
   * </p>
   *
   * @param other 比較対象
   * @return {@code this <= other} を満たす場合 true
   */
  default boolean le(T other) {
    return this.eq(other) || this.lt(other);
  }

  /**
   * greater than.
   * <p>
   * 保持している値がnullの場合は最も小さい日付相当で比較
   * </p>
   *
   * @param other 比較対象
   * @return {@code this > other} を満たす場合 true
   */
  default boolean gt(T other) {
    if (this.eq(other)) {
      return false;
    }

    if (other.isEmpty()) {
      return true;
    }

    if (this.isEmpty()) {
      return false;
    }

    return this.getNullableValue().get().isAfter(other.getNullableValue().get());
  }

  /**
   * greater than or equal to.
   * <p>
   * 保持している値がnullの場合は最も小さい日付相当で比較
   * </p>
   *
   * @param other 比較対象
   * @return {@code this >= other} を満たす場合 true
   */
  default boolean ge(T other) {
    return this.eq(other) || this.gt(other);
  }

}
