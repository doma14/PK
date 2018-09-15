package SB8;

/**
 * Klasse zum Speichern der Knoten inklusive der Kindknoten und des Elternknotens.
 * @param <T> Generischer Wert des Knotens
 */
public class Node<T extends Comparable<T>>  {

	/**
	 * Wert des Knotens
	 */
	private T value;
	/**
	 * Linker Kindknoten
	 */
	private Node left;
	/**
	 * Rechter Kindknoten
	 */
	private Node right;
	/**
	 * Elternknoten
	 */
	private Node parent;
	/**
	 * Balancefaktor = (Höhe des rechten Teilbaums einer Node) - (Höhe des linken Teilbaums einer Node)
	 */
	private int balanceFaktor;

	/**
	 * Konstruktor
	 * @param value Wert des Knotens
	 */
	public Node(T value) {
		this.value = value;
	}

	/* Getter und Setter */
	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public T getValue() {
		return this.value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public int getBalanceFaktor() {
		return balanceFaktor;
	}

	public void setBalanceFaktor(int balanceFaktor) {
		this.balanceFaktor = balanceFaktor;
		//TODO hier Logik fuer Rotation?
	}
}
