package SB4;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Entry implements Comparable<Entry> {
	private GregorianCalendar date;
	private String name;

	public Entry(String name, int year, int month, int day, int hour, int minute) {
		this.name = name;
		this.date = new GregorianCalendar(year, month, day, hour, minute);
	}

	public Entry(String name, int year, int month, int day) {
		this.name = name;
		this.date = new GregorianCalendar(year, month, day);
	}

	public void setDate(int year, int month, int day) {
		this.date = new GregorianCalendar(year, month, day);
	}

	public void setTime(int hour, int minute) {
		this.date.set(this.date.get(Calendar.YEAR), this.date.get(Calendar.MONTH), this.date.get(Calendar.DAY_OF_MONTH),
				hour, minute);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public GregorianCalendar getDate() {
		return this.date;
	}

	public abstract void print(); // dd.MM.yyyy HH:mm: name

	@Override
	public int compareTo(Entry entry) {
		return this.date.compareTo(entry.date);
	}
}