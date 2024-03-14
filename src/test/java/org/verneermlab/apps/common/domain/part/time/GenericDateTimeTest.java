package org.verneermlab.apps.common.domain.part.time;

import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GenericDateTimeTest {

  @Test
  public void testOf() {
    var actual = GenericDateTime.of(LocalDateTime.of(2024, 1, 1, 1, 1, 1, 1)).toString();
    Assertions.assertEquals("2024-01-01T01:01:01.000000001", actual);
  }

  @Test
  public void testFromYyyyMmDdSeparator() {
    var actual1 = GenericDateTime.fromYyyyMmDdSeparator("2024/01/01").toString();
    Assertions.assertEquals("2024-01-01T00:00", actual1);

    var actual2 = GenericDateTime.fromYyyyMmDdSeparator("").toString();
    Assertions.assertEquals("null", actual2);
  }

  @Test
  public void testGetNullableValue() {
    var actual = GenericDateTime.of(LocalDateTime.of(2024, 1, 1, 1, 1, 1, 1)).getNullableValue();
    Assertions.assertEquals(Optional.of(LocalDateTime.of(2024, 1, 1, 1, 1, 1, 1)), actual);
  }

  @Test
  public void testToYyyyMmDdWithSeparator() {
    var actual = GenericDateTime.of(LocalDateTime.of(2024, 1, 1, 1, 1, 1, 1)).toYyyyMmDdWithSeparator();
    Assertions.assertEquals(Optional.of("2024/01/01"), actual);
  }

  @Test
  public void testToYyyyMmDdHhMmSs() {
    var actual = GenericDateTime.of(LocalDateTime.of(2024, 1, 1, 1, 2, 3, 4)).toYyyyMmDdHhMmSs();
    Assertions.assertEquals(Optional.of("2024/01/01 01:02:03"), actual);
  }

  @Test
  public void testCoverage() {
    var obj = GenericDateTime.of(LocalDateTime.of(2024, 1, 1, 1, 1, 1, 1));
    obj.hashCode();
    Assertions.assertEquals(obj, obj);
    Assertions.assertEquals(GenericDateTime.of(LocalDateTime.of(2024, 1, 1, 1, 1, 1, 1)), obj);
    Assertions.assertNotEquals(obj, null);
    Assertions.assertNotEquals(obj, "");
    Assertions.assertEquals("null", GenericDateTime.of(null).toString());
  }

}
