package org.verneermlab.base.domain.type.time.behavior.localdate;

import java.time.LocalDate;
import org.verneermlab.base.domain.type.time.NullableDateType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 日付（月）の補正.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface DateMonthsShift<T extends NullableDateType<T>> extends NullableDateType<T>, InstanceCreator<T> {

  /**
   * 月初日補正をしたインスタンスを返却します.
   *
   * @return 月初日補正をしたインスタンス
   */
  default T beginMonth() {
    if (this.isEmpty()) {
      return newInstanceFromThis();
    }
    LocalDate updated = this.getNullableValue().get().withDayOfMonth(1);
    return newInstanceFromThis(updated);
  }

  /**
   * 月末日補正をしたインスタンスを返却します.
   *
   * @return 月末日補正をしたインスタンス
   */
  default T endMonth() {
    if (this.isEmpty()) {
      return newInstanceFromThis();
    }
    var date = this.getNullableValue().get();
    var updated = this.getNullableValue().get().withDayOfMonth(date.lengthOfMonth());
    return newInstanceFromThis(updated);
  }

}
