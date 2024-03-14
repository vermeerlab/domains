package org.verneermlab.apps.common.domain.part.numeric;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.NotEmptyType;
import org.verneermlab.base.domain.type.numeric.NullableNumberType;

/**
 * Nullを許容しない数値（汎用）.
 *
 * @author Yamashita.Takahiro
 */
public class NotEmptyNumber implements NotEmptyType<BigDecimal>, NullableNumberType<BigDecimal> {

  private final BigDecimal value;

  private NotEmptyNumber(BigDecimal value) {
    this.value = value;
  }

  /**
   * 数値からインスタンスを生成します.
   *
   * @param value 数値
   * @return 生成したインスタンス
   */
  public static NotEmptyNumber of(Number value) {
    // Nullの保持を許容しません.
    if (Objects.isNull(value)) {
      throw new NullPointerException();
    }
    return new NotEmptyNumber(new BigDecimal(value.doubleValue()));
  }

  /**
   * Nullを許容していないクラスのため、本メソッドを使用する意味はありません.
   *
   * @return プロパティの値
   */
  @Override
  public Optional<BigDecimal> getNullableValue() {
    return Optional.of(this.value);
  }

  @Override
  public BigDecimal getValue() {
    return this.value;
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 81 * hash + Objects.hashCode(this.value);
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
    final NotEmptyNumber other = (NotEmptyNumber) obj;
    return Objects.equals(this.getOrZero(), other.getOrZero());
  }

  @Override
  public String toString() {
    return Objects.toString(value);
  }

}
