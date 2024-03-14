package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import org.verneermlab.base.domain.type.time.NullableDateType;

/**
 * 期間日数.
 * <p>
 * プロパティ値がLocalDate
 * </p>
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface DateDaysRange<T extends NullableDateType<T>> extends NullableDateType<T> {

  /**
   * 指定日との日数の差異を返却します.
   *
   * @param after 指定日
   * @return 期間日数. thisまたはafterのプロパティ値がnullの場合は0
   */
  default Long rangeDays(T after) {
    if (this.isEmpty() || after.isEmpty()) {
      return 0L;
    }
    Long count = ChronoUnit.DAYS.between(this.getNullableValue().get(), after.getNullableValue().get());
    return count;
  }

  /**
   * 指定日との日数の差異を返却します.
   *
   * @param <R> 日数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param function 日数を扱うドメインオブジェクトを生成する関数
   * @return 期間日数を扱うドメインオブジェクト. thisまたはafterのプロパティ値がnullの場合はOptional.empty()
   */
  default <R> Optional<R> rangeDays(T after, Function<Long, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return Optional.empty();
    }
    Long count = ChronoUnit.DAYS.between(this.getNullableValue().get(), after.getNullableValue().get());
    return Optional.ofNullable(function.apply(count));
  }

  /**
   * 指定日との日数の差異を返却します.
   *
   * @param <R> 日数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param defaultSupplier thisまたはafterのプロパティ値がnullの場合のデフォルトの日数のドメインオブジェクトを生成する関数
   * @param function 日数を扱うドメインオブジェクトを生成する関数
   * @return 日数を扱うドメインオブジェクト
   */
  default <R> R rangeDays(T after, Supplier<R> defaultSupplier, Function<Long, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return defaultSupplier.get();
    }
    Long count = ChronoUnit.DAYS.between(this.getNullableValue().get(), after.getNullableValue().get());
    return function.apply(count);
  }
}
