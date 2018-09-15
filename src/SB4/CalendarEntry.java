package SB4;

import java.text.SimpleDateFormat;

public class CalendarEntry extends Entry {

	private boolean wholeDay;

	public CalendarEntry(String name, int year, int month, int day, int hour, int minute) {
		super(name, year, month, day, hour, minute);
	}

	public CalendarEntry(String name, int year, int month, int day) {
		super(name, year, month, day);
		this.wholeDay = true;
	}

	public boolean isWholeDay() {
		return this.wholeDay;
	}

	@Override
	public void print() {
		if (this.wholeDay) {
			System.out.println(
					new SimpleDateFormat("dd.MM.yyyy").format(super.getDate().getTime()) + ": " + super.getName());
		} else {
			System.out.println(new SimpleDateFormat("dd.MM.yyyy HH:mm").format(super.getDate().getTime()) + ": "
					+ super.getName());
		}
	}
}
