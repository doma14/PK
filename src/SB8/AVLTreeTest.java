package SB8;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {

	private AVLTree<Integer> avlTree = new AVLTree<>();

	@Before
	public void setup() {

		// 5 4
		// / \
		// 2 9 2 3
		// / \ /
		// 1 4 8 1 1 2
		// /
		// 6 1

		this.avlTree.add(5);
		this.avlTree.add(9);
		this.avlTree.add(2);
		this.avlTree.add(4);
		this.avlTree.add(8);
		this.avlTree.add(1);
		this.avlTree.add(6);
	}

	@Test
	public void testKonstruktor() {

		AVLTree tree = new AVLTree();
		Node<Integer> root = new Node<>(1);
	}

	@Test
	public void testPrint() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.add(6);
		tree.add(8);
		tree.add(1);
		tree.add(19);
		tree.add(7);

		tree.printTree(tree.getRoot());
		System.out.println("Anzahl: " + tree.size());
	}

	@Test
	public void testSearch() {
		System.out.println(this.avlTree.search(3));
	}

	@Test
	public void testAddSameElements() {
		AVLTree<Integer> tree = new AVLTree<>();
		tree.add(1);
		tree.add(1);
		tree.printTree(tree.getRoot());
	}

	@Test
	public void testHoehe() {
		Node<Integer> node = this.avlTree.getNodeToValue(4);
		System.out.println(this.avlTree.berechneHoehe(node));

		node = this.avlTree.getNodeToValue(5);
		System.out.println(this.avlTree.berechneHoehe(node));

		node = this.avlTree.getNodeToValue(9);
		System.out.println(this.avlTree.berechneHoehe(node));

		node = this.avlTree.getNodeToValue(8);
		System.out.println(this.avlTree.berechneHoehe(node));
	}

	@Test
	public void testBalance() {
		Node<Integer> node = this.avlTree.getNodeToValue(4);
		System.out.println(this.avlTree.berechneBalancefaktor(node));

		node = this.avlTree.getNodeToValue(8);
		System.out.println(this.avlTree.berechneBalancefaktor(node));

		node = this.avlTree.getNodeToValue(2);
		System.out.println(this.avlTree.berechneBalancefaktor(node));

		node = this.avlTree.getNodeToValue(9);
		System.out.println(this.avlTree.berechneBalancefaktor(node));

		node = this.avlTree.getNodeToValue(5);
		System.out.println(this.avlTree.berechneBalancefaktor(node));

		System.out.println("------------------\n");
		this.avlTree.printTree(this.avlTree.getRoot());
	}

	@Test
	public void testInfix() {
		List<Integer> infix = this.avlTree.getInfix();
		for (Integer i : infix) {
			System.out.printf(i.toString());
		}
	}

}
