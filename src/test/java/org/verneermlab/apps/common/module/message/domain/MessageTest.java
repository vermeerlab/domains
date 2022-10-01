package org.verneermlab.apps.common.module.message.domain;

import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Yamashita.Takahiro
 */
public class MessageTest {

  public MessageTest() {
  }

  private enum MessageCode implements MessageCodeType {

    PARAM1("0"), PARAM2("1");

    private final String code;

    @Override
    public String getCode() {
      return this.code;
    }

    private MessageCode(String code) {
      this.code = code;
    }

  }

  @Test
  public void testGetMessageCode() {
    var actual = Message.of(MessageCode.PARAM2);
    assertEquals(MessageCode.PARAM2, actual.getMessageCode());
  }

  @Test
  public void testGetArgs() {
    var actual1 = Message.of(MessageCode.PARAM2);
    assertEquals(0, actual1.getArgs().length);

    var actual2 = Message.of(MessageCode.PARAM2, "Test");
    assertEquals("Test", actual2.getArgs()[0]);
  }

  @Test
  public void testToMessage() {
    var actual1 = Message.of(MessageCode.PARAM1);
    assertEquals("0", actual1.toMessage((messageCode) -> messageCode.getCode()));

    var actual2 = Message.of(MessageCode.PARAM2, "test");

    Map<String, String> messageTable = new HashMap<>();
    messageTable.put("1", "messageValue:{0}");

    // メッセージテーブルからメッセージも文言テンプレートを取得して変換する実装例
    assertEquals("messageValue:test", actual2.toMessage((messageCode) -> messageTable.get(messageCode.getCode())));
  }

  @Test
  public void testEquals() {
    assertEquals(Message.of(MessageCode.PARAM1), Message.of(MessageCode.PARAM1));
    assertNotEquals(Message.of(MessageCode.PARAM1), Message.of(MessageCode.PARAM2));
  }

  @Test
  public void testToString() {
    var actual1 = Message.of(MessageCode.PARAM2);
    assertEquals("1[]", actual1.toString());

    var actual2 = Message.of(MessageCode.PARAM2, "Test");
    assertEquals("1[Test]", actual2.toString());
  }

  @Test
  public void testCoverage() {
    var obj = Message.of(MessageCode.PARAM1);
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }

}
