package org.verneermlab.apps.common.domain.part.time;

import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class GenericDateTest {

  @Test
  public void testOf() {
    var actual = GenericDate.of(LocalDate.of(2024, 1, 1)).toString();
    Assertions.assertEquals("2024-01-01", actual);
  }

  @Test
  public void testFromYyyyMmDd() {
    var actual1 = GenericDate.fromYyyyMmDd("20240101").toString();
    Assertions.assertEquals("2024-01-01", actual1);

    var actual2 = GenericDate.fromYyyyMmDd("").toString();
    Assertions.assertEquals("null", actual2);
  }

  @Test
  public void testGetNullableValue() {
    var actual = GenericDate.fromYyyyMmDd("20240101").getNullableValue();
    Assertions.assertEquals(Optional.of(LocalDate.of(2024, 1, 1)), actual);
  }

  @Test
  public void testToYyyyMmDd() {
    var actual = GenericDate.fromYyyyMmDd("20240101").toYyyyMmDd();
    Assertions.assertEquals(Optional.of("20240101"), actual);
  }

  @Test
  public void testToYyyyMm() {
    var actual = GenericDate.fromYyyyMmDd("20240101").toYyyyMm();
    Assertions.assertEquals(Optional.of("202401"), actual);
  }

  @Test
  public void testCoverage() {
    var obj = GenericDate.of(LocalDate.of(2024, 1, 1));
    obj.hashCode();
    assertEquals(obj, obj);
    assertEquals(GenericDate.of(LocalDate.of(2024, 1, 1)), obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
    Assertions.assertEquals("null", GenericDate.of(null).toString());
  }

}
