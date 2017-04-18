package runnable;

import io.FilePartReader;

public class Main {
	public static void main(String[] args) {
		FilePartReader fpr = new FilePartReader("src/file.txt", 1, 4);
		System.out.println(fpr.readLines());
	}
}
