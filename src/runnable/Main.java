package runnable;

import java.util.Iterator;

import io.FilePartReader;
import statistics.FileWordAnalyzer;

public class Main {
	public static void main(String[] args) {
		FilePartReader fpr = new FilePartReader("src/file.txt", 1, 4);
		FileWordAnalyzer fwa = new FileWordAnalyzer(fpr);
		System.out.println(fpr.readLines());
		
		System.out.println("\nSorted list: ");
		Iterator it = fwa.wordsByAbc().iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
		
		System.out.println("\n\nWords containing 'ik'");
		it = fwa.wordsContatiningSubString("ik").iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}
	}
}
