package org.verneermlab.base.domain.type.text;

import java.util.Objects;
import org.verneermlab.base.domain.type.SinglePropertyObjectType;

/**
 * Nullを許容する文字列の基底型となるインターフェース.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface NullableTextType<T> extends SinglePropertyObjectType<String> {

  /**
   * 保持している文字列を返却します.
   *
   * @return <code>null</code>の場合は空文字
   */
  default String getOrDefault() {
    return this.getNullableValue().orElse("");
  }

  /**
   * 文字列が空文字またはNullであることを判定します.
   *
   * @return 空文字またはnullの場合はtrue
   */
  default boolean isEmpty() {
    return Objects.equals("", this.getNullableValue().orElse(""));
  }

  /**
   * 文字列の保持を判定します.
   *
   * @return 空文字または<code>null</code>以外の場合はtrue
   */
  default boolean hasText() {
    return !this.isEmpty();
  }

  /**
   * 対象の文字列が含まれるか判定をします.
   *
   * @param <U> 検査対象のクラスの型
   * @param other 検査文字列
   * @return 検査文字列が含まれる場合はtrue<br>自インスタンスまたは引数で指定したインスタンスが空文字またはnullの場合はfalse
   */
  default <U extends NullableTextType<T>> boolean contains(U other) {
    if (this.isEmpty() || other.isEmpty()) {
      return false;
    }

    var result = this.getNullableValue().get().contains(other.getNullableValue().get());
    return result;
  }

}
