package org.verneermlab.apps.common.domain.part.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.time.NullableDateTimeType;

/**
 * 汎用日時.
 *
 * @author Yamashita.Takahiro
 */
public class GenericDateTime implements NullableDateTimeType<GenericDateTime> {

  private static final DateTimeFormatter formatterYYYYMMDD_Separator
          = DateTimeFormatter.ofPattern("uuuu/MM/dd");
  private static final DateTimeFormatter formatterYYYYMMDD_Second
          = DateTimeFormatter.ofPattern("uuuu/MM/dd HH:mm:ss");

  private final LocalDateTime value;

  private GenericDateTime(LocalDateTime value) {
    this.value = value;
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 日時
   * @return 生成したインスタンス
   */
  public static GenericDateTime of(LocalDateTime value) {
    return new GenericDateTime(value);
  }

  /**
   * 日付文字列からインスタンスを生成します.
   *
   * @param date yyyyMMdd形式の日付文字列
   * @return 生成したインスタンス
   */
  public static GenericDateTime fromYyyyMmDdSeparator(String date) {
    if (Objects.equals("", date)) {
      return new GenericDateTime(null);
    }
    var localDate = LocalDate.parse(date, formatterYYYYMMDD_Separator);
    var converted = LocalDateTime.of(localDate, LocalTime.MIN);
    return new GenericDateTime(converted);
  }

  @Override
  public Optional<LocalDateTime> getNullableValue() {
    return Optional.ofNullable(value);
  }

  /**
   * 日付文字列（YYYY/MM/DD形式）を返却します.
   *
   * @return 日付文字列
   */
  public Optional<String> toYyyyMmDdWithSeparator() {
    Optional<String> result = Objects.nonNull(value)
            ? Optional.of(formatterYYYYMMDD_Separator.format(value))
            : Optional.empty();
    return result;
  }

  /**
   * 日付文字列（YYYY/MM/DD hh:MM:ss）を返却します.
   *
   * @return 日付文字列
   */
  public Optional<String> toYyyyMmDdHhMmSs() {
    Optional<String> result = Objects.nonNull(value)
            ? Optional.of(formatterYYYYMMDD_Second.format(value))
            : Optional.empty();
    return result;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + Objects.hashCode(this.value);
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
    final GenericDateTime other = (GenericDateTime) obj;
    return Objects.equals(this.value, other.value);
  }

  @Override
  public String toString() {
    return Objects.toString(value);
  }

}
