package org.verneermlab.base.domain.type.text.behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.verneermlab.base.domain.type.text.NullableTextType;
import org.verneermlab.base.internal.domain.type.InstanceCreator;

/**
 * 文字列を結合.
 *
 * @author Yamashita.Takahiro
 * @param <T> 本インターフェースを実装した具象クラスの型
 */
public interface TextJoin<T extends NullableTextType<T>> extends NullableTextType<T>, InstanceCreator<T> {

  /**
   * 文字列を結合したインスタンスを返却します.
   *
   * @param delimiter 区切り文字
   * @param appendText 連結文字
   * @return 文字列を結合した文字インスタンス
   */
  @SuppressWarnings("unchecked")
  default T join(T delimiter, T... appendText) {
    List<String> strList = new ArrayList<>();
    strList.add(this.getNullableValue().orElse(""));

    List<String> appendStrList = Stream.of(appendText).map(e -> e.getNullableValue().orElse("")).collect(Collectors.toList());
    strList.addAll(appendStrList);

    var delimiterStr = Objects.isNull(delimiter) ? "" : delimiter.getNullableValue().orElse("");

    var str = String.join(delimiterStr, strList);
    return this.newInstanceFromThis(str);
  }

  /**
   * 文字列を結合したインスタンスを返却します.
   *
   * @param appendText 連結文字
   * @return 文字列を結合した文字インスタンス
   */
  @SuppressWarnings("unchecked")
  default T concat(T... appendText) {
    return this.join(null, appendText);
  }
}
