package org.verneermlab.base.internal.domain.type;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class InstanceCreatorTest {

  public InstanceCreatorTest() {
  }

  @Test
  public void testNewInstanceFromThis() {
    var impl = new Test2();
    assertThrows(RuntimeException.class, () -> impl.newInstanceFromThis(""));
  }

  @Test
  public void testNewInstanceFromThis_Object() {
    var impl = new Test1("");
    assertThrows(RuntimeException.class, () -> impl.newInstanceFromThis());
  }

  public class Test1 implements InstanceCreator<Test1> {

    Test1(String value) {

    }
  }

  public class Test2 implements InstanceCreator<Test1> {

  }
}
