package SB5;

public class RotThirteen {

	public RotThirteen() {

	}

	public static String rot13(String word) {
		String rot13Wort = "";
		char[] buchstaben;
		char[] rot13Buchstaben = null;
		int value = 0;
		int diff = 0;

		if (word != null) {
			buchstaben = word.toCharArray();
			rot13Buchstaben = new char[word.length()];
			for (int i = 0; i < buchstaben.length; i++) {
				if (buchstaben[i] >= 65 && buchstaben[i] <= 122) {
					value = (int) buchstaben[i] - 13;
					if (buchstaben[i] <= 122 && buchstaben[i] >= 97) {
						if (value < 97) {
							diff = 97 - value;
							value = 123 - diff; // erstes diff ist bereits z
						}
						rot13Buchstaben[i] = (char) value;
					} else if (buchstaben[i] <= 90 && buchstaben[i] >= 65) {
						if (value < 65) {
							diff = 65 - value;
							value = 91 - diff; // erstes diff ist bereits z
						}
						rot13Buchstaben[i] = (char) value;
					}
				} else {
					rot13Buchstaben[i] = buchstaben[i];
				}
			}
			rot13Wort = String.copyValueOf(rot13Buchstaben);
		}

		return rot13Wort;
	}

}