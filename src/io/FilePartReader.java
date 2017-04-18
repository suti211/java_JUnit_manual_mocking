package io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilePartReader {

	private String filePath;
	private Integer fromLine;
	private Integer toLine;

	public FilePartReader(String filePath, Integer fromLine, Integer toLine) {
		this.filePath = filePath;
		this.fromLine = fromLine;
		this.toLine = toLine;

		if (toLine < fromLine || fromLine < 1) {
			throw new IllegalArgumentException();
		}
	}

	private String read() throws FileNotFoundException, IOException {
		return new String(Files.readAllBytes(Paths.get(filePath)));
	}

	public String readLines() {
		String lines = null;

		try {
			lines = read();
		} catch (FileNotFoundException e) {
			System.err.println("Nó szaccs fájl: " + filePath);
		} catch (IOException e) {
			System.err.println("Shit went wrong during file reading...");
		}

		String[] rows = lines.split("\n");
		String result = "";

		for (int i = fromLine - 1; i < toLine; i++) {
			result += rows[i];
		}

		return result;
	}
}
