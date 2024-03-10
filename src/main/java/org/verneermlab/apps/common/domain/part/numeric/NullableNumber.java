package org.verneermlab.apps.common.domain.part.numeric;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.numeric.NullableNumberType;

/**
 * Nullを許容する数値（汎用）.
 *
 * @author Yamashita.Takahiro
 */
public class NullableNumber implements NullableNumberType<BigDecimal> {

  private final BigDecimal value;

  private NullableNumber() {
    this.value = null;
  }

  private NullableNumber(BigDecimal value) {
    this.value = value;
  }

  /**
   * 数値からインスタンスを生成します.
   *
   * @param value 数値
   * @return 生成したインスタンス
   */
  public static NullableNumber of(Number value) {
    if (Objects.isNull(value)) {
      return new NullableNumber();
    }
    return new NullableNumber(new BigDecimal(value.doubleValue()));
  }

  @Override
  public Optional<BigDecimal> getNullableValue() {
    return Optional.ofNullable(this.value);
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
    final NullableNumber other = (NullableNumber) obj;
    return Objects.equals(this.getOrZero(), other.getOrZero());
  }

  @Override
  public String toString() {
    return this.getOrZero().toPlainString();
  }

}
