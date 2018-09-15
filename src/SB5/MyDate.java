package SB5;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class MyDate {

	public MyDate() {

	}

	public static boolean existsDate(int day, int month, int year) {
		boolean exists = false;

		try { // unschoen, da doppelt
			LocalDate localDate = LocalDate.of(year, month, day);
			exists = true;
		} catch (DateTimeException e) {
			exists = false;
		}

		return exists;
	}

	public static String weekDay(int day, int month, int year) {
		String wochentag = null;

		try {
			LocalDate localDate = LocalDate.of(year, month, day);
			wochentag = localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
			// Bearbeitung, damit String ersten Buchstaben als Grossbuchstaben
			// hat
			// wochentag = wochentag.substring(0, 1).toUpperCase() +
			// wochentag.substring(1).toLowerCase();
		} catch (DateTimeException e) {
			// in dem Fall ist das Datum ungueltig
			wochentag = null;
		}

		return wochentag;
	}

}
