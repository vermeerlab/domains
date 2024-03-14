package org.verneermlab.base.domain.type.time.behavior.localdatetime;

import java.time.LocalDateTime;
import org.verneermlab.base.domain.type.time.NullableDateTimeType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 日時（月）の補正.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface DateTimeMonthsShift<T extends NullableDateTimeType<T>> extends NullableDateTimeType<T>, InstanceCreator<T> {

  /**
   * 月初日補正をしたインスタンスを返却します.
   *
   * @return 月初日補正をしたインスタンス
   */
  default T beginMonth() {
    if (this.isEmpty()) {
      return newInstanceFromThis();
    }
    LocalDateTime updated = this.getNullableValue().get().toLocalDate().atStartOfDay().withDayOfMonth(1);
    return newInstanceFromThis(updated);
  }

  /**
   * 月末日補正をしたインスタンスを返却します.
   *
   * @return 月初日補正をしたインスタンス
   */
  default T endMonth() {
    if (this.isEmpty()) {
      return newInstanceFromThis();
    }
    var date = this.getNullableValue().get().toLocalDate().plusMonths(1L)
            .atStartOfDay().withDayOfMonth(1).minusNanos(1L);
    return newInstanceFromThis(date);
  }

}
