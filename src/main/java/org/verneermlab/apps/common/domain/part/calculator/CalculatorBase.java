package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;

/**
 * 計算処理の基底.
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface CalculatorBase<T extends CalculatorBase<T>> {

    /**
     * 計算に使用するプロパティ値を返却します.
     *
     * @return 計算に使用するプロパティ値
     */
    default BigDecimal getValue() {
        return this.toBigDecimal();
    }

    /**
     * プロパティ数値を返却します.
     *
     * @return プロパティで保持している値
     */
    BigDecimal toBigDecimal();

    /**
     * プロパティ数値を返却します.
     *
     * @return プロパティで保持している値（桁落ちした場合は、桁落ちした値）
     */
    default Long toLong() {
        return this.toBigDecimal().longValue();
    }

    /**
     * プロパティ数値を返却します.
     *
     * @return プロパティで保持している値（桁落ちした場合は、桁落ちした値）
     */
    default Integer toInt() {
        return this.toBigDecimal().intValue();
    }

}
