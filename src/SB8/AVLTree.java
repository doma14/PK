package SB8;

import java.util.ArrayList;
import java.util.List;

/**
 * Diese Klasse dient zum Verwalten eines AVL-Baumes.
 * @param <T> generischer Parameter
 */
public class AVLTree<T extends Comparable<T>> {

	/**
	 * Wurzelelement des AVL-Baumes
	 */
	private Node<T> root;

	/**
	 * Enthält die Anzahl der Elemente des Baumes
	 */
	private int anzahlWerte;

	/**
	 * Standardkonstruktor
	 */
	public AVLTree() {

	}

	/**
	 * Gibt das Wurzelelement zurück.
	 *
	 * @return <code>Node</code> Wurzelelement
	 */
	public Node<T> getRoot() {
		return this.root;
	}

	/**
	 * Methode zum Hinzufügen eines Elements in den AVL-Baum.
	 *
	 * @param value Hinzuzufügender Wert
	 */
	public void add(T value) {
		if (this.root == null) {
			this.root = new Node<>(value);
			anzahlWerte++;
		} else {
			if (insert(this.root, value)) {
				anzahlWerte++;
			}
			berechneBalanceBaum();
		}
	}

	private void berechneBalanceBaum() {
		if (this.root != null) {
			setzeBalanceAnKnoten(this.root);
		}
	}

	private void setzeBalanceAnKnoten(Node<T> node) {
		if (node != null) {
			node.setBalanceFaktor(berechneBalancefaktor(node));
			setzeBalanceAnKnoten(node.getLeft());
			setzeBalanceAnKnoten(node.getRight());
		}
	}


	/**
	 * Interne Methode zum rekursiven Hinzufügen eines Wertes in den AVL-Baum.
	 *
	 * @param node aktueller Knoten
	 * @param value hinzuzufügender Wert
	 */
	private boolean insert(Node<T> node, T value) {
		boolean hinzugefuegt = false;
		Node<T> neueNode = new Node<>(value);
		if (value.compareTo(node.getValue()) < 0) {
			/*falls der Wert kleiner ist, als der Wert des aktuell betrachteten Knotens,
			muss der neue Wert im linken Teilbaum hinzugefügt werden*/
			if (node.getLeft() == null) {
				node.setLeft(neueNode);
				neueNode.setBalanceFaktor(berechneBalancefaktor(neueNode));
				hinzugefuegt = true;
			} else {
				hinzugefuegt = insert(node.getLeft(), value);
			}
		} else if (value.compareTo(node.getValue()) > 0){
			/*falls der Wert groesser ist, als der Wert des aktuell betrachteten Knotens,
			muss der neue Wert im rechten Teilbaum hinzugefügt werden*/
			if (node.getRight() == null) {
				node.setRight(neueNode);
				neueNode.setBalanceFaktor(berechneBalancefaktor(neueNode));
				hinzugefuegt = true;
			} else {
				hinzugefuegt = insert(node.getRight(), value);
			}
		} else {
			System.out.println("Wert ist gleich!");
		}

		return hinzugefuegt;
	}

	public void delete(T value) {

	}

	/**
	 * Methode zum Suchen eines Wertes in einem Baum.
	 *
	 * @param value Zu suchender Wert
	 * @return <code>true</code>, wenn der gesuchte Wert im Baum enthalten ist, <code>false</code> sonst
	 */
	public boolean search(T value) {
		boolean vorhanden = false;

		vorhanden = sucheWert(value, this.root);

		return vorhanden;
	}

	/**
	 * Sucht rekursiv, ob ein Wert im Baum enthalten ist.
	 *
	 * @param value zu suchender Wert
	 * @param node aktueller Knoten, der untersucht werden soll
	 * @return <code>true</code>, wenn der gesuchte Wert im Baum enthalten ist, <code>false</code> sonst
	 */
	private boolean sucheWert(T value, Node<T> node) {
		boolean vorhanden = false;

		if (node != null) {
			if (node.getValue() == value) { //vergleiche, ob wert in aktuellem knoten enthalten ist
				vorhanden = true;
			} else {
				if (value.compareTo(node.getValue()) < 0) { //wert ist kleiner, also muss es im linken teilbaum sein
					vorhanden = sucheWert(value, node.getLeft());
				} else { // wert ist groesser, also muss es im rechten teilbaum sein
					vorhanden = sucheWert(value, node.getRight());
				}
			}
		}

		return vorhanden;
	}

	/**
	 * Ermittelt den Knoten zu einem bestimmten Wert.
	 * @param value Wert, zu dem der Knoten gesucht werden soll
	 * @return Der Knoten des Wertes oder null
	 */
	public Node<T> getNodeToValue(T value) {
		Node<T> knoten = null;

		knoten = sucheNode(value, this.root);

		return knoten;
	}


	/**
	 * Sucht rekursiv, ob ein Wert im Baum enthalten ist und liefert den dazugehörigen Knoten zurück.
	 *
	 * @param value zu suchender Wert
	 * @param node aktueller Knoten, der untersucht werden soll
	 * @return <code>true</code>, wenn der gesuchte Wert im Baum enthalten ist, <code>false</code> sonst
	 */
	private Node<T> sucheNode(T value, Node<T> node) {
		Node<T> knoten = null;

		if (node != null) {
			if (node.getValue() == value) { //vergleiche, ob wert in aktuellem knoten enthalten ist
				knoten = node;
			} else {
				if (value.compareTo(node.getValue()) < 0) { //wert ist kleiner, also muss es im linken teilbaum sein
					knoten = sucheNode(value, node.getLeft());
				} else { // wert ist groesser, also muss es im rechten teilbaum sein
					knoten = sucheNode(value, node.getRight());
				}
			}
		}

		return knoten;
	}


	public int size() {
		return this.anzahlWerte;
	}

	public int height() {
		return 0;
	}


	/**
	 * Methode zum Berechnen der Höhe eines Teilbaums.
	 * @param knoten Teilbaum
	 * @return Höhe des Teilbaums
	 */
	public int berechneHoehe(Node<T> knoten) {
		int maxLeft = 0;
		int maxRight = 0;

		if (knoten == null) {
			return 0;
		} else if (knoten.getLeft() == null && knoten.getRight() == null) {
			return 1;
		} else {
			maxLeft = berechneHoehe(knoten.getLeft());
			maxRight = berechneHoehe(knoten.getRight());
		}

		return (maxLeft >= maxRight) ? (maxLeft + 1) : (maxRight + 1);
	}


	/**
	 * Methode zum Berechnen des Balancefaktors eines Knotens. Dieser berechnet sich aus der Differenz
	 * der Höhe des rechten Teilbaums und der Höhe des linken Teilbaums.
	 * @return Balancefaktor des Knotens
	 */
	public int berechneBalancefaktor(Node<T> knoten) {
		return berechneHoehe(knoten.getRight()) - berechneHoehe(knoten.getLeft());
	}

	/**
	 * Gibt die Werte des Baums in Infix-Notation in einer Liste zurück.
	 *
	 * @return <code>List<T></code> Liste der Elemente in der Reihenfolge der Infix-Notation
	 */
	public List<T> getInfix() {
		List<T> liste = new ArrayList<>();
		fuelleInfix(liste, getRoot());
		return liste;
	}

	/**
	 * Private Methode zum Fuellen der Infix-Liste.
	 *
	 * @param liste zu fuellende Liste
	 * @param knoten aktueller Knoten
	 */
	private void fuelleInfix(List<T> liste, Node<T> knoten) {
		if (knoten != null) {
			fuelleInfix(liste, knoten.getLeft());
			liste.add(knoten.getValue());
			fuelleInfix(liste, knoten.getRight());
		}
	}

	/**
	 * Gibt die Werte des Baums in Prefix-Notation in einer Liste zurück.
	 *
	 * @return <code>List<T></code> Liste der Elemente in der Reihenfolge der Prefix-Notation
	 */
	public List<T> getPrefix() {
		List<T> liste = new ArrayList<>();
		fuellePrefix(liste, getRoot());
		return liste;
	}

	/**
	 * Private Methode zum Fuellen der Prefix-Liste.
	 *
	 * @param liste zu füllende Liste
	 * @param knoten aktueller Knoten
	 */
	private void fuellePrefix(List<T> liste, Node<T> knoten) {
		if (knoten != null) {
			liste.add(knoten.getValue());
			fuellePrefix(liste, knoten.getLeft());
			fuellePrefix(liste, knoten.getRight());
		}
	}

	/**
	 * Gibt die Werte des Baums in Postfix-Notation in einer Liste zurück.
	 *
	 * @return <code>List<T></code> Liste der Elemente in der Reihenfolge der Postfix-Notation
	 */
	public List<T> getPostfix() {
		List<T> liste = new ArrayList<>();
		fuellePostfix(liste, getRoot());
		return liste;
	}


	private void fuellePostfix(List<T> liste, Node<T> knoten) {
		if (knoten != null) {
			fuellePostfix(liste, knoten.getLeft());
			fuellePostfix(liste, knoten.getRight());
			liste.add(knoten.getValue());
		}
	}

	/**
	 * Private Methode zum Ausgeben des Baums
	 * @param node Startnode
	 */
	public void printTree(Node<T> node) {
		if (node == null) {
			System.out.println("-");
		} else {
			printTree(node.getLeft());
			System.out.println("[" + node.getValue() + ", " + node.getBalanceFaktor() + "]");
			printTree(node.getRight());
		}
	}
}
