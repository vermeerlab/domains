package org.verneermlab.apps.common.domain.part.text;

import java.util.Objects;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TextTest {

  public TextTest() {
  }

  @Test
  public void testInit() {
    var text = Text.createNoValue();
    assertTrue(text.isEmpty());

  }

  @Test
  public void testOf_Optional() {
    var text1 = Text.of(null);
    assertTrue(text1.isEmpty());

    var text2 = Text.of("testStr");
    assertEquals("testStr", text2.getOrDefault());

  }

  @Test
  public void testOf_String() {
    var text1 = Text.of("testStr");
    assertEquals("testStr", text1.getOrDefault());
  }

  @Test
  public void testGetValue() {
    var text1 = Text.of("testStr");
    assertEquals("testStr", text1.getNullableValue().get());

    var text2 = Text.createNoValue();
    assertEquals(Optional.empty(), text2.getNullableValue());

  }

  @Test
  public void testPadZero() {
    var text1 = Text.of("aa").zeroPadding(5);
    assertEquals("000aa", text1.getOrDefault());

    var text2 = Text.createNoValue().zeroPadding(5);
    assertEquals("00000", text2.getOrDefault());
  }

  @Test
  public void testRemoveReturn() {
    var text1 = Text.of("aa\n").removeReturn();
    assertEquals("aa", text1.getOrDefault());

    var text2 = Text.createNoValue().removeReturn();
    assertEquals("", text2.getOrDefault());
  }

  @Test
  public void testConcat() {
    var text1 = Text.of("aa").concat(Text.of("bb"), Text.of("cc"));
    assertEquals("aabbcc", text1.getOrDefault());
  }

  @Test
  public void testJoin() {
    var text1 = Text.of("aa").join(Text.of("-"), Text.of("bb"), Text.of("cc"));
    assertEquals("aa-bb-cc", text1.getOrDefault());
  }

  @Test
  public void testSubstringBegin() {
    var text1 = Text.of("123456789");
    assertEquals("123456789", text1.substring(0).getOrDefault());
    assertEquals("123456789", "123456789".substring(0));

    var text2 = Text.of("🍀𩸽飴\uDB40\uDD01");
    assertEquals("🍀𩸽飴\uDB40\uDD01", text2.substring(0).getOrDefault());
    assertEquals("𩸽飴\uDB40\uDD01", text2.substring(1).getOrDefault());

    assertEquals("", text2.substring(100).getOrDefault());
    assertEquals("", "123456789".substring(9));
  }

  @Test
  public void testSubstring() {
    var text1 = Text.of("123456789");

    assertEquals("", text1.substring(-1, 1).getOrDefault());

    assertEquals("1", text1.substring(0, 1).getOrDefault());
    assertEquals("1", "123456789".substring(0, 1));

    var text2 = Text.of("🍀𩸽飴\uDB40\uDD01");
    assertEquals("🍀", text2.substring(0, 1).getOrDefault());
    assertEquals("𩸽", text2.substring(1, 2).getOrDefault());

    assertEquals("", text2.substring(1, 1).getOrDefault());
    assertEquals("", "123456789".substring(1, 1));

    assertEquals("🍀𩸽飴\uDB40\uDD01", text2.substring(0, 100).getOrDefault());
  }

  @Test
  public void testApply() {

    var text = Text.of("aaa");
    var callbacked1 = text.apply((value) -> value + "test");
    assertEquals("aaatest", callbacked1.getOrDefault());

    var textEmpty = Text.createNoValue();
    var callbacked2 = textEmpty.apply((value) -> (Objects.isNull(value) ? "bb" : "cc") + "test");
    assertEquals("bbtest", callbacked2.getOrDefault());

  }

  @Test
  public void testContains() {
    var text1 = Text.of("aa");
    assertTrue(text1.contains(Text.of("a")));
    assertFalse(text1.contains(Text.of("b")));
    assertFalse(Text.createNoValue().contains(Text.of("b")));
    assertFalse(text1.contains(Text.createNoValue()));
    assertFalse(Text.createNoValue().contains(Text.createNoValue()));
  }

  @Test
  public void testLength() {
    var text1 = Text.of("aa");
    assertEquals(2, text1.length());

    var text2 = Text.of("");
    assertEquals(0, text2.length());

    var text3 = Text.createNoValue();
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

    var text2 = Text.createNoValue();
    assertFalse(text2.hasText());

  }

  @Test
  public void testIsEmpty() {
    var text1 = Text.of("aa");
    assertFalse(text1.isEmpty());

    var text2 = Text.createNoValue();
    assertTrue(text2.isEmpty());

  }

  @Test
  public void testEquals() {
    assertEquals(Text.createNoValue(), Text.createNoValue());
    assertEquals(Text.of("1"), Text.of("1"));
    assertNotEquals(Text.of(""), Text.createNoValue());
  }

  @Test
  public void testToString() {
    assertEquals("", Text.of("").toString());
    assertEquals("", Text.createNoValue().toString());
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
