package org.verneermlab.base.domain.type.text.behavior;

import java.util.function.UnaryOperator;
import org.verneermlab.base.domain.type.text.NullableTextType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 保持している文字列を編集.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface TextUnaryOperator<T> extends NullableTextType<T>, InstanceCreator<T> {

  /**
   * callbackを用いて保持している値を編集して新しいインスタンスを生成します.
   *
   * @param callback コールバック関数
   * @return 編集後の新しいインスタンス.
   */
  default T apply(UnaryOperator<String> callback) {
    String updated = callback.apply(this.getNullableValue().orElse(null));
    return newInstanceFromThis(updated);
  }
}
