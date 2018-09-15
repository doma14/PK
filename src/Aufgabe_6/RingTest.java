package Aufgabe_6;

import org.junit.Assert;
import org.junit.Test;

public class RingTest {

	private Ring ring;

	@Test
	public void testKonstruktorFalscheParameter() {
		Ring ring = new Ring(-20);
		ring = new Ring(0);
	}

	@Test
	public void testLeererRing() {
		this.ring = new Ring(2);
		Assert.assertTrue("Erwarte leeren Ring, aber hatte " + this.ring.size()
				+ " Element(e).", this.ring.size() == 0);
	}

	@Test
	public void testPush() {
		this.ring = new Ring(2);
		ring.push(1);
		Assert.assertTrue("Erwarte Ring der Groesse 1, aber war " + ring.size(),
				ring.size() == 1);
		for (int i = 2; i <= 100; i++) { //fuege 99 Elemente hinzu
			ring.push(i);
		}
		Assert.assertTrue("Erwarte Ring der Groesse 100, aber war " + ring.size(),
				ring.size() == 100);
	}

	@Test
	public void testPopLeererRing() {
		this.ring = new Ring(2);
		Integer ergebnis = this.ring.pop();
		Assert.assertTrue("Erwarte Null bei leerem Ring, aber war " + ergebnis,
				ergebnis == null);
	}

	@Test
	public void testPopNichtleererRing() {
		this.ring = new Ring(2);
		Integer ergebnis;
		this.ring.push(1);
		this.ring.push(2);
		this.ring.push(3);
		ergebnis = this.ring.pop();
		Assert.assertEquals("Erwarte Wert 1, aber war " + ergebnis,
				ergebnis, Integer.valueOf(1));
		ergebnis = this.ring.pop();
		Assert.assertEquals("Erwarte Wert 2, aber war " + ergebnis,
				ergebnis, Integer.valueOf(2));
		ergebnis = this.ring.pop();
		Assert.assertEquals("Erwarte Wert 3, aber war " + ergebnis,
				ergebnis, Integer.valueOf(3));
		Assert.assertEquals("Erwarte leeren Ring, aber hatte noch " + this.ring.size() +
								" Element(e)", Integer.valueOf(0), Integer.valueOf(this.ring.size()));
		ergebnis = this.ring.pop();
		Assert.assertTrue("Pop sollte kein Element mehr zurueckliefern.",
				ergebnis == null);
	}

	@Test
	public void testSizeLeererRing() {
		this.ring = new Ring(2);
		Assert.assertEquals("Fehler bei size von leerem Ring.",
				0, this.ring.size());
		this.ring.push(1);
		this.ring.pop();
		Assert.assertEquals("Fehler bei size von leerem Ring nach Einfuegen und " +
						"Entfernen eines Wertes.",0, this.ring.size());
	}

	@Test
	public void testSizeNichtleererRing() {
		this.ring = new Ring();
		for (int i = 0; i < 100; i++) {
			this.ring.push(i);
		}
		Assert.assertEquals("Fehler bei size nach Einfuegen von 100 Werten.",
				100, this.ring.size());
	}
}
