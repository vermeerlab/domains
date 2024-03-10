package org.verneermlab.base.domain.type.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;
import org.verneermlab.base.domain.type.SinglePropertyObjectType;

/**
 * Nullを許容する日の基底型となるインターフェース.
 * <p>
 * Nullableな日への操作を拡張する機能を提供します.
 * </p>
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface NullableDateType<T> extends SinglePropertyObjectType<LocalDate> {

  /**
   * プロパティがNullであるか判定をします.
   *
   * @return nullの場合はtrue
   */
  default boolean isEmpty() {
    return this.getNullableValue().isEmpty();
  }

  /**
   * 保持する日時に適用するタイムゾーンIDを返却します.
   *
   * @return タイムゾーンID
   */
  default ZoneId getZoneId() {
    return TimeZone.getTimeZone("Asia/Tokyo").toZoneId();
  }
}
