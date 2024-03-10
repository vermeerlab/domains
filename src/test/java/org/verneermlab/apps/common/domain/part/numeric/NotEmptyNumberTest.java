package org.verneermlab.apps.common.domain.part.numeric;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NotEmptyNumberTest {

  @Test
  public void testOf() {
    var result = NotEmptyNumber.of(123);
    assertEquals(123, result.getOrZero().intValue());

    assertThrows(NullPointerException.class, () -> NotEmptyNumber.of(null));

  }

  @Test
  public void testGetNullableValue() {
    var result = NotEmptyNumber.of(123);
    assertEquals(123, result.getNullableValue().get().intValue());

  }

  @Test
  public void testGetValue() {
    var result = NotEmptyNumber.of(123);
    assertEquals(123, result.getValue().intValue());
  }

  @Test
  public void testCoverage() {
    var obj = NotEmptyNumber.of(1);
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, 2);
    assertEquals(obj, NotEmptyNumber.of(1));
    assertEquals("1", NotEmptyNumber.of(1).toString());
  }

}
