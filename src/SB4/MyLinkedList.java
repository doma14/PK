package SB4;

public class MyLinkedList<T> {
	private Node<T> head;

	public T get(int index) {
		T value = null;
		Node<T> n = null;

		if (index >= 0) {
			n = this.head;
			while (index > 0) {
				if (n.getNext() != null) {
					if (n.getNext() instanceof Node) {
						n = n.getNext();
					}
				} else {
					break;
				}
				index--;
			}
			if (index == 0 && n != null) {
				value = n.getValue();
			}
		}

		return value;
	}

	public void add(T value) {
		if (this.head == null) {
			this.head = new Node<T>(value, null);
		} else {
			Node<T> n = this.head;
			while (n.getNext() != null) {
				n = n.getNext();
			}
			n.setNext(new Node<T>(value, null));
		}
	}

	public boolean delete(T value) {
		boolean deleted = false;
		Node<T> n = null;

		if (this.head != null) {
			n = this.head;
			if (this.head.getValue() == value) {
				this.head = n.getNext();
				n = null;
				deleted = true;
			} else {

				while (n.getNext() != null && !n.getNext().getValue().equals(value)) {
					n = n.getNext();
				}
				if (n.getNext() == null) {
					// wert nicht gefunden, mache nichts
				} else if (n.getNext().getValue().equals(value)) {
					n.setNext(n.getNext().getNext());
					deleted = true;
				}

			}
		}
		return deleted;
	}

	public int size() {
		int size = 0;

		if (this.head != null) {
			Node<T> n = this.head;
			size++;
			while (n.getNext() != null) {
				size++;
				n = n.getNext();
			}
		}

		return size;
	}

	public boolean isEmpty() {
		boolean empty = false;

		if (this.head == null) {
			empty = true;
		}

		return empty;
	}

	public T first() {
		T value = null;

		if (this.head != null) {
			value = this.head.getValue();
		}

		return value;
	}

	public T last() {
		Node<T> n = null;
		T value = null;
		if (this.head != null) {
			n = this.head;
			while (n.getNext() != null) {
				n = n.getNext();
			}
			value = n.getValue();
		}

		return value;
	}

	public boolean addAt(T value, int index) {
		boolean success = false;
		Node<T> neu = new Node<T>();
		Node<T> prev = null;
		neu.setValue(value);
		Node<T> n = null;

		if (index >= 0) {
			if (this.head != null) {

				if (index == 0) { // anstelle von head einfuegen
					neu.setNext(this.head);
					this.head = neu;
					success = true;
				} else {

					n = this.head;
					while (index > 0) {
						index--;
						if (n.getNext() != null) {
							prev = n;
							n = n.getNext();
						} else {
							prev = n; // ist dann die letzte Node
							n = null;
							break;
						}
					}
					if (index == 0) {
						if (prev != null) {
							prev.setNext(neu);
							neu.setNext(n);
							success = true;
						}
					}
				}
			}

		}

		return success;
	}

	/*
	 * public void print() { Node<T> n = null; if (this.head != null) { n =
	 * this.head; while (n.getNext() != null) {
	 * System.out.println("Node mit Wert: " + n.getValue()); n = n.getNext(); }
	 * System.out.println("Node mit Wert: " + n.getValue()); } }
	 */

}
