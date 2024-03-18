package org.verneermlab.apps.common.domain.part.text;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.text.behavior.TextJoin;
import org.verneermlab.base.domain.type.text.behavior.TextRemoveReturn;
import org.verneermlab.base.domain.type.text.behavior.TextSubstring;
import org.verneermlab.base.domain.type.text.behavior.TextUnaryOperator;
import org.verneermlab.base.domain.type.text.behavior.TextZeroPadding;

/**
 * Nullを許容する文字列（汎用）.
 * <p>
 * フロントから任意（<code>null</code>）を受け取り、それを永続化する可能性があることを考慮しています.
 * </p>
 *
 * @author Yamashita.Takahiro
 */
public final class Text implements TextZeroPadding<Text>, TextJoin<Text>,
        TextRemoveReturn<Text>, TextSubstring<Text>, TextUnaryOperator<Text>, Serializable {

  private static final long serialVersionUID = 1L;

  private final String value;

  private Text() {
    this.value = null;
  }

  private Text(String value) {
    this.value = value;
  }

  /**
   * 値を保持しないインスタンスを生成します.
   *
   * @return 生成したインスタンス
   */
  public static Text createNoValue() {
    return new Text();
  }

  /**
   * インスタンスを生成します.
   * <p>
   * フロントや永続化時に<code>null</code>を設定されている場合に使用することを想定しています.
   * </p>
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Text of(String value) {
    return new Text(value);
  }

  @Override
  public Optional<String> getNullableValue() {
    return Optional.ofNullable(value);
  }

  @Override
  public int hashCode() {
    int hash = 5;
    hash = 89 * hash + Objects.hashCode(this.value);
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
    final Text other = (Text) obj;
    return Objects.equals(this.getNullableValue().orElse(null), other.getNullableValue().orElse(null));
  }

  @Override
  public String toString() {
    return Objects.toString(value);
  }
}
