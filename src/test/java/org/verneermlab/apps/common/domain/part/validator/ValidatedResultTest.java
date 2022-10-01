package org.verneermlab.apps.common.domain.part.validator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.verneermlab.apps.common.module.message.domain.Message;
import org.verneermlab.apps.common.module.message.domain.MessageCodeType;

/**
 *
 * @author Yamashita.Takahiro
 */
public class ValidatedResultTest {

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

  public ValidatedResultTest() {
  }

  @Test
  public void testOf_Message() {
    var actual = ValidatedResult.of(Message.of(MessageCode.PARAM1));
    assertEquals(Message.of(MessageCode.PARAM1), actual.getMessage());

    var actual2 = ValidatedResult.of(Message.of(MessageCode.PARAM1, "1"));
    assertEquals(Message.of(MessageCode.PARAM1, "1"), actual2.getMessage());
    assertNotEquals(Message.of(MessageCode.PARAM1), actual2.getMessage());
  }

  @Test
  public void testOf_Message_Throwable() {
    var actual1 = ValidatedResult.of(Message.of(MessageCode.PARAM1), new RuntimeException());
    var actual2 = ValidatedResult.of(Message.of(MessageCode.PARAM1), new RuntimeException());
    assertEquals(actual1, actual2);
  }

  @Test
  public void testGetMessage() {
    var actual1 = ValidatedResult.of(Message.of(MessageCode.PARAM1), new RuntimeException());
    assertEquals(Message.of(MessageCode.PARAM1), actual1.getMessage());

    var actual2 = ValidatedResult.of(Message.of(MessageCode.PARAM2), new RuntimeException());
    assertFalse(actual1.equals(actual2));

  }

  @Test
  public void testGetThrowable() {
    var actual1 = ValidatedResult.of(Message.of(MessageCode.PARAM1), new RuntimeException());
    assertEquals(new RuntimeException().toString(), actual1.getThrowable().get().toString());
  }

  @Test
  public void testToString() {
    var actual1 = ValidatedResult.of(Message.of(MessageCode.PARAM1), new RuntimeException());
    assertEquals("0[]::java.lang.RuntimeException", actual1.toString());
  }

  @Test
  public void testCoverage() {
    var obj = ValidatedResult.of(Message.of(MessageCode.PARAM1), new RuntimeException());
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }
}
