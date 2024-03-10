package org.verneermlab.apps.common.domain.part.text;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NotEmptyTextTest {

  public NotEmptyTextTest() {
  }

  @Test
  public void testOf() {
    var result = NotEmptyText.of("ss").concat(NotEmptyText.of("tt"));
    assertEquals(NotEmptyText.of("sstt"), result);

    assertThrows(NullPointerException.class, () -> NotEmptyText.of(null));
  }

  @Test
  public void testGetNullableValue() {
    var result = NotEmptyText.of("ss").getNullableValue();
    assertTrue(result.isPresent());

  }

  @Test
  public void testGetValue() {
    var result = NotEmptyText.of("ss").getValue();
    assertEquals("ss", result);
  }

  @Test
  public void testHasText() {
    var text1 = NotEmptyText.of("aa");
    assertTrue(text1.hasText());

    var text2 = NotEmptyText.of("");
    assertFalse(text2.hasText());

  }

  @Test
  public void testIsEmpty() {
    var text1 = NotEmptyText.of("aa");
    assertFalse(text1.isEmpty());

    var text2 = NotEmptyText.of("");
    assertTrue(text2.isEmpty());
  }

  @Test
  public void testContains() {
    var text1 = NotEmptyText.of("aa");
    assertTrue(text1.contains(NotEmptyText.of("a")));
    assertFalse(text1.contains(NotEmptyText.of("b")));
    assertFalse(NotEmptyText.of("").contains(NotEmptyText.of("b")));
    assertFalse(text1.contains(NotEmptyText.of("")));
    assertFalse(NotEmptyText.of("").contains(NotEmptyText.of("")));
  }

  @Test
  public void testEquals() {
    var result = NotEmptyText.of("ss");
    assertEquals(NotEmptyText.of("ss"), result);
    assertNotEquals(NotEmptyText.of("ss2"), result);
  }

  @Test
  public void testCoverage() {
    var obj = NotEmptyText.of("1");
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
    obj.substring(-1);
  }
}
