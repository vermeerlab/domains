package org.verneermlab.apps.common.domain.part.calculator;

import java.util.List;

/**
 * 加算計算
 *
 * @param <T> 実装クラスの型
 * @author Yamashita.Takahiro
 */
public interface Plus<T> extends CalculatorBase {

    /**
     * 加算したインスタンスを返却します.
     *
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SuppressWarnings("unchecked")
    public T plus(T... other);

    /**
     * 複数要素を加算します.
     * <br>
     * 加算は複数情報を一括で処理したいケースがあるため、デフォルトでも準備しています.
     *
     * @param others 計算するインスタンスリスト
     * @throws RuntimeException 引数インスタンスからクラス情報が取得出来ない場合
     * @return 計算後のインスタンス
     */
    public T plusAll(List<T> others);

}
