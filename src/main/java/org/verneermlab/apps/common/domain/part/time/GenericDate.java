package org.verneermlab.apps.common.domain.part.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.time.NullableDateType;
import org.verneermlab.base.domain.type.time.behavior.localdate.DateComparator;
import org.verneermlab.base.domain.type.time.behavior.localdate.DateDaysRange;
import org.verneermlab.base.domain.type.time.behavior.localdate.DateMonthsRange;
import org.verneermlab.base.domain.type.time.behavior.localdate.DateMonthsShift;
import org.verneermlab.base.domain.type.time.behavior.localdate.DateUnaryOperator;
import org.verneermlab.base.domain.type.time.behavior.localdate.DateYearsRange;

/**
 * 汎用日付.
 *
 * @author Yamashita.Takahiro
 */
public class GenericDate implements NullableDateType<GenericDate>,
        DateUnaryOperator<GenericDate>, DateComparator<GenericDate>,
        DateDaysRange<GenericDate>, DateMonthsRange<GenericDate>,
        DateMonthsShift<GenericDate>, DateYearsRange<GenericDate> {

  private static final DateTimeFormatter formatterYYYYMMDD = DateTimeFormatter.ofPattern("uuuuMMdd");
  private static final DateTimeFormatter formatterYYYYMM = DateTimeFormatter.ofPattern("uuuuMM");

  private final LocalDate value;

  private GenericDate(LocalDate value) {
    this.value = value;
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 日付
   * @return 生成したインスタンス
   */
  public static GenericDate of(LocalDate value) {
    return new GenericDate(value);
  }

  /**
   * 日付文字列からインスタンスを生成します.
   *
   * @param date yyyyMMdd形式の日付文字列
   * @return 生成したインスタンス
   */
  public static GenericDate fromYyyyMmDd(String date) {
    if (Objects.equals("", date)) {
      return new GenericDate(null);
    }
    var converted = LocalDate.parse(date, formatterYYYYMMDD);
    return new GenericDate(converted);
  }

  @Override
  public Optional<LocalDate> getNullableValue() {
    return Optional.ofNullable(value);
  }

  /**
   * 日付文字列（YYYYMMDD形式）を返却します.
   *
   * @return 日付文字列（YYYYMMDD形式）
   */
  public Optional<String> toYyyyMmDd() {
    Optional<String> result = Objects.nonNull(this.value)
            ? Optional.of(formatterYYYYMMDD.format(this.value))
            : Optional.empty();
    return result;
  }

  /**
   * 日付文字列（YYYYMM形式）を返却します.
   *
   * @return 日付文字列（YYYYMM形式）
   */
  public Optional<String> toYyyyMm() {
    Optional<String> result = Objects.nonNull(this.value)
            ? Optional.of(formatterYYYYMM.format(this.value))
            : Optional.empty();
    return result;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 97 * hash + Objects.hashCode(this.value);
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
    final GenericDate other = (GenericDate) obj;
    return Objects.equals(this.value, other.value);
  }

  @Override
  public String toString() {
    return Objects.toString(value);
  }

}
