package test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import io.FilePartReader;
import statistics.FileWordAnalyzer;

public class FilePartReaderTester {

	Class noparams[] = {};

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	// @Test
	// public void testRead() throws Exception {
	// Method read = FilePartReader.class.getDeclaredMethod("read", noparams);
	// read.setAccessible(true);
	// String fromFile = (String) read.invoke(new FilePartReader("src/file.txt",
	// 1, 4), null);
	// fromFile = fromFile.replaceAll("\\s", "");
	// String fileContent =
	// "elsosormasodiksormegmindigmasodiksoraztanegyharmadikisvegullegyenmaregynegyedikiscsakugy";
	// assertEquals(fileContent, fromFile);
	// }

	@Test
	public void testConstructorParameters() {

		new FilePartReader("shit", 2, 5);
	}

	@Test
	public void testInvalidFilepath() throws IOException {
		exception.expect(IOException.class);
		new FilePartReader("asd.txt", 1, 2).readLines();
	}

	@Test
	public void testConstructorWithZero() {
		exception.expect(IllegalArgumentException.class);
		new FilePartReader("src/file.txt", 0, 2);
	}

	@Test
	public void testConstructorWithLower() {
		exception.expect(IllegalArgumentException.class);
		new FilePartReader("src/file.txt", 2, 1);
	}

	@Test
	public void testReadLines1() throws IOException {
		String result = new FilePartReader("src/file.txt", 1, 4).readLines();
		result = result.replaceAll("\\s", "");
		String fileContent = "elsosormasodiksormegmindigmasodiksoraztanegyharmadikisvegullegyenmaregynegyedikiscsakugy";
		assertEquals(fileContent, result);
	}

	@Test
	public void testReadLines2() throws IOException {
		String result = new FilePartReader("src/file.txt", 2, 4).readLines();
		result = result.replaceAll("\\s", "");
		String fileContent = "masodiksormegmindigmasodiksoraztanegyharmadikisvegullegyenmaregynegyedikiscsakugy";
		assertEquals(fileContent, result);
	}

	@Test
	public void testReadLines3() throws IOException {
		String result = new FilePartReader("src/file.txt", 2, 3).readLines();
		result = result.replaceAll("\\s", "");
		String fileContent = "masodiksormegmindigmasodiksoraztanegyharmadikis";
		assertEquals(fileContent, result);
	}

	@Test
	public void testSortByABC() throws IOException {
		class MockReader extends FilePartReader {

			public MockReader(String path, Integer from, Integer to) {
				super(path, from, to);
			}

			@Override
			public String readLines() {
				return "allahu\nakbar\nisten a legnagyobb";
			}
		}

		MockReader reader = new MockReader("dummy", 1, 2);

		FileWordAnalyzer fwa = new FileWordAnalyzer(reader);
		ArrayList<String> result = fwa.wordsByAbc();

		String sorted = "";

		for (String s : result) {
			sorted += s;
		}

		String expected = "aakbarallahuistenlegnagyobb";

		assertEquals(expected, sorted);

	}

	@Test
	public void testContainingWords() throws IOException {
		class MockReader extends FilePartReader {

			public MockReader(String path, Integer from, Integer to) {
				super(path, from, to);
			}

			@Override
			public String readLines() {
				return "fekete majmok\nvannak a ketrecen\nkívül borsodban";
			}

		}

		MockReader reader = new MockReader("dummy", 1, 2);

		FileWordAnalyzer fwa = new FileWordAnalyzer(reader);
		ArrayList<String> result = fwa.wordsContatiningSubString("et");
		
		String containing = "";
		
		for (String s : result) {
			containing += s;
		}
		
		String expected = "feketeketrecen";
		assertEquals(expected, containing);
	}

}
