package uebungen;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hamming {





	public static void main(String[] args) {
		int distanz = berechneHammingDistanz("Test", "Taxs");
		System.out.println(distanz);
		String[] texte = {"0011", "0100", "0101", "1011"};
		String erg = berechneHammingSumme(texte);
	}

	private static String berechneHammingSumme(String[] texte) {
		String ergebnis = null;
		HashMap<String,  Integer> map = new HashMap<>();
		int summe = 0;

		for (int i = 0; i < texte.length; i++) {
			for (int j = 0; j < texte.length; j++) {
				summe += berechneHammingDistanz(texte[i], texte[j]);
			}
			map.put(texte[i], summe);
			summe = 0;
		}

		int minSumme = Integer.MAX_VALUE;

		for (Map.Entry<String, Integer> entries : map.entrySet()) {
			System.out.println(entries.getKey() + " => " + entries.getValue());
			if (entries.getValue() < minSumme) {
				minSumme = entries.getValue();
			}
		}

		System.out.println("minsumme: " + minSumme);

		return ergebnis;
	}


	private static int berechneHammingDistanz(String quelle, String ziel) {
		int distanz = 0;
		char[] quelleArray = quelle.toLowerCase().toCharArray();
		char[] zielArray = ziel.toLowerCase().toCharArray();

		for (int i = 0; i < quelle.length(); i++) {
			if (quelle.charAt(i) != ziel.charAt(i)) {
				distanz++;
			}
		}

		return distanz;
	}
}
