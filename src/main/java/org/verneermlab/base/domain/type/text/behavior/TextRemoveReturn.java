package org.verneermlab.base.domain.type.text.behavior;

import org.verneermlab.base.domain.type.text.NullableTextType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 文字列のキャリッジリターン除去.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface TextRemoveReturn<T> extends NullableTextType<T>, InstanceCreator<T> {

  /**
   * キャリッジリターンを除去したインスタンスを返却します.
   *
   * @return キャリッジリターンを除去した文字インスタンス
   */
  default T removeReturn() {
    if (this.getNullableValue().isEmpty()) {
      return this.newInstanceFromThis();
    }

    var replaced = this.getNullableValue().orElse("").replaceAll("\\r\\n|\\n", "");
    return this.newInstanceFromThis(replaced);
  }
}
