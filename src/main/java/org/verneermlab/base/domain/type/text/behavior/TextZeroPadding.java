package org.verneermlab.base.domain.type.text.behavior;

import org.verneermlab.base.domain.type.text.NullableTextType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 文字列の前ゼロ埋め.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface TextZeroPadding<T> extends NullableTextType<T>, InstanceCreator<T> {

  /**
   * 前ゼロ埋めをした文字列を返却します.
   * <p>
   * 値を保持していない場合もゼロ埋めして桁を保証します.
   * </p>
   *
   * @param length ゼロ埋めを含めた文字列桁数
   * @return 文字列を前ゼロ埋めした文字インスタンス
   */
  default T zeroPadding(Integer length) {
    var format = "%" + length + "s";
    var replaced = String.format(format, this.getNullableValue().orElse("")).replace(" ", "0");
    return this.newInstanceFromThis(replaced);
  }
}
