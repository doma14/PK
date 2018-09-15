package SB2;

public class Room {

	public String name;
	public int height;
	public int width;
	public int length;
	public boolean hasWindow;

	public Room() {
		this.name = "";
		this.height = 0;
		this.width = 0;
		this.length = 0;
		this.hasWindow = false;
	}

	public Room(String name, int height, int width, int length, boolean hasWindow) {
		super();
		this.name = name;
		this.height = height;
		this.width = width;
		this.length = length;
		this.hasWindow = hasWindow;
	}

	public static int calcArea(int length, int width, int height) {
		int area = 0;
		int bodenUndDecke = 0;
		int waende = 0;

		if (length >= 0 && width >= 0) {
			bodenUndDecke = 2 * length * width;
		}

		if (length >= 0 && width >= 0 && height >= 0) {
			waende = 2 * length * height + 2 * width * height;
		}

		area = bodenUndDecke + waende;

		return area;
	}

	public static int calcVolume(int length, int width, int height) {
		int erg = 0;

		if (length >= 0 && width >= 0 && height >= 0) {
			erg = length * height * width;
		}

		return erg;
	}

	public int getArea() {
		return Room.calcArea(length, width, height);
	}

	public int getVolume() {
		return Room.calcVolume(length, width, height);
	}

	public String getStatus() {
		return "name: " + this.name + "; length: " + this.length + "cm; width: " + this.width + "cm; height: " + this.height + "cm; hasWindow: " + this.hasWindow;
	}
}
