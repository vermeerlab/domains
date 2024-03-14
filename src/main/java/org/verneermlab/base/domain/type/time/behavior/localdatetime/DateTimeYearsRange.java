package org.verneermlab.base.domain.type.time.behavior.localdatetime;

import java.time.Period;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import org.verneermlab.base.domain.type.time.NullableDateTimeType;

/**
 * 期間年数.
 * <p>
 * プロパティ値がLocalDate
 * </p>
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface DateTimeYearsRange<T extends NullableDateTimeType<T>> extends NullableDateTimeType<T> {

  /**
   * 指定日との年数の差異を返却します.
   *
   * @param after 指定日
   * @return 期間年数. thisまたはafterのプロパティ値がnullの場合は0
   */
  default int rangeYears(T after) {
    if (this.isEmpty() || after.isEmpty()) {
      return 0;
    }
    var period = Period.between(this.getNullableValue().get().toLocalDate(), after.getNullableValue().get().toLocalDate());
    int count = period.getYears();
    return count;
  }

  /**
   * 指定日との年数の差異を返却します.
   *
   * @param <R> 年数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param function 年数を扱うドメインオブジェクトを生成する関数
   * @return 期間年数を扱うドメインオブジェクト. thisまたはafterのプロパティ値がnullの場合はOptional.empty()
   */
  default <R> Optional<R> rangeYears(T after, Function<Integer, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return Optional.empty();
    }
    var period = Period.between(this.getNullableValue().get().toLocalDate(), after.getNullableValue().get().toLocalDate());
    int count = period.getYears();
    return Optional.ofNullable(function.apply(count));
  }

  /**
   * 指定日との年数の差異を返却します.
   *
   * @param <R> 年数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param defaultSupplier thisまたはafterのプロパティ値がnullの場合のデフォルトの年数のドメインオブジェクトを生成する関数
   * @param function 年数を扱うドメインオブジェクトを生成する関数
   * @return 年数を扱うドメインオブジェクト
   */
  default <R> R rangeYears(T after, Supplier<R> defaultSupplier, Function<Integer, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return defaultSupplier.get();
    }
    var period = Period.between(this.getNullableValue().get().toLocalDate(), after.getNullableValue().get().toLocalDate());
    int count = period.getYears();
    return function.apply(count);
  }
}
