package SB4;

public class Node<T> {

	private T value;
	private Node<T> next;

	public Node() {
		this.value = null;
		this.next = null;
	}

	public Node(T value, Node<T> next) {
		this.value = value;
		this.next = next;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public T getValue() {
		return this.value;
	}

	public void setNext(Node<T> node) {
		this.next = node;
	}

	public Node<T> getNext() {
		return this.next;
	}
}
