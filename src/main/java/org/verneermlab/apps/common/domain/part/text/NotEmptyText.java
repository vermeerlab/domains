package org.verneermlab.apps.common.domain.part.text;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;
import org.verneermlab.base.domain.type.NotEmptyType;
import org.verneermlab.base.domain.type.text.behavior.TextJoin;
import org.verneermlab.base.domain.type.text.behavior.TextRemoveReturn;
import org.verneermlab.base.domain.type.text.behavior.TextSubstring;
import org.verneermlab.base.domain.type.text.behavior.TextUnaryOperator;
import org.verneermlab.base.domain.type.text.behavior.TextZeroPadding;

/**
 * Nullを許容しない文字列（汎用）.
 *
 * @author Yamashita.Takahiro
 */
public final class NotEmptyText implements NotEmptyType<String>,
        TextJoin<NotEmptyText>, TextZeroPadding<NotEmptyText>, TextRemoveReturn<NotEmptyText>,
        TextSubstring<NotEmptyText>, TextUnaryOperator<NotEmptyText>, Serializable {

  private static final long serialVersionUID = 1L;

  private final String value;

  private NotEmptyText() {
    // 文字列結合の元インスタンスとなるため空文字を初期値として設定します.
    this.value = "";
  }

  private NotEmptyText(String value) {
    // Nullの保持を許容しません.
    if (Objects.isNull(value)) {
      throw new NullPointerException();
    }

    this.value = value;
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static NotEmptyText of(String value) {
    return new NotEmptyText(value);
  }

  /**
   * Nullを許容していないクラスのため、本メソッドを使用する意味はありません.
   *
   * @return プロパティの値
   */
  @Override
  public Optional<String> getNullableValue() {
    return Optional.of(value);
  }

  @Override
  public String getValue() {
    return this.value;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 92 * hash + Objects.hashCode(this.value);
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
    final NotEmptyText other = (NotEmptyText) obj;
    return Objects.equals(this.getNullableValue().orElse(null), other.getNullableValue().orElse(null));
  }

  @Override
  public String toString() {
    return Objects.toString(value);
  }
}
