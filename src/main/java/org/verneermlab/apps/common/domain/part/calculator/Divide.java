/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Yamashita.Takahiro
 */
public interface Divide<T extends Divide<T>> extends CalculatorBase<T> {

    /**
     * インスタンスが保持しているscaleを返却します.
     *
     * @return プロパティで保持している値
     */
    Integer getScale();

    /**
     * インスタンスが保持しているRoundingModeを返却します.
     *
     * @return プロパティで保持している値
     */
    RoundingMode getRoundingMode();

    /**
     * 除算したインスタンスを返却します.
     * 小数点以下の桁数（scale）と丸め（{@code RoundingMode}）はインスタンスのデフォルトを使用します.<br>
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
     */
    default <U extends Divide<U>> T divide(U other) {
        return this.divide(other, this.getScale(), this.getRoundingMode());
    }

    /**
     * 除算したインスタンスを返却します.
     *
     * 小数点以下の桁数（scale）はインスタンスのデフォルトを使用します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @param roundingMode 丸めモード
     * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
     */
    default <U extends Divide<U>> T divide(U other, RoundingMode roundingMode) {
        return this.divide(other, this.getScale(), roundingMode);
    }

    /**
     * 除算したインスタンスを返却します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @param scale 小数点以下の有効桁数
     * @param roundingMode 丸めモード
     * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
     */
    default <U extends Divide<U>> T divide(U other, int scale, RoundingMode roundingMode) {
        if (other.getValue().compareTo(BigDecimal.ZERO) == 0) {
            return newInstance(BigDecimal.ZERO, this.getScale(), this.getRoundingMode());
        }

        var result = this.getValue().divide(other.getValue(), scale, roundingMode);
        return newInstance(result, this.getScale(), this.getRoundingMode());
    }

    /**
     * インスタンスを生成します.
     *
     * @param value 保持させる値
     * @return 生成したインスタンス
     */
    @SuppressWarnings("unchecked")
    private T newInstance(BigDecimal value, int scale, RoundingMode roundingMode) {
        try {
            Class<?> clazz = this.getClass();
            return (T) clazz
                    .getDeclaredConstructor(BigDecimal.class, Integer.class, RoundingMode.class)
                    .newInstance(value, scale, roundingMode);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException("Calculator could not get Other Class", ex);
        }
    }
}
