package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {


	/*

	https://www.hackerrank.com/challenges/30-binary-search-trees/tutorial


	Binary Search Tree is a node-based binary tree data structure which has the following properties:

The left subtree of a node contains only nodes with keys lesser than the node’s key.
The right subtree of a node contains only nodes with keys greater than the node’s key.
The left and right subtree each must also be a binary search tree.


https://www.hackerrank.com/challenges/30-binary-trees/tutorial

Tree Traversal
A traversal of some binary tree, t, is an algorithm that iterates through each node in t exactly 1 time.

	 */

	static class Node {
		Node left, right;
		int data;

		Node(int data) {
			this.data = data;
			left = right = null;
		}

	}

	static int getHeight(Node root) {
		if (root == null) {
			return -1;
		}

		return 1 + Math.max(getHeight(root.left), getHeight(root.right));

	}

	static Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	private Node generateBST(int[] data) {

		Node root = null;

		for (int item : data) {
			root = insert(root, item);
		}

		return root;
	}

	@Test
	void getHeightBST_rootNull() {
		assertEquals(-1, getHeight(null));
	}

	@Test
	void getHeightBST_leaf() {
		assertEquals(0, getHeight(generateBST(new int[] { 3 })));
	}

	@Test
	void getHeightBST() {
		Node root = generateBST(new int[] { 3, 5, 2, 1, 4, 6, 7 });

		assertEquals(3, getHeight(root));
	}

	/**
	 * A level-order traversal of tree t is a recursive algorithm that processes the root,
	 * followed by the children of the root (from left to right),
	 * followed by the grandchildren of the root (from left to right).
	 * Because a level-order traversal goes level-by-level, it's also known as a breadth-first-search (BFS).
	 *
	 * @param root first node of the BST
	 */
	static Queue<Node> levelOrder(Node root) {
		Queue<Node> queue = new LinkedList<>();
		Queue<Node> resultQeue = new LinkedList<>();

		if (root != null) {
			// enqueue current root
			queue.add(root);
			resultQeue.add(root);

			// while there are nodes to process
			while (!queue.isEmpty()) {
				// dequeue next node
				Node tree = queue.poll();

				// enqueue child elements from next level in order
				if (tree.left != null) {
					queue.add(tree.left);
					resultQeue.add(tree.left);
				}
				if (tree.right != null) {
					queue.add(tree.right);
					resultQeue.add(tree.right);
				}
			}
		}

		//		resultQeue.stream().map(n -> String.valueOf(n.data)).collect(Collectors.joining(" "));
		return resultQeue;
	}

	@Test
	void levelOrderBSTTest() {
		Node root = generateBST(new int[] { 3, 5, 4, 7, 2, 1 });

		Queue<Node> levelOrder = levelOrder(root);

		assertIterableEquals(List.of(3, 2, 5, 1, 4, 7),
				levelOrder.stream().map(n -> n.data).collect(Collectors.toList()));
	}
}
