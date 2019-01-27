import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * µ¥Ôª²âÊÔ
 * @author GTC
 *
 */
public class TextProcessorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test0() {
		TextProcessor tp = new TextProcessor();
		assertEquals("ERROR: Invalid character detected!", tp.process("I have some number 1112344", 20));
	}

	@Test
	public void test1() {
		TextProcessor tp = new TextProcessor();
		assertEquals("ERROR: Width out of range!", tp.process("So many whitespaces", 3));
	}

	@Test
	public void test2() {
		TextProcessor tp = new TextProcessor();
		assertEquals("ERROR: Width out of range!", tp.process("So many whitespaces", 90));
	}

	@Test
	public void test3() {
		TextProcessor tp = new TextProcessor();
		assertEquals("So(1);   (1);many(1); (1);whitespaces(2,3);", tp.process("So   many whitespaces", 10));
	}

	@Test
	public void test4() {
		TextProcessor tp = new TextProcessor();
		assertEquals(" (1);So(1); (1);many(1); (1);whitespaces(1,2);", tp.process(" So many whitespaces", 10));
	}

	@Test
	public void test5() {
		TextProcessor tp = new TextProcessor();
		assertEquals("So(1); (1);many(1); (1);whitespaces(1,2);  (2,3);", tp.process("So many whitespaces  ", 10));
	}

	@Test
	public void test6() {
		TextProcessor tp = new TextProcessor();
		assertEquals(" (1);So(1); (1);many(1); (1);whitespaces(1,2); (3);", tp.process(" So many whitespaces ", 10));
	}

	@Test
	public void test7() {
		TextProcessor tp = new TextProcessor();
		assertEquals("So(1); (1);manywhitespacesAAA(1,2,3);", tp.process("So manywhitespacesAAA", 10));
	}

	@Test
	public void test8() {
		TextProcessor tp = new TextProcessor();
		assertEquals(
				"The(1); (1);main(1); (1);theme(1); (1);of(1); (1);education(1); (1);in(1); (2);engineering(2); (2);school(2); (2);is(2); (2);learning(2,3); (3);to(3); (3);teach(3); (3);yourself(3);",
				tp.process("The main theme of education in engineering school is learning to teach yourself", 30));
	}

	@Test
	public void test9() {
		TextProcessor tp = new TextProcessor();
		assertEquals("      (1);", tp.process("      ", 10));
	}

	@Test
	public void test10() {
		TextProcessor tp = new TextProcessor();
		assertEquals("            (1,2);", tp.process("            ", 10));
	}

	@Test
	public void test11() {
		TextProcessor tp = new TextProcessor();
		assertEquals("aaaaaa(1);", tp.process("aaaaaa", 10));
	}

	@Test
	public void test12() {
		TextProcessor tp = new TextProcessor();
		assertEquals("aaaaaaaaaaaa(1,2);", tp.process("aaaaaaaaaaaa", 10));
	}

	@Test
	public void test13() {
		TextProcessor tp = new TextProcessor();
		assertEquals("ERROR: Empty string", tp.process("", 20));
	}
}
