package org.verneermlab.base.domain.type.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.TimeZone;
import org.verneermlab.base.domain.type.SinglePropertyObjectType;

/**
 * Nullを許容する日時の基底型となるインターフェース.
 * <p>
 * Nullableな日時への操作を拡張する機能を提供します.
 * </p>
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface NullableDateTimeType<T> extends SinglePropertyObjectType<LocalDateTime> {

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
   * プロパティ値をLocalDateへ変換して返却します.
   *
   * @return LocalDate
   */
  default Optional<LocalDate> toLocalDate() {
    if (this.getNullableValue().isEmpty()) {
      return Optional.empty();
    }

    var dateTime = this.getNullableValue().get();
    return Optional.of(LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth()));
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
    var zonedDateTime = ZonedDateTime.of(this.getNullableValue().get(), this.getZoneId());
    return Optional.of(zonedDateTime.toEpochSecond());
  }

}
