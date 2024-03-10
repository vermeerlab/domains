package org.verneermlab.base.domain.type;

import java.util.Optional;

/**
 * 単一プロパティを扱う基底型となるインターフェース.
 * <p>
 * Objectのnull判定を各所でnull判定を実装しないように安全に扱えるようにします.
 * </p>
 * 例えば、WebAPIのリクエストボディに任意項目としてnullもしくは未設定で取得することがあります.<br>
 * そういった場合にnull判定を各所で実装しなくても良いように、WebAPIやDBへの登録時まで遅延できるように内包させて扱うためのインターフェースです. <br>
 *
 * @author Yamashita.Takahiro
 * @param <T> 基底型を使用するクラスの型
 */
public interface SinglePropertyObjectType<T> {

  /**
   * 要素のプロパティの値を返却します.
   * <p>
   * 使用側で直接使用するのではなく拡張先のクラスで使用することを想定しています.
   * </p>
   *
   * @return プロパティの値
   */
  Optional<T> getNullableValue();

}
