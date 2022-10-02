package org.verneermlab.apps.common.domain.part.time;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;
import org.verneermlab.apps.common.domain.type.IsEmpty;

/**
 * 汎用日付クラス.
 *
 * @author Yamashita.Takahiro
 */
public class DateBase implements IsEmpty {

  private static final DateTimeFormatter formatterYYYYMMDD = DateTimeFormatter.ofPattern("uuuuMMdd");
  private static final DateTimeFormatter formatterYYYYMM = DateTimeFormatter.ofPattern("uuuuMM");
  private static final ZoneId timeZoneId = TimeZone.getTimeZone("Asia/Tokyo").toZoneId();

  private final Optional<LocalDate> value;

  private DateBase(Optional<LocalDate> value) {
    this.value = value;
  }

  /**
   * 値を保持しないインスタンスを生成します.
   *
   * @return 生成したインスタンス
   */
  public static DateBase init() {
    return DateBase.of(Optional.empty());
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
  public static DateBase of(Optional<LocalDate> valueOp) {
    return new DateBase(valueOp);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static DateBase of(LocalDate value) {
    return new DateBase(Optional.ofNullable(value));
  }

  /**
   * 日付文字列からインスタンスを生成します.
   *
   * @param date yyyyMMdd形式の日付文字列
   * @return 生成したインスタンス
   */
  public static DateBase fromYyyyMmDd(String date) {
    if (Objects.isNull(date) || Objects.equals("", date)) {
      return DateBase.init();
    }

    var converted = LocalDate.parse(date, formatterYYYYMMDD);
    return DateBase.of(converted);
  }

  /**
   * 日付文字列（YYYYMMDD形式）を返却します.
   *
   * @return 日付文字列（YYYYMMDD形式）
   */
  public Optional<String> toYyyyMmDd() {
    Optional<String> result = this.value.isPresent()
            ? Optional.of(formatterYYYYMMDD.format(this.value.get()))
            : Optional.empty();
    return result;
  }

  /**
   * 日付文字列（YYYYMM形式）を返却します.
   *
   * @return 日付文字列（YYYYMM形式）
   */
  public Optional<String> toYyyyMm() {
    Optional<String> result = this.value.isPresent()
            ? Optional.of(formatterYYYYMM.format(this.value.get()))
            : Optional.empty();
    return result;
  }

  /**
   * LocalDateをDateへ変換します.
   *
   * @return Data型インスタンス
   */
  public Optional<Date> toDate() {
    Optional<Date> result = this.value.isPresent()
            ? Optional.of(Date.from(this.value.get().atStartOfDay(timeZoneId).toInstant()))
            : Optional.empty();
    return result;
  }

  /**
   * LocalDateをLocalDateTimeへ変換します.
   *
   * @return LocalDateTime型インスタンス
   */
  public Optional<LocalDateTime> toLocalDateTime() {
    Optional<LocalDateTime> result = this.value.isPresent()
            ? Optional.of(this.value.get().atStartOfDay(timeZoneId).toLocalDateTime())
            : Optional.empty();
    return result;
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

    var zonedDateTime = ZonedDateTime.of(this.value.get().atStartOfDay(), timeZoneId);
    return Optional.of(zonedDateTime.toEpochSecond());
  }

  /**
   * 指定数を加算します.
   *
   * @param daysToAdd 加算日数
   * @return 加算後のインスタンス
   */
  public DateBase plusDays(long daysToAdd) {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var result = DateBase.of(this.value.get().plusDays(daysToAdd));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param daysToSubtract 減算日数
   * @return 加算後のインスタンス
   */
  public DateBase minusDays(long daysToSubtract) {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var result = DateBase.of(this.value.get().minusDays(daysToSubtract));
    return result;
  }

  /**
   * 指定数を加算します.
   *
   * @param monthsToAdd 加算月数
   * @return 加算後のインスタンス
   */
  public DateBase plusMonths(long monthsToAdd) {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var result = DateBase.of(this.value.get().plusMonths(monthsToAdd));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param monthsToSubtract 減算月数
   * @return 加算後のインスタンス
   */
  public DateBase minusMonths(long monthsToSubtract) {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var result = DateBase.of(this.value.get().minusMonths(monthsToSubtract));
    return result;
  }

  /**
   * 指定数を加算します.
   *
   * @param yearsToAdd 加算年数
   * @return 加算後のインスタンス
   */
  public DateBase plusYears(long yearsToAdd) {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var result = DateBase.of(this.value.get().plusYears(yearsToAdd));
    return result;
  }

  /**
   * 指定数を減算します.
   *
   * @param yearsToSubtract 減算年数
   * @return 加算後のインスタンス
   */
  public DateBase minusYears(long yearsToSubtract) {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var result = DateBase.of(this.value.get().minusYears(yearsToSubtract));
    return result;
  }

  /**
   * 月初日補正をしたインスタンスを返却します.
   *
   * @return 月初日補正をしたインスタンス
   */
  public DateBase beginMonth() {
    if (this.isEmpty()) {
      return DateBase.init();
    }
    var updated = this.value.get().withDayOfMonth(1);
    return DateBase.of(updated);
  }

  /**
   * 月末日補正をしたインスタンスを返却します.
   *
   * @return 月末日補正をしたインスタンス
   */
  public DateBase endMonth() {
    if (this.isEmpty()) {
      return DateBase.init();
    }

    var beginMonth = this.beginMonth().getValue().get();
    var updated = beginMonth.withDayOfMonth(beginMonth.lengthOfMonth());
    return DateBase.of(updated);
  }

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param after 指定日
   * @return 月数
   */
  public BigDecimal rangeMonth(DateBase after) {
    if (this.isEmpty() || after.isEmpty()) {
      return BigDecimal.ZERO;
    }
    var period = Period.between(this.value.get(), after.getValue().get());
    var monthCount = period.toTotalMonths();

    return new BigDecimal(monthCount);
  }

  /**
   * 指定日との月数の差異を返却します.
   *
   * @param after 指定日
   * @return 月数（0.5単位での月数）
   */
  public BigDecimal rangeMonthHalfUp(DateBase after) {
    if (this.isEmpty() || after.isEmpty()) {
      return BigDecimal.ZERO;
    }
    var period = Period.between(this.value.get(), after.getValue().get());
    var monthCount = period.toTotalMonths();
    var dayCount = period.minusMonths(monthCount).getDays();

    var halfUpDay = new BigDecimal(dayCount)
            .divide(new BigDecimal(30), 2, RoundingMode.HALF_UP)
            .multiply(new BigDecimal(2)).setScale(0, RoundingMode.HALF_UP)
            .divide(new BigDecimal(2));

    return new BigDecimal(monthCount).add(halfUpDay);
  }

  /**
   * 保持している値を返却します.
   *
   * @return 保持している値
   */
  public Optional<LocalDate> getValue() {
    return value;
  }

  @Override
  public boolean isEmpty() {
    return this.value.isEmpty();
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 13 * hash + Objects.hashCode(this.value);
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
    final DateBase other = (DateBase) obj;
    return Objects.equals(this.value.orElse(null), other.value.orElse(null));
  }

  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "Optional.empty";
    }
    return this.value.get().toString();
  }

}
