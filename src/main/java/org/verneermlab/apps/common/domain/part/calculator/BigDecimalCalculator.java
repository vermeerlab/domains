/*
 *  Copyright(C) 2021 Sanyu Academy All rights reserved.
 */
package org.verneermlab.apps.common.domain.part.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 計算機
 * <br>
 * BigDecimalによる計算を行います.<br>
 * 数値の基本型（Integer, Long）も透過的に扱えるようにしています.
 *
 * @author Yamashita.Takahiro
 */
public class BigDecimalCalculator implements CalculatorBase {

    private final Integer DEFAULT_SCALE = 0;
    private final RoundingMode DEFAULT_ROUND_MODE = RoundingMode.DOWN;

    private final BigDecimal value;
    private final Integer scale;
    private final RoundingMode roundingMode;

    BigDecimalCalculator(BigDecimal value, Integer scale, RoundingMode roundingMode) {
        this.value = value;
        this.scale = Objects.isNull(scale)
                ? DEFAULT_SCALE
                : scale;

        this.roundingMode = Objects.isNull(roundingMode)
                ? DEFAULT_ROUND_MODE
                : roundingMode;
    }

    public static BigDecimalCalculatorBuilder builder(BigDecimal value) {
        return new BigDecimalCalculatorBuilder(value);
    }

    public static BigDecimalCalculatorBuilder builder(Integer value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return new BigDecimalCalculatorBuilder(bigDecimal);
    }

    public static BigDecimalCalculatorBuilder builder(Long value) {
        var bigDecimal = BigDecimal.valueOf(value);
        return new BigDecimalCalculatorBuilder(bigDecimal);
    }

    public static BigDecimalCalculatorBuilder builder(String value) {
        var bigDecimal = new BigDecimal(value);
        return new BigDecimalCalculatorBuilder(bigDecimal);
    }

    /**
     * 加算したインスタンスを返却します.
     *
     * @param <T> 計算対象の型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SafeVarargs
    public final <T extends CalculatorBase> BigDecimalCalculator plus(T... other) {
        if (other.length == 1) {
            var result = this.getCalcValue().add(other[0].getCalcValue());
            return BigDecimalCalculator.builder(result).build();
        }
        BigDecimal result = Stream.of(other)
                .map(T::getCalcValue)
                .filter(Objects::nonNull)
                .reduce(this.getCalcValue(), BigDecimal::add);

        return BigDecimalCalculator.builder(result).build();
    }

    /**
     * 複数要素を加算したインスタンスを返却します.
     *
     * 加算は複数情報を一括で処理したいケースがあるため、デフォルトでも準備しています.
     *
     * @param <T> 計算対象の型
     * @param others 計算するインスタンスリスト
     * @throws RuntimeException 引数インスタンスからクラス情報が取得出来ない場合
     * @return 計算後のインスタンス
     */
    public final <T extends CalculatorBase> BigDecimalCalculator plusAll(List<T> others) {
        BigDecimal result = others.stream()
                .map(T::getCalcValue)
                .filter(Objects::nonNull)
                .reduce(this.getCalcValue(), BigDecimal::add);

        return BigDecimalCalculator.builder(result).build();
    }

    /**
     * 加算したインスタンスを返却します.
     *
     * @param <T> 計算対象の型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    @SafeVarargs
    public final <T extends CalculatorBase> BigDecimalCalculator minus(T... other) {
        if (other.length == 1) {
            var result = this.getCalcValue().subtract(other[0].getCalcValue());
            return BigDecimalCalculator.builder(result).build();
        }
        BigDecimal result = Stream.of(other)
                .map(T::getCalcValue)
                .filter(Objects::nonNull)
                .reduce(this.getCalcValue(), BigDecimal::subtract);

        return BigDecimalCalculator.builder(result).build();
    }

    /**
     * 乗算したインスタンスを返却します.
     *
     * @param <T> 計算対象の型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス
     */
    public final <T extends CalculatorBase> BigDecimalCalculator multiply(T other) {
        var result = this.getCalcValue().multiply(other.getCalcValue());
        return BigDecimalCalculator.builder(result).build();
    }

    /**
     * 除算したインスタンスを返却します.
     * 小数点以下の桁数（scale）と丸め（{@code RoundingMode}）はインスタンスのデフォルトを使用します.<br>
     *
     * @param <T> 計算対象の型
     * @param other 計算するインスタンス
     * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
     */
    public final <T extends CalculatorBase> BigDecimalCalculator divide(T other) {
        return this.divide(other, this.getScale(), this.getRoundingMode());
    }

    /**
     * 除算したインスタンスを返却します.
     *
     * 小数点以下の桁数（scale）はインスタンスのデフォルトを使用します.
     *
     * @param <T> 計算対象の型
     * @param other 計算するインスタンス
     * @param roundingMode 丸めモード
     * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
     */
    public final <T extends CalculatorBase> BigDecimalCalculator divide(T other, RoundingMode roundingMode) {
        return this.divide(other, this.getScale(), roundingMode);
    }

    /**
     * 除算したインスタンスを返却します.
     *
     * @param <T> 計算対象の型
     * @param other 計算するインスタンス
     * @param scale 小数点以下の有効桁数
     * @param roundingMode 丸めモード
     * @return 計算後のインスタンス.ゼロ除算の場合の戻り値はゼロを返却します（実行時例外をスローしません）
     */
    public final <T extends CalculatorBase> BigDecimalCalculator divide(T other, int scale, RoundingMode roundingMode) {
        if (other.getCalcValue().compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimalCalculator.builder(BigDecimal.ZERO)
                    .scale(scale)
                    .roundingMode(roundingMode)
                    .build();
        }

        var result = this.getCalcValue().divide(other.getCalcValue(), scale, roundingMode);
        return BigDecimalCalculator.builder(result)
                .scale(scale)
                .roundingMode(roundingMode)
                .build();
    }

    /**
     * インスタンスが保持しているscaleを返却します.
     *
     * @return プロパティで保持している値
     */
    public Integer getScale() {
        return this.scale;
    }

    /**
     * インスタンスが保持しているRoundingModeを返却します.
     *
     * @return プロパティで保持している値
     */
    public RoundingMode getRoundingMode() {
        return this.roundingMode;
    }

    @Override
    public BigDecimal toBigDecimal() {
        return this.value;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.value);
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
        final BigDecimalCalculator other = (BigDecimalCalculator) obj;
        return this.value.compareTo(other.value) == 0;
    }

}
