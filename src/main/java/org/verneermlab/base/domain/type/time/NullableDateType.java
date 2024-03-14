package org.verneermlab.base.domain.type.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;
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

  /**
   * プロパティ値をDateへ変換します.
   *
   * @return Data型インスタンス
   */
  default Optional<Date> toDate() {
    if (this.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(Date.from(
            this.getNullableValue().get().atStartOfDay(this.getZoneId()).toInstant()));
  }

  /**
   * プロパティ値をLocalDateTimeへ変換します.
   *
   * @return LocalDateTime型インスタンス
   */
  default Optional<LocalDateTime> toLocalDateTime() {
    if (this.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(this.getNullableValue().get().atStartOfDay(this.getZoneId()).toLocalDateTime());
  }

  /**
   * プロパティ値をUnixTimeへ変換します.
   *
   * @return UnixTime
   */
  default Optional<Long> toUnixTime() {
    if (this.isEmpty()) {
      return Optional.empty();
    }
    var zonedDateTime = ZonedDateTime.of(this.getNullableValue().get().atStartOfDay(), this.getZoneId());
    return Optional.of(zonedDateTime.toEpochSecond());
  }
}
