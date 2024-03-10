package org.verneermlab.base.domain.type.text.behavior;

import java.text.BreakIterator;
import java.util.ArrayList;
import org.verneermlab.base.domain.type.text.NullableTextType;

/**
 * 文字列の部分文字列である文字列を取得.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface TextSubstring<T extends NullableTextType<T>> extends TextJoin<T>, TextLength<T> {

  /**
   * 文字列の部分文字列である文字のインスタンスを返却します.
   *
   * @param beginIndex 開始位置
   * @return 取得したインスタンス. 開始位置がマイナスの場合は空インスタンス. 開始位置が文字列長を超えていた場合は同じ値のインスタンス.
   */
  default T substring(int beginIndex) {
    return this.substring(beginIndex, Integer.MAX_VALUE);
  }

  /**
   * 文字列の部分文字列である文字のインスタンスを返却します.
   *
   * @param beginIndex 開始位置
   * @param endIndex 終了位置
   * @return 取得したインスタンス. 開始位置がマイナスの場合または終了位置が開始位置よりも起きい場合は空インスタンス. 開始位置が文字列長を超えていた場合は同じ値のインスタンス.
   */
  default T substring(int beginIndex, int endIndex) {
    if (beginIndex < 0) {
      return this.newInstanceFromThis();
    }

    int length = this.length();
    int endIndexPos = length < endIndex ? length : endIndex;

    if (endIndexPos < beginIndex) {
      return this.newInstanceFromThis();
    }

    if (beginIndex == 0 && length <= endIndex) {
      return this.newInstanceFromThis(this.getNullableValue().get());
    }

    BreakIterator iterator = BreakIterator.getCharacterInstance();
    iterator.setText(this.getNullableValue().orElse(""));

    var strArray = new ArrayList<String>();
    for (int start = iterator.first(), end = iterator.next();
            end != BreakIterator.DONE; start = end, end = iterator.next()) {
      String str = this.getNullableValue().orElse("").substring(start, end);
      strArray.add(str);
    }

    var subList = strArray.subList(beginIndex, endIndexPos);
    var subStr = String.join("", subList);
    return this.newInstanceFromThis(subStr);
  }

}
