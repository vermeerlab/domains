package org.verneermlab.apps.common.domain.part.validator;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import org.junit.jupiter.api.Test;
import org.verneermlab.apps.common.module.message.domain.Message;
import org.verneermlab.apps.common.module.message.domain.MessageCodeType;

/**
 *
 * @author Yamashita.Takahiro
 */
public class ValidatedResultsTest {

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

  public ValidatedResultsTest() {
  }

  @Test
  public void testInit() {
    ValidatedResults.init();
  }

  @Test
  public void testOf() {
    ValidatedResults.of(Collections.emptyList());
    ValidatedResults.of(ValidatedResult.of(Message.of(MessageCode.PARAM1)));
  }

  @Test
  public void testGetItems() {
    var actual1 = ValidatedResults.of(Collections.emptyList());
    assertEquals(0, actual1.getItems().size());

    assertThrowsExactly(UnsupportedOperationException.class,
            () -> actual1.getItems().add(ValidatedResult.of(Message.of(MessageCode.PARAM1)))
    );
  }

  @Test
  public void testIfPresentThrow_Supplier() throws Exception {
    var actual1 = ValidatedResults.of(Collections.emptyList());
    actual1.ifPresentThrow(() -> new RuntimeException());

    var actual2 = ValidatedResults.of(ValidatedResult.of(Message.of(MessageCode.PARAM1)));
    assertThrowsExactly(RuntimeException.class,
            () -> actual2.ifPresentThrow(() -> new RuntimeException())
    );
  }

  @Test
  public void testIfPresentThrow_Function() throws Exception {
    var actual1 = ValidatedResults.of(Collections.emptyList());
    actual1.ifPresentThrow((validatedResults) -> new RuntimeException(String.valueOf(validatedResults.size())));

    var actual2 = ValidatedResults.of(List.of(ValidatedResult.of(Message.of(MessageCode.PARAM1))));
    assertThrowsExactly(RuntimeException.class,
            () -> actual2.ifPresentThrow((validatedResults) -> new RuntimeException(String.valueOf(validatedResults.size())))
    );
  }

  @Test
  public void testMerge_ValidatedResults() {
    var actual1 = ValidatedResults.of(List.of(ValidatedResult.of(Message.of(MessageCode.PARAM1))));
    var actual2 = ValidatedResults.of(List.of(ValidatedResult.of(Message.of(MessageCode.PARAM2))));
    assertEquals(2, actual1.merge(actual2).getItems().size());

  }

  @Test
  public void testMerge_ValidatedResult() {
    var actual1 = ValidatedResults.of(List.of(ValidatedResult.of(Message.of(MessageCode.PARAM1))));
    var actual2 = ValidatedResult.of(Message.of(MessageCode.PARAM2));
    assertEquals(2, actual1.merge(actual2).getItems().size());
  }

  @Test
  public void testMerge_List() {
    var actual1 = ValidatedResults.of(List.of(ValidatedResult.of(Message.of(MessageCode.PARAM1))));
    var actual2 = ValidatedResult.of(Message.of(MessageCode.PARAM2));
    assertEquals(2, actual1.merge(List.of(actual2)).getItems().size());
  }

}
