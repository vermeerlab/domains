/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 減算計算
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Minus<T extends Minus<T>> extends CalculatorBase<T> {

    /**
     * 減算したインスタンスを返却します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SuppressWarnings("unchecked")
    default <U extends Minus<U>> T minus(U... other) {
        if (other.length == 1) {
            var result = this.getValue().subtract(other[0].getValue());
            return newInstance(result);
        }
        BigDecimal result = Stream.of(other)
                .map(U::getValue)
                .filter(Objects::nonNull)
                .reduce(this.getValue(), BigDecimal::subtract);

        return newInstance(result);
    }

    /**
     * インスタンスを生成します.
     *
     * @param value 保持させる値
     * @return 生成したインスタンス
     */
    @SuppressWarnings("unchecked")
    private T newInstance(BigDecimal value) {
        try {
            Class<?> clazz = this.getClass();
            return (T) clazz
                    .getDeclaredConstructor(BigDecimal.class)
                    .newInstance(value);
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException("Calculator could not get Other Class", ex);
        }
    }

}
