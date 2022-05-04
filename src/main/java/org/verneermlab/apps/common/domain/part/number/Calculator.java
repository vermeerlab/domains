package org.verneermlab.apps.common.domain.part.number;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 数値計算に使用する基本型.
 *
 * @param <T> Calculatorの実装クラス
 * @author Yamashita.Takahiro
 */
public interface Calculator<T extends Calculator<T>> {

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

    /**
     * 加算したインスタンスを返却します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SuppressWarnings("unchecked")
    default <U extends Calculator<U>> T plus(U... other) {
        if (other.length == 1) {
            var result = this.toBigDecimal().add(other[0].toBigDecimal());
            return newInstance(result);
        }
        BigDecimal result = Stream.of(other)
                .map(U::toBigDecimal)
                .filter(Objects::nonNull)
                .reduce(this.toBigDecimal(), BigDecimal::add);

        return newInstance(result);
    }

    /**
     * 減算したインスタンスを返却します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SuppressWarnings("unchecked")
    default <U extends Calculator<U>> T minus(U... other) {
        if (other.length == 1) {
            var result = this.toBigDecimal().subtract(other[0].toBigDecimal());
            return newInstance(result);
        }
        BigDecimal result = Stream.of(other)
                .map(U::toBigDecimal)
                .filter(Objects::nonNull)
                .reduce(this.toBigDecimal(), BigDecimal::subtract);

        return newInstance(result);
    }

    /**
     * 乗算したインスタンスを返却します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SuppressWarnings("unchecked")
    default <U extends Calculator<U>> T multiply(U... other) {
        if (other.length == 1) {
            var result = this.toBigDecimal().multiply(other[0].toBigDecimal());
            return newInstance(result);
        }
        BigDecimal result = Stream.of(other)
                .map(U::toBigDecimal)
                .filter(Objects::nonNull)
                .reduce(this.toBigDecimal(), BigDecimal::multiply);

        return newInstance(result);
    }

    /**
     * 除算したインスタンスを返却します.
     * 小数点以下の桁数（scale）と丸め（{@code RoundingMode}）はインスタンスのデフォルトを使用します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    default <U extends Calculator<U>> T divide(U other) {
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
     * @return 計算後のインスタンス
     */
    default <U extends Calculator<U>> T divide(U other, RoundingMode roundingMode) {
        return this.divide(other, this.getScale(), roundingMode);
    }

    /**
     * 除算したインスタンスを返却します.
     *
     * @param <U> 計算するインスタンスの型
     * @param other 計算するインスタンス
     * @param scale 小数点以下の有効桁数
     * @param roundingMode 丸めモード
     * @return 計算後のインスタンス
     */
    default <U extends Calculator<U>> T divide(U other, int scale, RoundingMode roundingMode) {
        var result = this.toBigDecimal().divide(other.toBigDecimal(), scale, roundingMode);
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
    default <U extends Calculator<U>> T plusAll(List<U> others) {
        BigDecimal result = others.stream()
                .map(U::toBigDecimal)
                .filter(Objects::nonNull)
                .reduce(this.toBigDecimal(), BigDecimal::add);

        // new T() 相当
        return newInstance(result);
    }

    /**
     * 動的にインスタンスを生成します.
     * <br>
     * リフレクションを使用していることで性能面に懸念がある場合などあれば、本メソッドの new T()
     * 相当の箇所を具象クラス側で実装してください.<br> {
     *
     * @see
     * <a href="https://nagise.hatenablog.jp/entry/20131121/1385046248">new
     * T()したいケースへの対処法</a>
     * @see
     * <a href="https://pppurple.hatenablog.com/entry/2016/08/08/004011">JavaのリフレクションAPIでコンストラクタ取得、インスタンス生成</a>
     *
     * @param value 数値
     * @return 生成したのインスタンス
     */
    @SuppressWarnings("unchecked")
    private T newInstance(BigDecimal value) {
        try {
            Class<?> clazz = this.getClass();
            return (T) clazz
                    .getDeclaredConstructor(BigDecimal.class, Integer.class, RoundingMode.class)
                    .newInstance(value, this.getScale(), this.getRoundingMode());
        } catch (ReflectiveOperationException ex) {
            throw new RuntimeException("Calculator could not get Other Class", ex);
        }
    }

}
