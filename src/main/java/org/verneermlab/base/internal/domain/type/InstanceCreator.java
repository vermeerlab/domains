package org.verneermlab.base.internal.domain.type;

import java.lang.reflect.Constructor;

/**
 * 自インスタンスのコンストラクタを生成するインターフェース.
 *
 * @author Yamashita.Takahiro
 * @param <T> 生成対象となるクラスの型
 */
public interface InstanceCreator<T> {

  /**
   * 生成対象と同じクラスのデフォルトコンストラクタを使用したインスタンスを生成します.
   * <p>
   * このメソッドは具象クラスから使用しないでください. インターフェースのdefaultメソッドでのみ使用してください.
   * </p>
   *
   * @return 生成したインスタンス.
   */
  @SuppressWarnings("unchecked")
  default T newInstanceFromThis() {
    try {
      Constructor<?> constructor = this.getClass().getDeclaredConstructor();
      constructor.setAccessible(true);
      return (T) constructor.newInstance();
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 生成対象と同じクラスのデフォルトコンストラクタを使用したインスタンスを生成します.
   * <p>
   * このメソッドは具象クラスから使用しないでください. インターフェースのdefaultメソッドでのみ使用してください.
   * </p>
   *
   * @param originalValue コンストラクタの引数
   * @return 生成したインスタンス.
   */
  @SuppressWarnings("unchecked")
  default T newInstanceFromThis(Object originalValue) {
    try {
      Constructor<?> constructor = this.getClass().getDeclaredConstructor(originalValue.getClass());
      constructor.setAccessible(true);
      return (T) constructor.newInstance(originalValue);
    } catch (ReflectiveOperationException e) {
      throw new RuntimeException(e);
    }
  }
}
