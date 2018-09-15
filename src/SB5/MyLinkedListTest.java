package SB5;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import SB4.MyLinkedList;

public class MyLinkedListTest {

	private MyLinkedList<Integer> list;

	@Before
	public void setUp() throws Exception {
		list = new MyLinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testKonstruktor() {
		try {
			list = new MyLinkedList<Integer>();
		} catch (AssertionError e) {
			System.out.println("Der Konstruktor konnte nicht richtig aufgerufen werden: " + e.getMessage());
		}
		Assert.assertNotNull(list);
	}

	@Test
	public void testFirst() {
		list = new MyLinkedList<Integer>();

		try {
			assert list.first() == null;
		} catch (AssertionError e) {
			System.err.println("Das erste Listenelement sollte null sein (leere Liste), war: " + list.first());
		}

		/* Nehme an, dass #add funktioniert und durch testAdd getestet wurde */
		int value = 1;
		list.add(value);
		list.add(2);
		list.add(3);
		list.add(4);

		try {
			assert list.first() == value;
		} catch (AssertionError e) {
			System.err.println("Das erste Listenelement hätte den Wert " + value + " haben sollen, war: " + list.first()
					+ "\n" + e.getMessage());
		}
		Assert.assertTrue(list.first() == value);

		list.delete(1);

		value = 2;
		try {
			assert list.first() == value;
		} catch (AssertionError e) {
			System.err.println("Das erste Listenelement hätte den Wert " + value + " haben sollen, war: " + list.first()
					+ "\n" + e.getMessage());
		}
		Assert.assertTrue(list.first() == value);

		list.delete(2);
		list.delete(3);
		list.delete(4);

		Assert.assertTrue(list.isEmpty());
		/* testFirst bei leerer Liste */
		try {
			assert list.first() == null;
		} catch (AssertionError e) {
			System.err.println("Das erste Listenelement sollte null sein (leere Liste), war: " + list.first());
		}
	}

	@Test
	public void testLast() {
		list = new MyLinkedList<Integer>();

		/* Nehme an, dass #add funktioniert und durch testAdd getestet wurde */
		/*
		 * Nehme an, dass #delete funktioniert und durch testDelete getestet
		 * wurde
		 */

		try {
			assert list.last() == null;
		} catch (AssertionError e) {
			System.err.println("Das letzte Listenelement sollte null sein (leere Liste), war: " + list.last());
		}

		list.add(1);
		list.add(2);
		list.add(3);
		int value = 4;
		list.add(value);

		try {
			assert list.last() == value;
		} catch (AssertionError e) {
			System.err.println("Das letzte Listenelement hätte den Wert " + value + " haben sollen, war: " + list.last()
					+ "\n" + e.getMessage());
		}
		Assert.assertTrue(list.last() == value);

		list.delete(4);
		value = 3;

		try {
			assert list.last() == value;
		} catch (AssertionError e) {
			System.err.println("Das letzte Listenelement hätte den Wert " + value + " haben sollen, war: " + list.last()
					+ "\n" + e.getMessage());
		}
		Assert.assertTrue(list.last() == value);

		list.delete(3);
		list.delete(2);
		list.delete(1);

		try {
			assert list.last() == null;
		} catch (AssertionError e) {
			System.err.println("Das letzte Listenelement sollte null sein (leere Liste), war: " + list.last());
		}
	}

	@Test
	public void testSize() {
		list = new MyLinkedList<Integer>();
		/* Nehme an, dass #add funktioniert und durch testAdd getestet wurde */
		list.add(1);
		list.add(2);
		list.add(3);

		try {
			assert list.size() == 3;
		} catch (AssertionError e) {
			System.err.println(
					"Size hat den falschen Wert für die Größe der Liste ermittelt. Erwartet: 3, Ist: " + list.size());
		}
		Assert.assertTrue(list.size() == 3);
		list = new MyLinkedList<Integer>();

		try {
			assert list.size() == 0;
		} catch (AssertionError e) {
			System.err.println(
					"Size hat den falschen Wert für die Größe der Liste ermittelt. Erwartet: 0, Ist: " + list.size());
		}
		Assert.assertTrue(list.size() == 0);
	}

	@Test
	public void testDelete() {
		list = new MyLinkedList<Integer>();

		int value = 4;
		try {
			assert list.delete(4) == false;
		} catch (AssertionError e) {
			System.err.println(
					"Delete hat true zurückgegeben, obwohl die Liste leer ist: <" + value + ">\n" + e.getMessage());
		}

		/* Nehme an, dass #add funktioniert und durch testAdd getestet wurde */
		list.add(1);
		list.add(2);
		list.add(10);
		list.add(12);

		value = 2;
		try {
			assert list.delete(value) == true;
		} catch (AssertionError e) {
			System.err.println(
					"Delete hat false zurückgegeben, obwohl ein Wert gelöscht werden sollte, der in der Liste vorhanden ist: <"
							+ value + ">\n" + e.getMessage());
		}

		value = 3;
		try {
			assert list.delete(value) == false;
		} catch (AssertionError e) {
			System.err.println(
					"Delete hat true zurückgegeben, obwohl ein Wert gelöscht werden sollte, der nicht in der Liste enthalten ist: <"
							+ value + ">\n" + e.getMessage());
		}
	}

	@Test
	public void testIsEmpty() {
		list = new MyLinkedList<Integer>();

		try {
			assert list.isEmpty() == true;
		} catch (AssertionError e) {
			System.err.println("isEmpty gibt false zurück, obwohl die Liste leer ist.\n" + e.getMessage());
		}
		Assert.assertTrue(list.isEmpty());

		/* Nehme an, dass #add funktioniert und durch testAdd getestet wurde */
		list.add(1);

		try {
			assert list.isEmpty() == false;
		} catch (AssertionError e) {
			System.err.println("isEmpty gibt true zurück, obwohl die Liste Elemente besitzt.\n" + e.getMessage());
		}
		Assert.assertTrue(!list.isEmpty());

		/*
		 * Nehme an, dass #delete funktioniert und durch testDelete getestet
		 * wurde
		 */
		list.delete(1);

		try {
			assert list.isEmpty() == true;
		} catch (AssertionError e) {
			System.err.println("isEmpty gibt false zurück, obwohl die LIste leer ist.\n" + e.getMessage());
		}
		Assert.assertTrue(list.isEmpty());

	}

	@Test
	public void testAdd() {
		// Konstruktor hat eigenen Test, muss hier nicht erneut getestet werden
		list = new MyLinkedList<Integer>();
		try {
			list.add(1);
		} catch (Throwable e) {
			System.err.println("Beim Hinzufügen eines Listenelements ist ein Fehler aufgetreten: " + e.getMessage());
		}
		Assert.assertTrue(list.last() == 1);
		try {
			list.add(2);
		} catch (Throwable e) {
			System.err.println(
					"Beim Hinzufügen eines zweiten Listenelements ist ein Fehler aufgetreten: " + e.getMessage());
		}
		Assert.assertTrue(list.last() == 2);
		list.add(3);
		Assert.assertTrue(list.last() == 3);
		Assert.assertTrue(list.size() == 3);
	}

	@Test
	public void testGet() {
		list = new MyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		/* Nehme an, dass #add funktioniert und durch testAdd getestet wurde */
		int outOfRange = -1;
		try {
			assert list.get(outOfRange) == null;
			outOfRange = 5;
			assert list.get(outOfRange) == null;
		} catch (AssertionError e) {
			System.err.println(
					"Falscher Rückgabewert bei illegalem Eingabeparameter: <" + outOfRange + ">\n" + e.getMessage());
		}
		int inRange = 0;
		try {
			assert list.get(inRange) == 1;
			inRange = 4;
			assert list.get(inRange) == 5;
		} catch (AssertionError e) {
			System.err.println(
					"Falscher Rückgabewert bei korrektem Eingabeparameter: <" + inRange + ">\n" + e.getMessage());
		}
		Assert.assertTrue(list.get(-1) == null);
		Assert.assertTrue(list.get(0) == 1);
		Assert.assertTrue(list.get(5) == null);
	}

	@Test
	public void testAddAt() {
		list = new MyLinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		/* Nehme an, dass #add funktioniert und durch testAdd getestet wurde */
		/* Nehme an, dass #get funktioniert und durch testGet getestet wurde */

		int inRange = 5;
		int value = 500;
		try {
			assert list.addAt(value, inRange) == true;
		} catch (AssertionError e) {
			System.err.println("addAt gibt false zurück, obwohl ein korrekter Index angegeben wurde: <" + inRange
					+ ">\n" + e.getMessage());
		}
		try {
			assert list.get(inRange) == value;
		} catch (AssertionError e) {
			System.err.println("addAt hat nicht den richtigen Wert an der richtigen Stelle eingefügt! Soll: " + value
					+ " => Ist: " + list.get(inRange) + "\n" + e.getMessage());
		}

		inRange = 0;
		value = 200;
		try {
			assert list.addAt(value, inRange) == true;
		} catch (AssertionError e) {
			System.err.println("addAt gibt false zurück, obwohl ein korrekter Index angegeben wurde: <" + inRange
					+ ">\n" + e.getMessage());
		}
		try {
			assert list.get(inRange) == value;
		} catch (AssertionError e) {
			System.err.println("addAt hat nicht den richtigen Wert an der richtigen Stelle eingefügt! Soll: " + value
					+ " => Ist: " + list.get(inRange) + "\n" + e.getMessage());
		}

		int outOfRange = 9;
		value = 900;
		try {
			assert list.addAt(value, outOfRange) == false;
		} catch (AssertionError e) {
			System.err.println("addAt gibt true zurück, obwohl ein inkorrekter Index angegeben wurde: <" + outOfRange
					+ ">\n" + e.getMessage());
		}
	}

}
