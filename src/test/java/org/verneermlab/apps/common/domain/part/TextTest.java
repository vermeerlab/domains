package org.verneermlab.apps.common.domain.part;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Yamashita.Takahiro
 */
public class TextTest {

  public TextTest() {
  }

  @Test
  public void testInit() {
    var text = Text.init();
    assertTrue(text.isEmpty());

  }

  @Test
  public void testOf_Optional() {
    var text1 = Text.of(Optional.empty());
    assertTrue(text1.isEmpty());

    var text2 = Text.of(Optional.of("testStr"));
    assertEquals("testStr", text2.get());

  }

  @Test
  public void testOf_String() {
    var text1 = Text.of("testStr");
    assertEquals("testStr", text1.get());
  }

  @Test
  public void testGetValue() {
    var text1 = Text.of("testStr");
    assertEquals("testStr", text1.getValue().get());

    var text2 = Text.init();
    assertEquals(Optional.empty(), text2.getValue());

  }

  @Test
  public void testPadZero() {
    var text1 = Text.of("aa").padZero(5);
    assertEquals("000aa", text1.get());

    var text2 = Text.init().padZero(5);
    assertEquals("00000", text2.get());
  }

  @Test
  public void testRemoveReturn() {
    var text1 = Text.of("aa\n").removeReturn();
    assertEquals("aa", text1.get());

    var text2 = Text.init().removeReturn();
    assertEquals("", text2.get());
  }

  @Test
  public void testConcat() {
    var text1 = Text.of("aa").concat(Text.of("bb"), Text.of("cc"));
    assertEquals("aabbcc", text1.get());
  }

  @Test
  public void testJoin() {
    var text1 = Text.of("aa").join(Text.of("-"), Text.of("bb"), Text.of("cc"));
    assertEquals("aa-bb-cc", text1.get());
  }

  @Test
  public void testApply() {

    var text = Text.of("aaa");
    var callbacked1 = text.apply((value) -> value + "test");
    assertEquals("aaatest", callbacked1.get());

    var textEmpty = Text.init();
    var callbacked2 = textEmpty.apply((value) -> value + "test");
    assertEquals("test", callbacked2.get());

  }

  @Test
  public void testOrElse() {
    var text1 = Text.of("aa").orElse("test");
    assertEquals("aa", text1);

    var text2 = Text.init().orElse("test");
    assertEquals("test", text2);

  }

  @Test
  public void testContains() {
    var text1 = Text.of("aa");
    assertTrue(text1.contains(Text.of("a")));
    assertFalse(text1.contains(Text.of("b")));
  }

  @Test
  public void testLength() {
    var text1 = Text.of("aa");
    assertEquals(2, text1.length());

    var text2 = Text.of("");
    assertEquals(0, text2.length());

    var text3 = Text.init();
    assertEquals(0, text3.length());

    var text4 = Text.of("🍀");
    assertEquals(1, text4.length());

    var text5 = Text.of("👪");
    assertEquals(1, text5.length());

    var text6 = Text.of("𩸽");
    assertEquals(1, text6.length());

    var text7 = Text.of("飴󠄁");
    assertEquals(1, text7.length());

    var text8 = Text.of("\uD867\uDE3D");  // U+29E3D
    assertEquals(1, text8.length());

    var text9 = Text.of("飴\uDB40\uDD01");  // U+98F4 U+E0101
    assertEquals(1, text9.length());

  }

  @Test
  public void testHasText() {
    var text1 = Text.of("aa");
    assertTrue(text1.hasText());

    var text2 = Text.init();
    assertFalse(text2.hasText());

  }

  @Test
  public void testIsEmpty() {
    var text1 = Text.of("aa");
    assertFalse(text1.isEmpty());

    var text2 = Text.init();
    assertTrue(text2.isEmpty());

  }

  @Test
  public void testEquals() {
    assertEquals(Text.init(), Text.init());
    assertEquals(Text.of("1"), Text.of("1"));
    assertNotEquals(Text.of(""), Text.init());
  }

  @Test
  public void testToString() {
    assertEquals("", Text.of("").toString());
    assertNull(Text.init().toString());
  }

  @Test
  public void testCoverage() {
    var obj = Text.of("1");
    obj.hashCode();
    assertEquals(obj, obj);
    assertNotEquals(obj, null);
    assertNotEquals(obj, "");
  }
}
