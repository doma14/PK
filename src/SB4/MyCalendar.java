package SB4;

import java.util.ArrayList;
import java.util.Collections;

public class MyCalendar {

	private ArrayList<Entry> entries = new ArrayList<Entry>();

	public MyCalendar() {
	}

	public void addEntry(Entry entry) {
		this.entries.add(entry);
	}

	public void printAll() {
		for (Entry e : entries) {
			e.print();
		}
	}

	public void printCalendar() {
		for (Entry e : entries) {
			if (e instanceof CalendarEntry) {
				e.print();
			}
		}
	}

	public void printToDo() {
		for (Entry e : entries) {
			if (e instanceof ToDoEntry) {
				e.print();
			}
		}
	}

	public void sort() {
		Collections.sort(entries);
	}
}
