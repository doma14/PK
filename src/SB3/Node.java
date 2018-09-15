package SB3;

public class Node {
	
	private int value;
	private Node next;
	
	public Node() {
		this.value = 0;
		this.next = null;
	}
	
	public Node(int value, Node next) {
		this.value = value;
		this.next = next;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setNext(Node node) {
		this.next = node;
	}
	
	public Node getNext() {
		return this.next;
	}	
}
