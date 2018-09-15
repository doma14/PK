package SB3;

public class MyLinkedList {
	private Node head;
	
	public MyLinkedList() {
		
	}
	
	public int get(int index) {
		int value = -1;
		Node n = null;
		
		if (index >= 0) {
			n = this.head;
			while (index > 0) {
				if (n.getNext() != null) {
					n = n.getNext();
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
	
	public void add(int value) {
		if (this.head == null) {
			this.head = new Node(value, null);
		} else {
			Node n = this.head;
			while (n.getNext() != null) {
				n = n.getNext();
			}
			n.setNext(new Node(value, null));
		}
	}

	public boolean delete(int value) {
		boolean deleted = false;
		Node n = null;
		
		if (this.head != null) {
			n = this.head;
			if (this.head.getValue() == value) {
				this.head = n.getNext();
				n = null;
				deleted = true;
			} else {
				
				while (n.getNext() != null && n.getNext().getValue() != value) {
					n = n.getNext();
				}
				if (n.getNext() == null) {
					//wert nicht gefunden, mache nichts
				} else if (n.getNext().getValue() == value) {
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
			Node n = this.head;
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
	
	public int first() {
		int value = -1;
		
		if (this.head != null) {
			value = this.head.getValue();
		}
		
		return value;
	}
	
	public int last() {
		Node n = null;
		int value = -1;
		if (this.head != null) {
			n = this.head;
			while (n.getNext() != null) {
				n = n.getNext();
			}
			value = n.getValue();
		}
		
		return value;
	}
	
	public boolean addAt(int value, int index) {
		boolean success = false;
		Node neu = new Node();
		Node prev = null;
		neu.setValue(value);
		Node n = null;
				
		if (index >= 0) {
			if (this.head != null) {
				
				if (index == 0) { //anstelle von head einfuegen
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
							prev = n; //ist dann die letzte Node
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
	
	
	/*public void print() {
		Node n = null;
		if (this.head != null) {
			n = this.head;
			while (n.getNext() != null) {
				System.out.println("Node mit Wert: " + n.getValue());
				n = n.getNext();
			}
			System.out.println("Node mit Wert: " + n.getValue());
		}
	}*/
	
}
