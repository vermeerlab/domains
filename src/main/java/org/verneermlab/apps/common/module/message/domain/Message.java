package org.verneermlab.apps.common.module.message.domain;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;

/**
 * メッセージ.
 *
 * @author Yamashita.Takahiro
 */
public class Message {

  private final MessageCodeType messageCode;
  private final Object[] args;

  private Message(MessageCodeType messageCode, Object[] args) {
    this.messageCode = messageCode;
    this.args = args;
  }

  public static Message of(MessageCodeType messageCode) {
    return new Message(messageCode, new Object[0]);
  }

  public static Message of(MessageCodeType messageCode, Object... args) {
    return new Message(messageCode, args);
  }

  public MessageCodeType getMessageCode() {
    return messageCode;
  }

  public Object[] getArgs() {
    return args;
  }

  /**
   * メッセージ文字列へ変換します.
   *
   * @param funcMessagePattern 保持するメッセージコードを使用してメッセージ文字列を取得する関数
   * @return メッセージ文字列
   */
  public String toMessage(Function<MessageCodeType, String> funcMessagePattern) {
    var pattern = funcMessagePattern.apply(messageCode);
    var result = MessageFormat.format(pattern, args);
    return result;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 71 * hash + Objects.hashCode(this.messageCode.getCode());
    hash = 71 * hash + Arrays.deepHashCode(this.args);
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
    final Message other = (Message) obj;
    if (!Objects.equals(this.messageCode, other.messageCode)) {
      return false;
    }
    return Arrays.deepEquals(this.args, other.args);
  }

  @Override
  public String toString() {
    return messageCode.getCode() + Arrays.toString(args);
  }

}
