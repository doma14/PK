package SB7;

import java.io.*;
import java.net.URI;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Diese Klasse bietet Funktionalitäten, um einzelne Buchstaben, ganze Sätze oder Dateien
 * mithilfe der Vigenere-Matrix zu ver- und entschlüsseln.
 */
public class Vigenere {

	private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	private static final String SUFFIX_ENC = ".enc";
	private static final String SUFFIX_DEC = ".dec";
	private static char[][] vigenereMatrix;

	/*
	 * 'Static-Konstruktor'-Block, dabei wird die Vigenere-Matrix initialisiert.
	 * Vigenere-Matrix ist dadurch charakterisiert, dass an der Y-Achse der Schlüssel angetragen ist
	 * und auf der X-Achse der Klartextbuchstabe bzw. Chiffrebuchstabe.
	 */
	static {
		createMatrix();
		//print();
	}

	/**
	 * Methode dient zum Initialisieren der Vigenere-Matrix.
	 */
	private static void createMatrix() {
		int index;
		int alphabetIndex = 0;
		if (vigenereMatrix == null) {
			vigenereMatrix = new char[ALPHABET.length][ALPHABET.length];
			for (int i = 0; i < ALPHABET.length; i++) {
				index = alphabetIndex;
				for (int j = 0; j < ALPHABET.length; j++) {
					vigenereMatrix[i][j] = ALPHABET[index];
					index = (index + 1) % 26; //damit der index wieder von vorne anfaengt
				}
				alphabetIndex++; //verschiebt den anfangsbuchstaben einer zeile um 1
			}
		}
	}

	/**
	 * Ermittelt aus einem Klartextbuchstaben und einem Schlüsselbuchstaben den entsprechenden Wert der
	 * Vigenere-Matrix.
	 * @param clearTextChar Klartextbuchstabe
	 * @param secretKeyChar Schlüsselbuchstabe
	 * @return <code>char</code> ermittelter Buchstabe der Vigenere-Matrix
	 */
	public static char getCipher(char clearTextChar, char secretKeyChar) {
		char verschluesselterBuchstabe;
		boolean istKleinbuchstabe = Character.isLowerCase(clearTextChar);
		int indexSchluessel = ermittleMatrixIndex(secretKeyChar);
		int indexKlartextbuchstabe = ermittleMatrixIndex(clearTextChar);

		verschluesselterBuchstabe = vigenereMatrix[indexSchluessel][indexKlartextbuchstabe];
		//erhaelt groß-bzw kleinschreibung
		verschluesselterBuchstabe = istKleinbuchstabe ? Character.toLowerCase(verschluesselterBuchstabe)
				: Character.toUpperCase(verschluesselterBuchstabe);

		return verschluesselterBuchstabe;
	}

	/**
	 * Verschlüsselt einen gegebenen Text mit einem gegebenen Schlüssel. Dabei bleiben Satzzeichen, Umlaute
	 * und Groß- und Kleinschreibung erhalten.
	 * Im Falle falscher Parameter wird ein leerer String zurückgeliefert.
	 *
	 * @param clearText Klartext
	 * @param secretKey Schlüssel
	 * @return Geheimtext
	 */
	public static String encryptText(String clearText, String secretKey) {
		StringBuilder cipherBuilder = new StringBuilder();

		if (clearText != null && secretKey != null) {
			char[] clearTextArray = clearText.toCharArray();
			char[] secretKeyArray = secretKey.toCharArray();

			int keyIndex = 0;
			int max = secretKeyArray.length;

			for (char clearLetter : clearTextArray) {
				if (clearLetter >= 'A' && clearLetter <= 'z') {
					cipherBuilder.append(Vigenere.getCipher(clearLetter, secretKeyArray[keyIndex]));
					keyIndex = (keyIndex + 1) % max;
				} else {
					cipherBuilder.append(clearLetter);
					//bei nicht erlaubten Buchstaben wird der Schluessel nicht verwendet, der Index muss daher nicht
					//angehoben werden
				}
			}
		}

		return cipherBuilder.toString();
	}

	/**
	 * Verschlüsselt den Dateiinhalt, der zum übergebenen Dateipfad gehört, mit dem gegebenen Schlüssel.
	 *
	 * @param filePath der Pfad zur Datei, die verschlüsselt werden soll
	 * @param secretKey geheimer Schlüssel
	 * @throws FileAlreadyExistsException Wird geworfen, falls die .enc-Datei schon existiert
	 */
	public static void encryptFile(String filePath, String secretKey) throws FileAlreadyExistsException {
		Path path = Paths.get(filePath);
		if (Files.isDirectory(path) || !Files.exists(path)) { //pruefe, ob parameter eine datei ist und existiert
			System.out.println("File does not exist.");
			System.exit(1);
		} else {

			if (new File(filePath + Vigenere.SUFFIX_ENC).exists()) {
				throw new FileAlreadyExistsException("Die Datei " + filePath + Vigenere.SUFFIX_ENC +
						" existiert bereits!");
			}
			BufferedWriter bufferedWriter = null;
			BufferedReader bufferedReader = null;
			try {
				File verschluesselteDatei = Files.createFile(Paths.get(filePath + Vigenere.SUFFIX_ENC)).toFile();
				bufferedWriter = new BufferedWriter(new FileWriter(verschluesselteDatei));
				bufferedReader = new BufferedReader(new FileReader(filePath));
				String zeile;
				StringBuilder klartext = new StringBuilder();
				StringBuilder verschluesselterInhalt = new StringBuilder();

				while ((zeile = bufferedReader.readLine()) != null) {
					klartext.append(zeile).append("\n");
				}

				//verschluessele dateiinhalt
				verschluesselterInhalt.append(Vigenere.encryptText(klartext.toString(), secretKey));

				//schreibe verschluesselten inhalt in datei
				bufferedWriter.write(verschluesselterInhalt.toString());
				bufferedWriter.flush();

				//schliesse alle streams
				bufferedReader.close();
				bufferedWriter.close();


			} catch (IOException e) {
				System.err.println("Beim Bearbeiten der Dateien ist ein Fehler aufgetreten.");
				//Logausgabe
				//e.printStackTrace();
			} finally {
				try {
					if (bufferedReader != null) {
						bufferedReader.close();
					}
					if (bufferedWriter != null) {
						bufferedWriter.close();
					}
				} catch (IOException e) {
					System.err.println("Beim Bearbeiten der Dateien ist ein Fehler aufgetreten.");
					//Logausgabe
					//e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Eine Methode, die einen verschlüsselten Buchstaben mithilfe
	 * des Vigenère-Quadrats wieder entschlüsselt. Der entschlüsselte
	 * Buchstabe wird zurückgegeben.
	 *
	 * @param encryptedChar verschlüsseltes Zeichen
	 * @param secretKeyChar Zeichen des Schlüssels
	 * @return Klartextbuchstabe
	 */
	public static char getClearTextChar(char encryptedChar, char secretKeyChar) {
		char clearTextChar = 0;
		int indexKeyChar = ermittleMatrixIndex(secretKeyChar);
		int indexClearChar = -1;
		boolean isUppercase = Character.isUpperCase(encryptedChar);

		for (int i = 0; i < Vigenere.vigenereMatrix.length; i++) {
			if (Vigenere.vigenereMatrix[i][indexKeyChar] == Character.toUpperCase(encryptedChar)) {
				indexClearChar = i;
			}
		}
		clearTextChar = isUppercase ? Character.toUpperCase(vigenereMatrix[indexClearChar][0]) : Character.toLowerCase(vigenereMatrix[indexClearChar][0]);

		return clearTextChar;
	}

	/**
	 * Entschlüsselt einen gegebenen Text mit einem gegebenen Schlüssel. Dabei bleiben Satzzeichen, Umlaute
	 * und Groß- und Kleinschreibung erhalten.
	 * Im Falle falscher Parameter wird ein leerer String zurückgeliefert.
	 *
	 * @param encryptedText Verschlüsselter Text, der entschlüsselt werden soll
	 * @param secretKey Key, der für die Entschlüsselung verwendet wird
	 * @return Klartext
	 */
	public static String decryptText(String encryptedText, String secretKey) {
		StringBuilder clearBuilder = new StringBuilder();

		if (encryptedText != null && secretKey != null) {
			char[] clearTextArray = encryptedText.toCharArray();
			char[] secretKeyArray = secretKey.toCharArray();

			int keyIndex = 0;
			int max = secretKeyArray.length;

			for (char clearLetter : clearTextArray) {
				if (clearLetter >= 'A' && clearLetter <= 'z') {
					clearBuilder.append(Vigenere.getClearTextChar(clearLetter, secretKeyArray[keyIndex]));
					keyIndex = (keyIndex + 1) % max;
				} else {
					clearBuilder.append(clearLetter);
					//bei nicht erlaubten Buchstaben wird der Schluessel nicht verwendet, der Index muss daher nicht
					//angehoben werden
				}
			}
		}

		return clearBuilder.toString();
	}

	/**
	 * Entschlüsselt den Dateiinhalt, der zum übergebenen Dateipfad gehört, mit dem gegebenen Schlüssel.
	 *
	 * @param filePath der Pfad zur Datei, die entschlüsselt werden soll
	 * @param secretKey geheimer Schlüssel
	 * @throws FileAlreadyExistsException Wird geworfen, falls die .dec-Datei schon existiert
	 */
	public static void decryptFile(String filePath, String secretKey) throws FileAlreadyExistsException {
		Path path = Paths.get(filePath);
		if (Files.isDirectory(path) || !Files.exists(path)) { //pruefe, ob parameter eine datei ist und existiert
			System.out.println("File does not exist.");
			System.exit(1);
		} else {

			if (new File(filePath + Vigenere.SUFFIX_DEC).exists()) {
				throw new FileAlreadyExistsException("Die Datei " + filePath + Vigenere.SUFFIX_DEC +
						" existiert bereits!");
			}
			BufferedWriter bufferedWriter = null;
			BufferedReader bufferedReader = null;
			try {
				File verschluesselteDatei = Files.createFile(Paths.get(filePath + Vigenere.SUFFIX_DEC)).toFile();
				bufferedWriter = new BufferedWriter(new FileWriter(verschluesselteDatei));
				bufferedReader = new BufferedReader(new FileReader(filePath));
				String zeile;
				StringBuilder klartext = new StringBuilder();
				StringBuilder entschluesselterInhalt= new StringBuilder();

				while ((zeile = bufferedReader.readLine()) != null) {
					klartext.append(zeile).append("\n");
				}

				//entschluessele dateiinhalt
				entschluesselterInhalt.append(Vigenere.decryptText(klartext.toString(), secretKey));

				//schreibe entschluesselten inhalt in datei
				bufferedWriter.write(entschluesselterInhalt.toString());
				bufferedWriter.flush();

				//schliesse alle streams
				bufferedReader.close();
				bufferedWriter.close();

			} catch (IOException e) {
				System.err.println("Beim Bearbeiten der Dateien ist ein Fehler aufgetreten.");
				//Logausgabe
				// e.printStackTrace();
			} finally {
				try {
					if (bufferedReader != null) {
						bufferedReader.close();
					}
					if (bufferedWriter != null) {
						bufferedWriter.close();
					}
				} catch (IOException e) {
					System.err.println("Beim Bearbeiten der Dateien ist ein Fehler aufgetreten.");
					//Logausgabe
					// e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Hilfsmethode zum Ermitteln des Matrix-Indexes (Wert 0 - 25)
	 * @param keyChar Buchstabe, zu dem der Index in der Matrix ermittelt werden soll
	 * @return int Index des Buchstaben in der Matrix
	 */
	private static int ermittleMatrixIndex(char keyChar) {
		return (int) Character.toUpperCase(keyChar) - (int) 'A';
	}

	/**
	 * Debug only
	 */
	private static void print() {
        for (int i = 0; i < vigenereMatrix.length; i++) {
            for (int j = 0; j < vigenereMatrix.length; j++) {
                System.out.print(vigenereMatrix[i][j]);
            }
            System.out.println();
        }
    }
}
