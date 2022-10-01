package org.verneermlab.apps.common.domain.part.validator;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 検証結果（集約）.
 *
 * @author Yamashita.Takahiro
 */
public class ValidatedResults {

  private final List<ValidatedResult> items;

  private ValidatedResults(List<ValidatedResult> items) {
    this.items = items;
  }

  public static ValidatedResults init() {
    return new ValidatedResults(Collections.emptyList());
  }

  /**
   * インスタンスを生成します.
   *
   * @param item 検証
   * @return 生成したインスタンス
   */
  public static ValidatedResults of(ValidatedResult... item) {
    return new ValidatedResults(List.of(item));
  }

  /**
   * インスタンスを生成します.
   *
   * @param items 検証リスト
   * @return 生成したインスタンス
   */
  public static ValidatedResults of(List<ValidatedResult> items) {
    return new ValidatedResults(items);
  }

  /**
   * 保持している情報（リスト）を返却します.
   *
   * @return 保持情報リスト（不変）
   */
  public List<ValidatedResult> getItems() {
    return Collections.unmodifiableList(this.items);
  }

  /**
   * 検証結果を保持している場合は実行時例外をスローします.
   *
   * @param <X> 実行時例外の型
   * @param exceptionSupplier 実行時例外をスローする関数
   * @throws X スローする実行時例外
   */
  public <X extends Throwable> void ifPresentThrow(Supplier<? extends X> exceptionSupplier) throws X {
    if (this.items.isEmpty()) {
      return;
    }
    throw exceptionSupplier.get();
  }

  /**
   * 検証結果を保持している場合は実行時例外をスローします.
   *
   * @param <X> 実行時例外の型
   * @param exceptionFunction 実行時例外をスローする関数
   * @throws X スローする実行時例外
   */
  public <X extends Throwable> void ifPresentThrow(Function<List<ValidatedResult>, ? extends X> exceptionFunction) throws X {
    if (this.items.isEmpty()) {
      return;
    }
    throw exceptionFunction.apply(this.items);
  }

  /**
   * 検証結果（集約）をマージします.
   *
   * @param other 結合する検証結果（集約）
   * @return 結合した検証結果（集約）
   */
  public ValidatedResults merge(ValidatedResults other) {
    var merged = Stream
            .concat(this.items.stream(), other.getItems().stream())
            .collect(Collectors.toList());
    return ValidatedResults.of(merged);
  }

  /**
   * 検証結果（集約）をマージします.
   *
   * @param otherItem 結合する検証結果
   * @return 結合した検証結果（集約）
   */
  public ValidatedResults merge(ValidatedResult otherItem) {
    return this.merge(List.of(otherItem));
  }

  /**
   * 検証結果（集約）をマージします.
   *
   * @param otherItems 結合する検証結果リスト
   * @return 結合した検証結果（集約）
   */
  public ValidatedResults merge(List<ValidatedResult> otherItems) {
    return this.merge(ValidatedResults.of(otherItems));
  }
}
