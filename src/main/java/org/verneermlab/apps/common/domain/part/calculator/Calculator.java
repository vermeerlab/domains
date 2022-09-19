package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import org.verneermlab.apps.common.domain.type.IsEmpty;

/**
 * 計算処理の基底.
 *
 * @author Yamashita.Takahiro
 */
public interface Calculator extends IsEmpty {

  /**
   * 計算に使用するプロパティ値を返却します.
   *
   * @return 計算に使用するプロパティ値
   */
  default BigDecimal getCalcValue() {
    return this.toBigDecimal();
  }

  /**
   * プロパティ数値を返却します.
   *
   * @return プロパティで保持している数値（<code>null</code>の場合は zero）
   */
  BigDecimal toBigDecimal();

  /**
   * プロパティ数値を返却します.
   *
   * @return プロパティで保持している数値（桁落ちした場合は、桁落ちした値）（<code>null</code>の場合は zero）
   */
  default Long toLong() {
    return this.toBigDecimal().longValue();
  }

  /**
   * プロパティ数値を返却します.
   *
   * @return プロパティで保持している数値（桁落ちした場合は、桁落ちした値）（<code>null</code>の場合は zero）
   */
  default Integer toInt() {
    return this.toBigDecimal().intValue();
  }

  /**
   * 保持している値が<code>null</code>か判定します.
   *
   * @return nullの場合はtrue
   */
  @Override
  boolean isEmpty();

}
