package org.verneermlab.base.domain.type;

/**
 * Nullを許容しない基底型となるインターフェース.
 * <p>
 * プロパティにNullの保持を許容しません.
 * </p>
 * インターフェースの意図を満たすために具象クラスのコンストラクタで必ずNullを許容しないガード節を実装してください.
 *
 * @author Yamashita.Takahiro
 * @param <T> 基底型を使用するクラスの型
 */
public interface NotEmptyType<T> {

  /**
   * 要素のプロパティの値を返却します.
   *
   * @return プロパティの値
   */
  T getValue();

}
