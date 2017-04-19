package statistics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.FilePartReader;

public class FileWordAnalyzer {
	FilePartReader partReader;

	public FileWordAnalyzer(FilePartReader partReader) {
		this.partReader = partReader;
	}

	public ArrayList<String> wordsByAbc() throws IOException {
		ArrayList<String> sortedWords = new ArrayList<>();

		for (String word : getStrippedFilePart()) {
			sortedWords.add(word);
		}

		// Collections.sort(sortedWords, String.CASE_INSENSITIVE_ORDER);
		sortedWords.sort(String::compareToIgnoreCase);

		return sortedWords;
	}

	public ArrayList<String> wordsContatiningSubString(String substring) throws IOException {
		ArrayList<String> containingWords = new ArrayList<>();

		for (String word : getStrippedFilePart()) {
			if (word.contains(substring)) {
				containingWords.add(word);
			}
		}
		return containingWords;
	}

	private String[] getStrippedFilePart() throws IOException {
		String filePart = partReader.readLines();
		String[] wordsInPart = filePart.replaceAll("\\s", " ").split(" ");
		return wordsInPart;
	}
}