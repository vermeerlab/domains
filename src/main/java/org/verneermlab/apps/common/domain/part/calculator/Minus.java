/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part.calculator;

/**
 * 減算計算
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Minus<T> extends CalculatorBase {

    /**
     * 減算したインスタンスを返却します.
     *
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SuppressWarnings("unchecked")
    public T minus(T... other);

}
