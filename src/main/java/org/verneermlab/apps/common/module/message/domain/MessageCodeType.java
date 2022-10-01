package org.verneermlab.apps.common.module.message.domain;

/**
 * メッセージコードの型.
 * <p>
 * メッセージコードを管理するEnumクラスに指定することを想定しています.
 * </p>
 * Enumは実PJにて具体的な値を設定するため本インターフェースを通じて共通処理を実装します.
 *
 * @author Yamashita.Takahiro
 */
public interface MessageCodeType {

  /**
   * メッセージコードを返却します.
   *
   * @return 保持しているメッセージコード
   */
  String getCode();

}
