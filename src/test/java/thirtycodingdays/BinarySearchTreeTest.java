package thirtycodingdays;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

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
        assertEquals(0, getHeight(generateBST(new int[]{3})));
    }

    @Test
    void getHeightBST() {
        Node root = generateBST(new int[]{3, 5, 2, 1, 4, 6, 7});

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

        //import java.util.stream.Collectors;
        //System.out.println(resultQeue.stream().map(n -> String.valueOf(n.data)).collect(Collectors.joining(" ")));
        return resultQeue;
    }

    @Test
    void levelOrderBSTTest() {
        Node root = generateBST(new int[]{3, 5, 4, 7, 2, 1});

        Queue<Node> levelOrder = levelOrder(root);

        assertIterableEquals(List.of(3, 2, 5, 1, 4, 7),
                levelOrder.stream().map(n -> n.data).collect(Collectors.toList()));
    }


	/*


	The binary tree above has the following traversals:

InOrder: 1 2 3 4 5 6 7
PostOrder: 1 3 2 5 7 6 4
PreOrder (a depth-first-search or DFS): 4 2 1 3 6 5 7
Level-Order(breadth-first-search (BFS)): 4 2 6 1 3 5 7

	 */

    void inOrder(Node root, List<Integer> result) {
        if (root != null) {
            inOrder(root.left, result);

            if (result == null) {
                result = new ArrayList<>();
            }
            result.add(root.data);

            inOrder(root.right, result);
        }
    }

    @Test
    void inOrderTest() {
        List<Integer> result = new ArrayList<>();
        Node root = generateBST(new int[]{3, 5, 4, 7, 2, 1, 9});

        inOrder(root, result);

        assertIterableEquals(List.of(1, 2, 3, 4, 5, 7, 9), result);
    }

    void postOrderTraversing(Node root, List<Integer> result) {
        if (root != null) {
            postOrderTraversing(root.left, result);
            postOrderTraversing(root.right, result);

            if (result == null) {
                result = new ArrayList<>();
            }
            result.add(root.data);
        }
    }

    @Test
    void postOrderTraversingTest() {
        List<Integer> result = new ArrayList<>();
        Node root = generateBST(new int[]{3, 5, 4, 7, 2, 1, 6});

        postOrderTraversing(root, result);

        assertIterableEquals(List.of(1, 3, 2, 5, 7, 6, 4), result);

        //TODO prepracovat generateBST tak aby vracela root uprostred tedy aby vysledny strom byl symetricky a ne aby byl u prostredniho nodu left/right null
        //aby Node root = generateBST(new int[] { 3, 5, 4, 7, 2, 1, 6 }); root = 4 a 4[2, 6], 2[1,3], 6[5,7]
    }

    void preOrderTraversing(Node root, List<Integer> result) {
        if (root != null) {
            if (result == null) {
                result = new ArrayList<>();
            }
            result.add(root.data);

            preOrderTraversing(root.left, result);
            preOrderTraversing(root.right, result);

        }
    }

    @Test
    void preOrderTraversingTest() {
        List<Integer> result = new ArrayList<>();
        Node root = generateBST(new int[]{3, 5, 2, 1, 4});

        preOrderTraversing(root, result);

        assertIterableEquals(List.of(1, 2, 4, 5, 3), result);

        //System.out.println(result.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(" ")));
//		result.forEach(n -> System.out.print(n + " "));
        //TODO prepracovat generateBST tak aby vracela root uprostred tedy aby vysledny strom byl symetricky a ne aby byl u prostredniho nodu left/right null
        //aby Node root = generateBST(new int[] { 3, 5, 4, 7, 2, 1, 6 }); root = 4 a 4[2, 6], 2[1,3], 6[5,7]
    }

	/*
	Tree : Top View
	Given a pointer to the root of a binary tree, print the top view of the binary tree.

The tree as seen from the top the nodes, is called the top view of the tree.
	 */

    public List<Integer> getTopView(Node root) {

        if (root == null) {
            return new ArrayList<>();
        }


        List<Integer> result = new ArrayList<>();

        class TopViewNode {
            int hd;
            int level;
            Node node;

            public TopViewNode(int hd, int level, Node node) {
                this.hd = hd;
                this.level = level;
                this.node = node;
            }
        }

        Queue<TopViewNode> queue = new LinkedList<>();
        Map<Integer, Node> resultHashMap = new HashMap<>();

        int minHd = 0;
        int maxHd = 0;

        // enqueue current root
        queue.add(new TopViewNode(0, 1, root));
        resultHashMap.put(0, root);


        //pouziju modified LevelOrder algorithm
        while (!queue.isEmpty()) {
            // dequeue next node
            TopViewNode tree = queue.poll();

            final int levelCounted = tree.level + 1;

            // enqueue child elements from next level in order
            if (tree.node.left != null) {
                final int hdCounted = tree.hd - 1;
                queue.add(new TopViewNode(hdCounted, levelCounted, tree.node.left));
                if (!resultHashMap.containsKey(hdCounted)) {
                    resultHashMap.put(hdCounted, tree.node.left);
                    minHd = Math.min(minHd, hdCounted);
                }
            }
            if (tree.node.right != null) {
                final int hdCounted = tree.hd + 1;
                queue.add(new TopViewNode(hdCounted, levelCounted, tree.node.right));
                if (!resultHashMap.containsKey(hdCounted)) {
                    resultHashMap.put(hdCounted, tree.node.right);
                    maxHd = Math.max(maxHd, hdCounted);
                }
            }
        }

        //transformuj hashmap na vystupni data strukturu
        for (int i = minHd; i <= maxHd; i++) {
            result.add(resultHashMap.get(i).data);
        }

        return result;
    }

    public void printTopView(Node root) {

        List<Integer> topViewList = getTopView(root);

        topViewList.forEach(n -> System.out.print(n + " "));
    }

    @Test
    void topViewTest() {
        Node rootXXX = generateBST(new int[]{1, 2, 5, 3, 6, 4});

		/*
			 1
			/ \
		   2   3
		      / \
		     5   6
		    / \
		   7   8

		   horizontal distance HD={-1, 2}
		   Level  {1, 4}
		 */
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.left.left = new Node(7);
        root.right.left.right = new Node(8);

        List<Integer> result = getTopView(root);

        assertIterableEquals(List.of(2, 1, 3, 6), result);

    }

    @Test
    void topViewTest2() {

		/* Create following Binary Tree
         1
        / \
       2   3
        \
		 4
		  \
           5
            \
             6

        horizontal distance HD={-1, 1}
		   Level  {1, 5}
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
        root.left.right.right.right = new Node(6);

        List<Integer> result = getTopView(root);

        assertIterableEquals(List.of(2, 1, 3, 6), result);

    }

    @Test
    void topViewTest_fromHackerRank() {

		/* Create following Binary Tree

       1
        \
		 2
		  \
           5
          / \
         3   6
          \
           4

        horizontal distance HD={0, 3}
		   Level  {1, 5}
        */

        Node root = new Node(1);
        root.right = new Node(2);
        root.right.right = new Node(5);
        root.right.right.left = new Node(3);
        root.right.right.right = new Node(6);
        root.right.right.left.right = new Node(4);

        List<Integer> result = getTopView(root);

        assertIterableEquals(List.of(1, 2, 5, 6), result);

    }
}
