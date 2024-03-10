package org.verneermlab.apps.common.domain.type;

/**
 * 要素を含まない.
 *
 * @author Yamashita.Takahiro
 */
public interface IsEmpty {

  /**
   * 要素を保持を判定します.
   *
   * @return 保持しない場合はtrue
   */
  boolean isEmpty();
}
