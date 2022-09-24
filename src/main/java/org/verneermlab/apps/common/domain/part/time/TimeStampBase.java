package org.verneermlab.apps.common.domain.part.time;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import org.verneermlab.apps.common.domain.type.IsEmpty;

/**
 * 汎用日時クラス.
 *
 * @author Yamashita.Takahiro
 */
public class TimeStampBase implements IsEmpty {

  private static final ZoneId timeZoneId = TimeZone.getTimeZone("Asia/Tokyo").toZoneId();
  private static final DateTimeFormatter formatterYYYYMMDD_Separator
          = DateTimeFormatter.ofPattern("uuuu/MM/dd");
  private static final DateTimeFormatter formatterYYYYMMDD_Second
          = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");

  private final Optional<LocalDateTime> value;

  private TimeStampBase(Optional<LocalDateTime> value) {
    this.value = value;
  }

  /**
   * 値を保持しないインスタンスを生成します.
   *
   * @return 生成したインスタンス
   */
  public static TimeStampBase init() {
    return TimeStampBase.of(Optional.empty());
  }

  /**
   * インスタンスを生成します.
   * <p>
   * フロントや永続化時に<code>null</code>を設定されている場合に使用することを想定しています.
   * </p>
   *
   * @param valueOp 値
   * @return 生成したインスタンス
   */
  public static TimeStampBase of(Optional<LocalDateTime> valueOp) {
    return new TimeStampBase(valueOp);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static TimeStampBase of(LocalDateTime value) {
    return new TimeStampBase(Optional.ofNullable(value));
  }

  /**
   * 日付文字列からインスタンスを生成します.
   *
   * @param date yyyyMMdd形式の日付文字列
   * @return 生成したインスタンス
   */
  public static TimeStampBase fromYyyyMmDd(String date) {
    if (Objects.isNull(date) || Objects.equals("", date)) {
      return TimeStampBase.init();
    }

    var dateBase = DateBase.fromYyyyMmDd(date);
    return TimeStampBase.from(dateBase);
  }

  /**
   * 汎用日付クラスからインスタンスを生成します.
   *
   * @param dateBase 汎用日付クラス
   * @return 生成したインスタンス
   */
  public static TimeStampBase from(DateBase dateBase) {
    return TimeStampBase.of(dateBase.toLocalDateTime());
  }

  /**
   * 日付文字列（YYYY/MM/DD形式）を返却します.
   *
   * @return 日付文字列
   */
  public Optional<String> toYyyyMmDdWithSeparator() {
    Optional<String> result = this.value.isPresent()
            ? Optional.of(formatterYYYYMMDD_Separator.format(this.value.get()))
            : Optional.empty();
    return result;
  }

  /**
   * 日付文字列（YYYY/MM/DD hh:MM:ss）を返却します.
   *
   * @return 日付文字列
   */
  public Optional<String> toYyyyMmDdHhMmSs() {
    Optional<String> result = this.value.isPresent()
            ? Optional.of(formatterYYYYMMDD_Second.format(this.value.get()))
            : Optional.empty();
    return result;
  }

  /**
   * 汎用日付インスタンスへ変換します.
   *
   * @return 汎用日付インスタンス
   */
  public DateBase toDateBase() {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var dateBase = DateBase.of(this.value.get().toLocalDate());
    return dateBase;
  }

  /**
   * LocalDateをDateへ変換します.
   *
   * @return Data型インスタンス
   */
  public Optional<Date> toDate() {
    if (this.isEmpty()) {
      return Optional.empty();
    }

    var zonedDateTime = ZonedDateTime.of(this.value.get(), timeZoneId);
    var instant = zonedDateTime.toInstant();
    return Optional.of(Date.from(instant));
  }

  /**
   * UnixTimeへ変換します.
   *
   * @return UnixTime
   */
  public Optional<Long> toUnixTime() {
    if (this.isEmpty()) {
      return Optional.empty();
    }

    var zonedDateTime = ZonedDateTime.of(this.value.get(), timeZoneId);
    return Optional.of(zonedDateTime.toEpochSecond());
  }

  /**
   * 指定数を加算します.
   *
   * @param seconds 加算秒数
   * @return 加算後のインスタンス
   */
  public TimeStampBase plusSeconds(long seconds) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().plusSeconds(seconds));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param seconds 減算秒数
   * @return 加算後のインスタンス
   */
  public TimeStampBase minusSeconds(long seconds) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().minusSeconds(seconds));
    return result;
  }

  /**
   * 指定数を加算します.
   *
   * @param minutes 加算分数
   * @return 加算後のインスタンス
   */
  public TimeStampBase plusMinutes(long minutes) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().plusMinutes(minutes));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param minutes 減算分数
   * @return 加算後のインスタンス
   */
  public TimeStampBase minusMinutes(long minutes) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().minusMinutes(minutes));
    return result;
  }

  /**
   * 指定数を加算します.
   *
   * @param hours 加算時間数
   * @return 加算後のインスタンス
   */
  public TimeStampBase plusHours(long hours) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().plusHours(hours));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param hours 減算時間数
   * @return 加算後のインスタンス
   */
  public TimeStampBase minusHours(long hours) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().minusHours(hours));
    return result;
  }

  /**
   * 指定数を加算します.
   *
   * @param daysToAdd 加算日数
   * @return 加算後のインスタンス
   */
  public TimeStampBase plusDays(long daysToAdd) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().plusDays(daysToAdd));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param daysToSubtract 減算日数
   * @return 加算後のインスタンス
   */
  public TimeStampBase minusDays(long daysToSubtract) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().minusDays(daysToSubtract));
    return result;
  }

  /**
   * 指定数を加算します.
   *
   * @param monthsToAdd 加算月数
   * @return 加算後のインスタンス
   */
  public TimeStampBase plusMonths(long monthsToAdd) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().plusMonths(monthsToAdd));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param monthsToSubtract 減算月数
   * @return 加算後のインスタンス
   */
  public TimeStampBase minusMonths(long monthsToSubtract) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().minusMonths(monthsToSubtract));
    return result;
  }

  /**
   * 指定数を加算します.
   *
   * @param yearsToAdd 加算年数
   * @return 加算後のインスタンス
   */
  public TimeStampBase plusYears(long yearsToAdd) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().plusYears(yearsToAdd));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param yearsToSubtract 減算年数
   * @return 加算後のインスタンス
   */
  public TimeStampBase minusYears(long yearsToSubtract) {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var result = TimeStampBase.of(this.value.get().minusYears(yearsToSubtract));
    return result;
  }

  /**
   * 月初日補正をしたインスタンスを返却します.
   *
   * @return 月初日補正をしたインスタンス
   */
  public TimeStampBase beginMonth() {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }
    var updated = this.value.get().withDayOfMonth(1);
    return TimeStampBase.of(updated);
  }

  /**
   * 月末日補正をしたインスタンスを返却します.
   *
   * @return 月末日補正をしたインスタンス
   */
  public TimeStampBase endMonth() {
    if (this.isEmpty()) {
      return TimeStampBase.init();
    }

    var localDateTime = this.beginMonth().getValue().get();
    var localDate = this.toDateBase().getValue().get();
    var updated = localDateTime.withDayOfMonth(localDate.lengthOfMonth());
    return TimeStampBase.of(updated);
  }

  /**
   * 保持している値を返却します.
   *
   * @return 保持している値
   */
  public Optional<LocalDateTime> getValue() {
    return value;
  }

  /**
   * 比較をします.
   *
   * @param other 比較対象
   * @return 等しい場合はtrue、または いずれかのインスタンスの値が<code>Optional.empty</code>の場合はfalse
   */
  public boolean isEquel(TimeStampBase other) {
    if (this.isEmpty() && other.isEmpty()) {
      return true;
    }
    if (this.isEmpty() || other.isEmpty()) {
      return false;
    }
    var result = this.value.get().isEqual(other.getValue().get());
    return result;
  }

  /**
   * 比較をします.
   *
   * @param other 比較対象
   * @return 等しくない場合はtrue、または いずれかのインスタンスの値が<code>Optional.empty</code>の場合はfalse
   */
  public boolean isNotEquel(TimeStampBase other) {
    return this.isEquel(other) == false;
  }

  /**
   * 比較をします.
   *
   * @param other 比較対象
   * @return 比較対象が以前（本インスタンス（this） ＜ 比較対象（other））の日時の場合はtrue、または いずれかのインスタンスの値が<code>Optional.empty</code>の場合はfalse
   */
  public boolean isBefore(TimeStampBase other) {
    if (this.isEmpty() || other.isEmpty()) {
      return false;
    }
    var result = this.value.get().isBefore(other.getValue().get());
    return result;
  }

  /**
   * 比較をします.
   *
   * @param other 比較対象
   * @return 比較対象が以降（比較対象（other） ＜ 本インスタンス（this））の日時の場合はtrue、または いずれかのインスタンスの値が<code>Optional.empty</code>の場合はfalse
   */
  public boolean isAfter(TimeStampBase other) {
    if (this.isEmpty() || other.isEmpty()) {
      return false;
    }
    var result = this.value.get().isAfter(other.getValue().get());
    return result;
  }

  @Override
  public boolean isEmpty() {
    return this.value.isEmpty();
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 11 * hash + Objects.hashCode(this.value);
    return hash;

  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final TimeStampBase other = (TimeStampBase) obj;
    return Objects.equals(this.value.orElse(null), other.value.orElse(null));
  }

  @Override
  public String toString() {
    if (this.isEmpty()) {
      return null;
    }
    return this.value.get().toString();
  }

}
