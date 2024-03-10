package org.verneermlab.base.domain.type.time;

import java.time.LocalDate;
import java.util.Optional;
import java.util.TimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullableDateTypeTest {

  @Test
  public void testIsEmpty() {
    var actual = new NullableDateTypeImpl(null);
    Assertions.assertTrue(actual.isEmpty());
  }

  @Test
  public void testGetZoneId() {
    var actual = new NullableDateTypeImpl(null);
    Assertions.assertEquals(TimeZone.getTimeZone("Asia/Tokyo").toZoneId(), actual.getZoneId());

  }

  public static class NullableDateTypeImpl implements NullableDateType<NullableDateTypeImpl> {

    private LocalDate value;

    public NullableDateTypeImpl(LocalDate value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDate> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
