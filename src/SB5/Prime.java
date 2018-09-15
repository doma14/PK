package SB5;

import java.util.ArrayList;
import java.util.List;

public class Prime {

	private List<Integer> primzahlen;
	private int groesstePrimzahl = 2;

	public Prime() {
		this.primzahlen = new ArrayList<Integer>();
		this.primzahlen.add(2);
		this.berechnePrimzahlen(100);
		this.groesstePrimzahl = primzahlen.get(primzahlen.size() - 1);
		// System.out.println("hoechstePrimzahl: " + groesstePrimzahl);
	}

	private void berechnePrimzahlen(int grenzwert) {
		int zahl = 2;
		boolean istPrim = true;

		if (grenzwert >= 0 && grenzwert <= Integer.MAX_VALUE) {
			for (int i = 2; i <= grenzwert; i++) {
				istPrim = true;
				// System.out.println("i: " + i);

				for (zahl = 2; zahl < Math.sqrt(i) + 1; zahl++) {
					// System.out.println("zahl: " + zahl);
					if (i % zahl == 0) {
						istPrim = false;
						break;
					}
				}

				if (istPrim) {
					if (!enthaelt(i)) {
						this.primzahlen.add(i);
						if (this.groesstePrimzahl < i) {
							this.groesstePrimzahl = i;
						}
					}
				}

			}
		}
	}

	private boolean enthaelt(int i) {
		// System.out.println("enthaelt i " + i);
		for (Integer in : this.primzahlen) {
			if (in.intValue() == i) {
				// System.out.println("ist enthalten");
				return true;
			}
		}
		return false;
	}

	// public static void main(String[] args) {
	// Prime p = new Prime();
	// System.out.println(p.isPrime(-10));
	// System.out.println(p.getPrime(-1));
	// System.out.println(p.primzahlen.size());
	// System.out.println(p.getPrime(25));
	// System.out.println(p.getPrime(Integer.MAX_VALUE));
	// System.out.println(p.getPrime(Integer.MIN_VALUE));
	// System.out.println(p.getPrime(0));
	// System.out.println(p.isPrime(Integer.MAX_VALUE + 1));
	//
	// }

	public boolean isPrime(int number) {
		boolean istPrim = false;

		if (number > groesstePrimzahl) {
			// System.out.println("number: " + number);
			// System.out.println("hP: " + groesstePrimzahl);
			berechnePrimzahlen(number);
			if (this.primzahlen.contains(number)) {
				istPrim = true;
			}
		} else {
			if (this.primzahlen.contains(number)) {
				istPrim = true;
			} else {
				istPrim = false;
			}
		}

		return istPrim;
	}

	public int getPrime(int n) {
		if (n < 0) {
			return 0;
		} else if (n >= 0 && n <= this.primzahlen.size() - 1) {
			return this.primzahlen.get(n);
		}
		return 0;
	}

}
