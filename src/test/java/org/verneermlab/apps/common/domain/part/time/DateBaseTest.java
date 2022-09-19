package org.verneermlab.apps.common.domain.part.time;

import java.math.BigDecimal;
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
public class DateBaseTest {

  public DateBaseTest() {
  }

  @Test
  public void testOf_Optional() {
    var actual = DateBase.of(Optional.of(LocalDate.of(2000, 1, 1)));
    assertEquals(LocalDate.of(2000, 1, 1), actual.getValue().get());
  }

  @Test
  public void testOf_LocalDate() {
    var actual = DateBase.of(LocalDate.of(2000, 1, 1));
    assertEquals(LocalDate.of(2000, 1, 1), actual.getValue().get());
  }

  @Test
  public void testFromYyyyMdDd() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(LocalDate.of(2000, 1, 1), actual.getValue().get());

    assertThrowsExactly(DateTimeParseException.class,
            () -> DateBase.fromYyyyMmDd("2000010z")
    );

  }

  @Test
  public void testToYyyyMmDd() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals("20000101", actual.toYyyyMmDd().get());

    var actualBlank = DateBase.fromYyyyMmDd("");
    assertEquals(DateBase.init(), actualBlank);

    var actualNull = DateBase.fromYyyyMmDd(null);
    assertEquals(DateBase.init(), actualNull);

    var actualInit = DateBase.init();
    assertTrue(actualInit.toYyyyMmDd().isEmpty());

  }

  @Test
  public void testToYyyyMm() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals("200001", actual.toYyyyMm().get());
  }

  @Test
  public void testToDate() {
    var actual = DateBase.fromYyyyMmDd("20000101");

    String str = "2000/01/01 00:00:00";
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    try {
      Date date = format.parse(str);
      assertEquals(date, actual.toDate().get());
    } catch (ParseException ex) {
      fail();
    }
  }

  @Test
  public void testToLocalDateTime() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(LocalDateTime.of(2000, 1, 1, 0, 0),
            actual.toLocalDateTime().get()
    );
  }

  @Test
  public void testToUnixTime() {
    var actual = DateBase.fromYyyyMmDd("20000101");

    String str = "2000/01/01 00:00:00";
    SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    try {
      Date date = format.parse(str);
      assertEquals(date.getTime() / 1000, actual.toUnixTime().get());
    } catch (ParseException ex) {
      fail();
    }

    var actualEmpty = DateBase.init();
    assertEquals(Optional.empty(), actualEmpty.toUnixTime());

  }

  @Test
  public void testPlusDays() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.fromYyyyMmDd("20000102"), actual.plusDays(1));
    assertEquals(DateBase.init(), DateBase.init().plusDays(1));
  }

  @Test
  public void testMinusDays() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.fromYyyyMmDd("19991231"), actual.minusDays(1));
    assertEquals(DateBase.init(), DateBase.init().minusDays(1));
  }

  @Test
  public void testPlusMonths() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.fromYyyyMmDd("20000201"), actual.plusMonths(1));
    assertEquals(DateBase.init(), DateBase.init().plusMonths(1));
  }

  @Test
  public void testMinusMonths() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.fromYyyyMmDd("19991201"), actual.minusMonths(1));
    assertEquals(DateBase.init(), DateBase.init().minusMonths(1));
  }

  @Test
  public void testPlusYears() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.fromYyyyMmDd("20010101"), actual.plusYears(1));
    assertEquals(DateBase.init(), DateBase.init().plusYears(1));
  }

  @Test
  public void testMinusYears() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(DateBase.fromYyyyMmDd("19990101"), actual.minusYears(1));
    assertEquals(DateBase.init(), DateBase.init().minusYears(1));
  }

  @Test
  public void testBeginMonth() {
    var actual = DateBase.fromYyyyMmDd("20000102");
    assertEquals(DateBase.fromYyyyMmDd("20000101"), actual.beginMonth());
    assertEquals(DateBase.init(), DateBase.init().beginMonth());
  }

  @Test
  public void testEndMonth() {
    var actual = DateBase.fromYyyyMmDd("20000102");
    assertEquals(DateBase.fromYyyyMmDd("20000131"), actual.endMonth());
    assertEquals(DateBase.init(), DateBase.init().endMonth());
  }

  @Test
  public void testRangeMonth() {
    var actual = DateBase.fromYyyyMmDd("20000102");
    assertEquals(new BigDecimal(1), actual.rangeMonth(DateBase.fromYyyyMmDd("20000202")));
    assertEquals(new BigDecimal(-1), actual.rangeMonth(DateBase.fromYyyyMmDd("19991202")));
    assertEquals(BigDecimal.ZERO, DateBase.init().rangeMonth(DateBase.fromYyyyMmDd("20000202")));
    assertEquals(BigDecimal.ZERO, DateBase.fromYyyyMmDd("20000202").rangeMonth(DateBase.init()));
    assertEquals(BigDecimal.ZERO, DateBase.init().rangeMonth(DateBase.init()));
  }

  @Test
  public void testRangeMonthHalfUp() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000101")));
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000102")));
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000103")));
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000104")));
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000105")));
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000106")));
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000107")));
    assertEquals(new BigDecimal(0), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000108")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000109")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000110")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000111")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000112")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000113")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000114")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000115")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000116")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000117")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000118")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000119")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000120")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000121")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000122")));
    assertEquals(new BigDecimal(0.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000123")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000124")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000125")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000126")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000127")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000128")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000129")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000130")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000131")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000201")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000202")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000203")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000204")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000205")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000206")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000207")));
    assertEquals(new BigDecimal(1), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000208")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000209")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000210")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000211")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000212")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000213")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000214")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000215")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000216")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000217")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000218")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000219")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000220")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000221")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000222")));
    assertEquals(new BigDecimal(1.5), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000223")));
    assertEquals(new BigDecimal(2), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000224")));
    assertEquals(new BigDecimal(2), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000225")));
    assertEquals(new BigDecimal(2), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000226")));
    assertEquals(new BigDecimal(2), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000227")));
    assertEquals(new BigDecimal(2), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000228")));
    assertEquals(new BigDecimal(2), actual.rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000229")));

    assertEquals(BigDecimal.ZERO, actual.rangeMonthHalfUp(DateBase.init()));
    assertEquals(BigDecimal.ZERO, DateBase.init().rangeMonthHalfUp(DateBase.fromYyyyMmDd("20000229")));
    assertEquals(BigDecimal.ZERO, DateBase.init().rangeMonthHalfUp(DateBase.init()));

  }

  @Test
  public void testGetValue() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(LocalDate.of(2000, 1, 1), actual.getValue().get());
    assertNull(DateBase.init().getValue().orElse(null));
  }

  @Test
  public void testIsEmpty() {
    assertTrue(DateBase.init().isEmpty());
    assertFalse(DateBase.fromYyyyMmDd("20000101").isEmpty());
  }

  @Test
  public void testEquals() {
    assertTrue(DateBase.init().equals(DateBase.init()));
    assertTrue(DateBase.fromYyyyMmDd("20000101").equals(DateBase.fromYyyyMmDd("20000101")));
    assertFalse(DateBase.init().equals(DateBase.fromYyyyMmDd("20000101")));
  }

  @Test
  public void testToString() {
    var actual = DateBase.fromYyyyMmDd("20000101");
    assertEquals(LocalDate.of(2000, 1, 1).toString(), actual.toString());
    assertEquals("2000-01-01", actual.toString());
    assertNull(DateBase.init().toString());
  }

  @Test
  public void testCoverage() {
    var obj = DateBase.of(LocalDate.of(2000, 1, 1));
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }
}
