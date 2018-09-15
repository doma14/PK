package SB7;


import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class VigenereTest {

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();

	@Test
	public void testGetCipher() {
		Assert.assertTrue("Erwarte 'N'", 'N' == Vigenere.getCipher('H', 'g'));
		Assert.assertTrue("Erwarte 'n'", 'n' == Vigenere.getCipher('h', 'g'));
	}

	@Test
	public void testEncryptText() {
		String clearText = "Hello, world!";
		String key = "cipherc";
		String cipherText = Vigenere.encryptText(clearText, key);
		Assert.assertTrue("Erwarte 'Jmass, nqtts!, aber war: " + cipherText, "Jmass, nqtts!".equals(cipherText));
		cipherText = Vigenere.encryptText(null, null);
		Assert.assertTrue("Erwarte leeren String, aber war: " + cipherText, "".equals(cipherText));

		cipherText = Vigenere.encryptText("theredfoxtrotsquietlyatmidnight", "bond");
		Assert.assertTrue("Erwarte uvrufrsryherugdxjsgozogpjralhvg String, aber war: " + cipherText, "uvrufrsryherugdxjsgozogpjralhvg".equals(cipherText));

		cipherText = Vigenere.encryptText("murderontheorientexpress", "train");
		Assert.assertTrue("Erwarte flrlrkfnbuxfrqrgkefckvsa String, aber war: " + cipherText, "flrlrkfnbuxfrqrgkefckvsa".equals(cipherText));

		cipherText = Vigenere.encryptText("themolessnuckintothegardenlastnight", "garden");
		Assert.assertTrue("Erwarte zhvpsyksjqypqiewsgnexdvqkncdwgtixkx String, aber war: " + cipherText, "zhvpsyksjqypqiewsgnexdvqkncdwgtixkx".equals(cipherText));

	}

	@Test
	public void testEncryptTextFalseParameters() {
		String clearText = null;
		String key = null;
		String cipherText = Vigenere.encryptText(clearText, key);
		Assert.assertTrue("Erwarte leeren String, aber war: " + cipherText, "".equals(cipherText));
	}


	@Test
	public void testEncryptFileAndDelete() {
		String testFile = "C:/tmp/testdelete.txt";
		try {
			Vigenere.encryptFile(testFile, "geheim");
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}
		File file = new File(testFile + ".enc");
		try {
			Files.delete(Paths.get(file.toURI()));
		} catch (IOException e) {
			System.out.println("Datei konnte nicht gelöscht werden.");
		}
	}

	@Test
	public void testEncryptFile() {
		String testFile = "C:/tmp/test.txt";
		try {
			Vigenere.encryptFile(testFile, "geheim");
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = FileAlreadyExistsException.class)
	public void testEncryptFileException() throws FileAlreadyExistsException {
		String testFile = "C:/tmp/testexception.txt";
		Vigenere.encryptFile(testFile, "geheim");
	}

	@Test
	public void testEncryptFileDoesNotExist() {
		exit.expectSystemExitWithStatus(1);
		String testFile = "C:/tmp/notthere.txt";
		try {
			Vigenere.encryptFile(testFile, "geheim");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGetClearTextCipher() {
		char clearChar = Vigenere.getClearTextChar('R', 'i');
		Assert.assertTrue("Erwarte 'J', aber war: " + clearChar, 'J' == clearChar);
		clearChar = Vigenere.getClearTextChar('r', 'i');
		Assert.assertTrue("Erwarte 'j', aber war: " + clearChar, 'j' == clearChar);
	}

	@Test
	public void testDecryptText() {
		String cipherText = "K ib h tiqizptqvt!";
		String key = "cipherc";
		String clearText = Vigenere.decryptText(cipherText, key);
		Assert.assertTrue("Erwarte 'I am a programmer!', aber war: " + clearText, "I am a programmer!".equals(clearText));
		/*	cloak klatrgafedvtssdwywcyty
			python pjphmfamhrcaifxifvvfmzwqtmyswst
			moore rcfpsgfspiecbcc*/
		cipherText = "klatrgafedvtssdwywcyty";
		key = "cloak";
		clearText = Vigenere.decryptText(cipherText, key);
		Assert.assertTrue("Erwarte 'iamtheprettiestunicorn', aber war: " + clearText, "iamtheprettiestunicorn".equals(clearText));
		cipherText = "pjphmfamhrcaifxifvvfmzwqtmyswst";
		key = "python";
		clearText = Vigenere.decryptText(cipherText, key);
		Assert.assertTrue("Erwarte 'alwayslookonthebrightsideoflife', aber war: " + clearText, "alwayslookonthebrightsideoflife".equals(clearText));
		cipherText = "rcfpsgfspiecbcc";
		key = "moore";
		clearText = Vigenere.decryptText(cipherText, key);
		Assert.assertTrue("Erwarte 'foryoureyesonly', aber war: " + clearText, "foryoureyesonly".equals(clearText));
	}

	@Test
	public void testDecryptTextFalseParameters() {
		String cipherText = null;
		String key = null;
		String clearText = Vigenere.decryptText(cipherText, key);
		Assert.assertTrue("Erwarte leeren String, aber war: " + clearText, "".equals(clearText));
	}


	@Test
	public void testDecryptFile() {
		String testFile = "C:/tmp/testdec.txt";
		try {
			Vigenere.decryptFile(testFile, "cipherc");
		} catch (FileAlreadyExistsException e) {
			e.printStackTrace();
		}
	}

}
