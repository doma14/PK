package SB4;

import java.text.SimpleDateFormat;

public class ToDoEntry extends Entry {

	private boolean done;

	public ToDoEntry(String name, int year, int month, int day) {
		super(name, year, month, day);
	}

	public void done() {
		this.done = true;
	}

	public void undone() {
		this.done = false;
	}

	@Override
	public void print() {
		if (this.done) {
			System.out.println("Done: " + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(super.getDate().getTime())
					+ ": " + super.getName());
		} else {
			System.out.println("ToDo: " + new SimpleDateFormat("dd.MM.yyyy HH:mm").format(super.getDate().getTime())
					+ ": " + super.getName());
		}
	}
}