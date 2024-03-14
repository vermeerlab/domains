package org.verneermlab.base.domain.type.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.TimeZone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NullableDateTimeTypeTest {

  @Test
  public void testIsEmpty() {

    var actual = new NullableDateTimeTypeImpl(null);
    Assertions.assertTrue((actual.isEmpty()));

  }

  @Test
  public void testGetZoneId() {
    var actual = new NullableDateTimeTypeImpl(null);
    Assertions.assertEquals(TimeZone.getTimeZone("Asia/Tokyo").toZoneId(), actual.getZoneId());
  }

  @Test
  public void testToLocalDate() {
    var actual1 = new NullableDateTimeTypeImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3));
    Assertions.assertEquals(LocalDate.of(2024, Month.MARCH, 1), actual1.toLocalDate().get());

    var actual2 = new NullableDateTimeTypeImpl(null);
    Assertions.assertEquals(Optional.empty(), actual2.toLocalDate());

  }

  @Test
  public void testToUnixTime() {
    var actual1 = new NullableDateTimeTypeImpl(LocalDateTime.of(2024, Month.MARCH, 1, 2, 3, 4));
    Assertions.assertEquals(1709226184L, actual1.toUnixTime().get());

    var actual2 = new NullableDateTimeTypeImpl(null);
    Assertions.assertTrue(actual2.toUnixTime().isEmpty());

  }

  public static class NullableDateTimeTypeImpl implements NullableDateTimeType<NullableDateTimeTypeImpl> {

    private final LocalDateTime value;

    public NullableDateTimeTypeImpl(LocalDateTime value) {
      this.value = value;
    }

    @Override
    public Optional<LocalDateTime> getNullableValue() {
      return Optional.ofNullable(value);
    }
  }

}
