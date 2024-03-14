package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;
import org.verneermlab.base.domain.type.time.NullableDateType;

/**
 * 期間月数.
 * <p>
 * プロパティ値がLocalDate
 * </p>
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface DateMonthsRange<T extends NullableDateType<T>> extends NullableDateType<T> {

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param after 指定日
   * @return 期間月数. thisまたはafterのプロパティ値がnullの場合は0
   */
  default Long rangeMonths(T after) {
    if (this.isEmpty() || after.isEmpty()) {
      return 0L;
    }
    var period = Period.between(this.getNullableValue().get(), after.getNullableValue().get());
    Long count = period.toTotalMonths();
    return count;
  }

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param <R> 月数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param function 月数を扱うドメインオブジェクトを生成する関数
   * @return 期間月数を扱うドメインオブジェクト. thisまたはafterのプロパティ値がnullの場合はOptional.empty()
   */
  default <R> Optional<R> rangeMonths(T after, Function<Long, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return Optional.empty();
    }
    Long count = this.rangeMonths(after);
    return Optional.ofNullable(function.apply(count));
  }

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param <R> 月数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param defaultSupplier thisまたはafterのプロパティ値がnullの場合のデフォルトの月数のドメインオブジェクトを生成する関数
   * @param function 月数を扱うドメインオブジェクトを生成する関数
   * @return 月数を扱うドメインオブジェクト
   */
  default <R> R rangeMonths(T after, Supplier<R> defaultSupplier, Function<Long, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return defaultSupplier.get();
    }
    Long count = this.rangeMonths(after);
    return function.apply(count);
  }

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param after 指定日
   * @return 月数（0.5単位での月数）
   */
  default BigDecimal rangeMonthsHalfUp(T after) {
    if (this.isEmpty() || after.isEmpty()) {
      return BigDecimal.ZERO;
    }
    var period = Period.between(this.getNullableValue().get(), after.getNullableValue().get());
    var monthCount = period.toTotalMonths();
    var dayCount = period.minusMonths(monthCount).getDays();

    var halfUpDay = new BigDecimal(dayCount)
            .divide(new BigDecimal(30), 2, RoundingMode.HALF_UP)
            .multiply(new BigDecimal(2)).setScale(0, RoundingMode.HALF_UP)
            .divide(new BigDecimal(2));

    return new BigDecimal(monthCount).add(halfUpDay);
  }

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param <R> 月数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param function 月数を扱うドメインオブジェクトを生成する関数
   * @return 期間月数を扱うドメインオブジェクト（0.5単位での月数）. thisまたはafterのプロパティ値がnullの場合はOptional.empty()
   */
  default <R> Optional<R> rangeMonthsHalfUp(T after, Function<BigDecimal, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return Optional.empty();
    }
    BigDecimal count = this.rangeMonthsHalfUp(after);
    return Optional.ofNullable(function.apply(count));
  }

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param <R> 月数を扱うドメインオブジェクトの型
   * @param after 指定日
   * @param defaultSupplier thisまたはafterのプロパティ値がnullの場合のデフォルトの月数のドメインオブジェクトを生成する関数
   * @param function 月数を扱うドメインオブジェクトを生成する関数
   * @return 月数を扱うドメインオブジェクト（0.5単位での月数）
   */
  default <R> R rangeMonthsHalfUp(T after, Supplier<R> defaultSupplier, Function<BigDecimal, R> function) {
    if (this.isEmpty() || after.isEmpty()) {
      return defaultSupplier.get();
    }
    BigDecimal count = this.rangeMonthsHalfUp(after);
    return function.apply(count);
  }
}
