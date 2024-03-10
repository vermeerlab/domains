package org.verneermlab.apps.common.domain.part.numeric;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class NullableNumberTest {

  @Test
  public void testOf() {
    Integer nullInt = null;
    var result1 = NullableNumber.of(nullInt);
    assertTrue(result1.isEmpty());

    var result2 = NullableNumber.of(1);
    assertEquals(NullableNumber.of(1), result2);
  }

  @Test
  public void testGetNullableValue() {
    Integer nullInt = null;
    var result1 = NullableNumber.of(nullInt);
    assertTrue(result1.getNullableValue().isEmpty());

    var result2 = NullableNumber.of(1);
    assertEquals(1, result2.getNullableValue().get().intValue());
  }

  @Test
  public void testGetRoundingMode() {
    var result = NullableNumber.of(1);
    assertEquals(RoundingMode.UNNECESSARY, result.getRoundingMode());

  }

  @Test
  public void testForCoverage() {
    var result1 = NullableNumber.of(1);
    assertTrue(result1.hashCode() >= 0);
    assertTrue(result1.equals(result1));
    assertFalse(result1.equals(null));
    assertFalse(result1.equals(new BigDecimal(1)));
    assertEquals(("1"), result1.toString());
  }

}
