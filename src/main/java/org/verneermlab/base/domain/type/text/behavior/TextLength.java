package org.verneermlab.base.domain.type.text.behavior;

import java.text.BreakIterator;
import org.verneermlab.base.domain.type.text.NullableTextType;

/**
 * 文字列の文字数.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface TextLength<T> extends NullableTextType<T> {

  /**
   * 保持している文字数を返却します.
   * <p>
   * サロゲートペアを考慮した文字数を返却します.
   * </p>
   *
   * @return 保持している文字数を返却します.<code>null</code>を保持している場合は 0 を返却します.
   */
  default int length() {
    if (this.getNullableValue().isEmpty()) {
      return 0;
    }

    BreakIterator iterator = BreakIterator.getCharacterInstance();
    iterator.setText(this.getNullableValue().orElse(""));

    int current = iterator.next();
    int count = 0;
    while (current != BreakIterator.DONE) {
      count++;
      current = iterator.next();
    }
    return count;
  }
}
