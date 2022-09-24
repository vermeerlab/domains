package org.verneermlab.base.utils.enumclass;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

/**
 * Enumのコード値からEnumクラスから逆引きをします.
 * <p>
 * Enumクラスにstaticメソッドを追加して使用することを想定しています.
 * </p>
 * 記述例.<br>
 * Enumクラス側
 * <p>
 * {@code
 * static final EnumReverseLookup<TestEnum, String> byCd = new EnumReverseLookup<>(TestEnum.class, TestEnum::getCd);
 * }
 * </p>
 * Enumクラスを逆引きする側
 * <p>
 * {@code
 * Optional<TestEnum> enumCls = TestEnum.byCd.lookup("1");
 * }
 * </p>
 *
 *
 * @author Yamashita.Takahiro
 * @param <E> 逆引き対象のEnumクラス
 * @param <ATT> 逆引きしたいEnum属性値
 */
public class EnumReverseLookup<E extends Enum<E>, ATT> {

  private final Class<E> enumClass;
  private final Function<E, ATT> getter;

  /**
   * インスタンスを生成します.
   *
   * @param enumClass 逆引き対象のEnumクラス
   * @param getter 逆引きしたいEnum属性値を取得するGetter
   */
  public EnumReverseLookup(Class<E> enumClass, Function<E, ATT> getter) {
    this.enumClass = enumClass;
    this.getter = getter;
  }

  /**
   * 逆引きしたEnumインスタンスを返却します.
   *
   * @param attr Enum属性値
   * @return 属性値に合致するEnumインスタンス（複数対象があった場合の取得する順序は保証しません）
   */
  public Optional<E> lookup(ATT attr) {
    var lookuped = Arrays.stream(this.enumClass.getEnumConstants())
            .filter(e -> this.getter.apply(e).equals(attr)).findAny();
    return lookuped;
  }

}
