package org.verneermlab.base.utils.enumclass;

import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EnumReverseLookupTest {

  private enum TestEnum {
    A("1"), B("2");

    private TestEnum(String cd) {
      this.cd = cd;
    }

    private final String cd;

    String getCd() {
      return cd;
    }

    static final EnumReverseLookup<TestEnum, String> byCd
            = new EnumReverseLookup<>(TestEnum.class, TestEnum::getCd);
  }

  public EnumReverseLookupTest() {
  }

  @Test
  public void testLookup() {
    Optional<TestEnum> actual = TestEnum.byCd.lookup("1");
    Assertions.assertEquals(TestEnum.A, actual.get());
  }

  @Test
  public void testLookupNotMatch() {
    var actual = TestEnum.byCd.lookup("a");
    Assertions.assertTrue(actual.isEmpty());
  }
}
