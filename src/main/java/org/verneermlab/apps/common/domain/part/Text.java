package org.verneermlab.apps.common.domain.part;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.verneermlab.apps.common.domain.type.IsEmpty;

/**
 * 文字列.
 * <p>
 * フロントから任意（<code>null</code>）を受け取り、それを永続化する可能性があることを考慮しています.
 * </p>
 * あえてインスタンス変数を<code>Optional</code>にすることで <code>null</code>の永続化を許容しています.
 *
 * @author Yamashita.Takahiro
 */
public class Text implements IsEmpty {

  private Optional<String> value;

  private Text(Optional<String> value) {
    this.value = value;
  }

  /**
   * 値を保持しないインスタンスを生成します.
   *
   * @return 生成したインスタンス
   */
  public static Text init() {
    return Text.of(Optional.empty());
  }

  /**
   * インスタンスを生成します.
   * <p>
   * フロントや永続化時に<code>null</code>を設定されている場合に使用することを想定しています.
   * </p>
   *
   * @param valueOp 値
   * @return 生成したインスタンス
   */
  public static Text of(Optional<String> valueOp) {
    return new Text(valueOp);
  }

  /**
   * インスタンスを生成します.
   *
   * @param value 値
   * @return 生成したインスタンス
   */
  public static Text of(String value) {
    return new Text(Optional.ofNullable(value));
  }

  /**
   * 保持している値を返却します.
   *
   * @return 保持している値
   */
  public Optional<String> getValue() {
    return value;
  }

  /**
   * 保持している文字列を返却します.
   *
   * @return <code>null</code>の場合は空文字
   */
  public String get() {
    return value.orElse("");
  }

  /**
   * 前ゼロ埋めをした文字列を返却します.
   * <p>
   * 値を保持していない場合もゼロ埋めして桁を保証します.
   * </p>
   *
   * @param length ゼロ埋めを含めた文字列桁数
   * @return 更新したインスタンス
   */
  public Text padZero(Integer length) {
    var format = "%" + length + "s";
    var replaced = String.format(format, this.get()).replace(" ", "0");
    return Text.of(replaced);
  }

  /**
   * キャリッジリターンを除外したインスタンスを返却します.
   *
   * @return 更新したインスタンス
   */
  public Text removeReturn() {
    if (this.isEmpty()) {
      return Text.init();
    }

    var replaced = this.get().replaceAll("\\r\\n|\\n", "");
    return Text.of(replaced);
  }

  /**
   * 文字列を結合したインスタンスを返却します.
   *
   * @param appendText 連結文字
   * @return 更新したインスタンス
   */
  public Text concat(Text... appendText) {
    return this.join(Text.init(), appendText);
  }

  /**
   * 文字列を結合したインスタンスを返却します.
   *
   * @param delimiter 区切り文字
   * @param appendText 連結文字
   * @return 更新したインスタンス
   */
  public Text join(Text delimiter, Text... appendText) {
    var strList = new ArrayList<String>();
    strList.add(this.get());

    var appendStrList = Stream.of(appendText).map(Text::get).collect(Collectors.toList());
    strList.addAll(appendStrList);

    var str = String.join(delimiter.get(), strList);
    return Text.of(str);
  }

  /**
   * callbackを用いて保持している値を編集します.
   * <p>
   * プロパティが<code>null</code>の場合は空文字として処理をします.
   * </p>
   * 基本クラスに準備されてない文字列編集をするときに使用することを想定しています.
   *
   * @param callback コールバック関数
   * @return 更新したインスタンス
   */
  public Text apply(UnaryOperator<String> callback) {
    String updated = callback.apply(this.get());
    return Text.of(updated);
  }

  /**
   * 文字列を保持していない場合に指定の文字列を返却します.
   *
   * @param defaultStr デフォルト文字列
   * @return 文字列を存在しない場合は引数で指定した文字列、存在する場合は保持している文字列（<code>null</code>の場合は空文字）
   */
  public String orElse(String defaultStr) {
    var result = this.isEmpty() ? defaultStr : this.get();
    return result;
  }

  /**
   * 対象の文字列が含まるか判定をします.
   *
   * @param targetText 検査文字列
   * @return 検査文字列が含まれる場合はtrue
   */
  public boolean contains(Text targetText) {
    var result = this.get().contains(targetText.get());
    return result;
  }

  /**
   * 文字列の保持を判定します.
   *
   * @return 空文字または<code>null</code>の場合はtrue
   */
  public boolean hasText() {
    return Objects.equals("", this.get()) == false;
  }

  @Override
  public boolean isEmpty() {
    return this.value.isEmpty();
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
    return Objects.equals(this.value.orElse(null), other.value.orElse(null));
  }

  @Override
  public String toString() {
    return this.value.orElse(null);
  }
}
