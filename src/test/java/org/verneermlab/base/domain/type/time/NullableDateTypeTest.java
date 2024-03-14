package org.verneermlab.base.domain.type.time;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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

  @Test
  public void testToDate() {
    var actual1 = new NullableDateTypeImpl(LocalDate.of(2024, Month.MARCH, 1));
    Assertions.assertEquals(Date.valueOf("2024-03-01"), actual1.toDate().get());

    var actual2 = new NullableDateTypeImpl(null);
    Assertions.assertTrue(actual2.toDate().isEmpty());
  }

  @Test
  public void testToLocalDateTime() {
    var actual1 = new NullableDateTypeImpl(LocalDate.of(2024, Month.MARCH, 1));
    Assertions.assertEquals(LocalDateTime.of(2024, Month.MARCH, 1, 0, 0), actual1.toLocalDateTime().get());

    var actual2 = new NullableDateTypeImpl(null);
    Assertions.assertTrue(actual2.toLocalDateTime().isEmpty());
  }

  @Test
  public void testToUnixTime() {
    var actual1 = new NullableDateTypeImpl(LocalDate.of(2024, Month.MARCH, 1));
    Assertions.assertEquals(1709218800L, actual1.toUnixTime().get());

    var actual2 = new NullableDateTypeImpl(null);
    Assertions.assertTrue(actual2.toUnixTime().isEmpty());
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
