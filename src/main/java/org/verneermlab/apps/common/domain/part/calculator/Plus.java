/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 加算計算
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Plus<T extends Plus<T>> extends CalculatorBase<T> {

    /**
     * 加算したインスタンスを返却します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SuppressWarnings("unchecked")
    default <U extends Plus<U>> T plus(U... other) {
        if (other.length == 1) {
            var result = this.getValue().add(other[0].getValue());
            return newInstance(result);
        }
        BigDecimal result = Stream.of(other)
                .map(U::getValue)
                .filter(Objects::nonNull)
                .reduce(this.getValue(), BigDecimal::add);

        return newInstance(result);
    }

    /**
     * 複数要素を加算します.
     * <br>
     * 加算は複数情報を一括で処理したいケースがあるため、デフォルトでも準備しています.
     *
     * @param <U> 計算するインスタンスの型
     * @param others 計算するインスタンスリスト
     * @throws RuntimeException 引数インスタンスからクラス情報が取得出来ない場合
     * @return 計算後のインスタンス
     */
    default <U extends Plus<U>> T plusAll(List<U> others) {
        BigDecimal result = others.stream()
                .map(U::getValue)
                .filter(Objects::nonNull)
                .reduce(this.getValue(), BigDecimal::add);

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
