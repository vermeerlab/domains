package org.verneermlab.apps.common.domain.part.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Yamashita.Takahiro
 */
public class TimeStampBaseTest {

  public TimeStampBaseTest() {
  }

  @Test
  public void testOf_Optional() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3)));
    assertEquals(LocalDateTime.of(2000, 1, 1, 2, 3), actual.getValue().get());

  }

  @Test
  public void testOf_LocalDateTime() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3)));
    assertEquals(LocalDateTime.of(2000, 1, 1, 2, 3), actual.getValue().get());
  }

  @Test
  public void testFromYyyyMmDd() {
    var actual = TimeStampBase.fromYyyyMmDd("20000101");
    assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0), actual.getValue().get());

    assertThrowsExactly(DateTimeParseException.class,
            () -> TimeStampBase.fromYyyyMmDd("2000010z")
    );

    var actual2 = TimeStampBase.fromYyyyMmDd("");
    assertTrue(actual2.isEmpty());

    var actual3 = TimeStampBase.fromYyyyMmDd(null);
    assertTrue(actual3.isEmpty());
  }

  @Test
  public void testToYyyyMmDdWithSeparator() {
    var actual = TimeStampBase.fromYyyyMmDd("20000101");
    assertEquals("2000/01/01", actual.toYyyyMmDdWithSeparator().get());
  }

  @Test
  public void testToYyyyMmDdHhMmSs() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/01 02:03:04", actual.toYyyyMmDdHhMmSs().get());
  }

  @Test
  public void testToDateBase() {
    var actual = TimeStampBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.of(LocalDate.of(2000, 1, 1)), actual.toDateBase());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.toDateBase().isEmpty());
  }

  @Test
  public void testToDate() {
    var actual = TimeStampBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.of(LocalDate.of(2000, 1, 1)).toDate(), actual.toDate());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.toDate().isEmpty());
  }

  @Test
  public void testToUnixTime() {
    var actual = TimeStampBase.fromYyyyMmDd("20000101");
    String str = "2000/01/01 00:00:00";
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    try {
      Date date = format.parse(str);
      assertEquals(date.getTime() / 1000, actual.toUnixTime().get());
    } catch (ParseException ex) {
      fail();
    }

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.toUnixTime().isEmpty());
  }

  @Test
  public void testPlusSeconds() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/01 02:03:05", actual.plusSeconds(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.plusSeconds(1).isEmpty());
  }

  @Test
  public void testMinusSeconds() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/01 02:03:03", actual.minusSeconds(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.minusSeconds(1).isEmpty());
  }

  @Test
  public void testPlusMinutes() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/01 02:04:04", actual.plusMinutes(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.plusMinutes(1).isEmpty());
  }

  @Test
  public void testMinusMinutes() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/01 02:02:04", actual.minusMinutes(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.minusMinutes(1).isEmpty());
  }

  @Test
  public void testPlusHours() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/01 03:03:04", actual.plusHours(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.plusHours(1).isEmpty());
  }

  @Test
  public void testMinusHours() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/01 01:03:04", actual.minusHours(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.minusHours(1).isEmpty());
  }

  @Test
  public void testPlusDays() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/01/02 02:03:04", actual.plusDays(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.plusDays(1).isEmpty());
  }

  @Test
  public void testMinusDays() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("1999/12/31 02:03:04", actual.minusDays(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.minusDays(1).isEmpty());
  }

  @Test
  public void testPlusMonths() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2000/02/01 02:03:04", actual.plusMonths(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.plusMonths(1).isEmpty());
  }

  @Test
  public void testMinusMonths() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("1999/12/01 02:03:04", actual.minusMonths(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.minusMonths(1).isEmpty());
  }

  @Test
  public void testPlusYears() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("2001/01/01 02:03:04", actual.plusYears(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.plusYears(1).isEmpty());
  }

  @Test
  public void testMinusYears() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 1, 2, 3, 4)));
    assertEquals("1999/01/01 02:03:04", actual.minusYears(1).toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.minusYears(1).isEmpty());
  }

  @Test
  public void testBeginMonth() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    assertEquals("2000/01/01 02:03:04", actual.beginMonth().toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.init();
    assertTrue(actual2.beginMonth().isEmpty());
  }

  @Test
  public void testEndMonth() {
    var actual = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    assertEquals("2000/01/31 02:03:04", actual.endMonth().toYyyyMmDdHhMmSs().get());

    var actual2 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 2, 3, 2, 3, 4)));
    assertEquals("2000/02/29 02:03:04", actual2.endMonth().toYyyyMmDdHhMmSs().get());

    var actual3 = TimeStampBase.init();
    assertTrue(actual3.endMonth().isEmpty());
  }

  @Test
  public void testIsEquel() {
    var actual1 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    var actual2 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    assertTrue(actual1.isEquel(actual2));

    var actual3 = TimeStampBase.init();
    var actual4 = TimeStampBase.init();
    assertTrue(actual3.isEquel(actual4));

  }

  @Test
  public void testIsNotEquel() {
    var actual1 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    var actual2 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 5)));
    assertTrue(actual1.isNotEquel(actual2));

    var actual3 = TimeStampBase.init();
    assertTrue(actual3.isNotEquel(actual1));
    assertTrue(actual1.isNotEquel(actual3));

  }

  @Test
  public void testIsBefore() {
    var actual1 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    var actual2 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 5)));
    assertTrue(actual1.isBefore(actual2));

    var actual3 = TimeStampBase.init();
    var actual4 = TimeStampBase.init();
    assertFalse(actual3.isBefore(actual4));
  }

  @Test
  public void testIsAfter() {
    var actual1 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 5)));
    var actual2 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    assertTrue(actual1.isAfter(actual2));

    var actual3 = TimeStampBase.init();
    var actual4 = TimeStampBase.init();
    assertFalse(actual3.isAfter(actual4));

  }

  @Test
  public void testEquals() {
    var actual1 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    var actual2 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    assertTrue(actual1.equals(actual2));

    var actual3 = TimeStampBase.init();
    var actual4 = TimeStampBase.init();
    assertTrue(actual3.equals(actual4));
  }

  @Test
  public void testToString() {
    var actual1 = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 4)));
    assertEquals("2000-01-03T02:03:04", actual1.toString());

    var actual3 = TimeStampBase.init();
    assertNull(actual3.toString());

  }

  @Test
  public void testCoverage() {
    var obj = TimeStampBase.of(Optional.of(LocalDateTime.of(2000, 1, 3, 2, 3, 5)));
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }

}
