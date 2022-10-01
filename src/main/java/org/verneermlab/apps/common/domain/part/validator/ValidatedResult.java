package org.verneermlab.apps.common.domain.part.validator;

import java.util.Objects;
import java.util.Optional;
import org.verneermlab.apps.common.module.message.domain.Message;

/**
 * 検証結果.
 *
 * @author Yamashita.Takahiro
 */
public class ValidatedResult {

  private final Message message;
  private final Throwable throwable;

  private ValidatedResult(Message message, Throwable throwable) {
    this.message = message;
    this.throwable = throwable;
  }

  public static ValidatedResult of(Message message) {
    return new ValidatedResult(message, null);
  }

  public static ValidatedResult of(Message message, Throwable throwable) {
    return new ValidatedResult(message, throwable);
  }

  public Message getMessage() {
    return message;
  }

  public Optional<Throwable> getThrowable() {
    return Optional.ofNullable(throwable);
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 83 * hash + Objects.hashCode(this.message);
    hash = 83 * hash + Objects.hashCode(this.throwable.toString());
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
    final ValidatedResult other = (ValidatedResult) obj;
    if (!Objects.equals(this.message, other.message)) {
      return false;
    }
    return Objects.equals(this.throwable.toString(), other.throwable.toString());
  }

  @Override
  public String toString() {
    return message.toString() + "::" + throwable.toString();
  }

}
