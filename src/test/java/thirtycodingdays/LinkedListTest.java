package thirtycodingdays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

public class LinkedListTest {

	@Test
	void linkedListTestInserting() {

		LinkedList<String> myLinkedList = new LinkedList<>();

		myLinkedList.add("a");
		myLinkedList.addFirst("b");
		myLinkedList.addLast("d");

		assertEquals("b", myLinkedList.getFirst());
		assertEquals("b", myLinkedList.get(0));
		assertEquals("d", myLinkedList.getLast());
		assertEquals("d", myLinkedList.get(2));
		assertEquals("a", myLinkedList.get(1));

	}

	static class Node {
		int data;
		Node next;

		Node(int d) {
			data = d;
			next = null;
		}
	}

	/**
	 * insert a new node to the linked list of nodes
	 *
	 * @param head a head node of the linked list
	 * @param data new data for a new node
	 * @return a head node of the linked list
	 */
	Node insert(Node head, int data) {

		if (head == null) {
			//initialization of the linked list
			return new Node(data);
		}
		//find last node and set its next to a new Node
		Node nodeTmp = head;
		while (nodeTmp.next != null) {
			nodeTmp = nodeTmp.next;
		}
		//found last node with next == null
		nodeTmp.next = new Node(data);

		//return head
		return head;

	}

	@Test
	void nodeTest() {
		int[] testData = new int[] { 2, 3, 4, 1 };
		Node head = null;
		for (int testDatum : testData) {
			head = insert(head, testDatum);
		}
        /*
        Node start = head;

        while (start != null) {
            assertEquals(2, start.data);
            start = start.next;
        }

         */

		Node start = head;
		assertEquals(2, start.data);
		start = start.next;
		assertEquals(3, start.data);
		start = start.next;
		assertEquals(4, start.data);
		start = start.next;
		assertEquals(1, start.data);
		start = start.next;
		assertNull(start);

	}

	static Node removeDuplicates(Node head) {
		if (head == null) {
			return null;
		}

		Node startNode = head;

		while (startNode != null) {
			int data = startNode.data;

			Node lastNode = startNode;
			Node tmpNode = startNode.next;
			while (tmpNode != null) {
				if (tmpNode.data == data) {
					lastNode.next = tmpNode.next;
					tmpNode = lastNode.next;
				} else {

					lastNode = tmpNode;
					tmpNode = tmpNode.next;
				}
			}

			startNode = startNode.next;
		}

		return head;
	}

	@Test
	void removeDuplicates_null_Test() {

		assertNull(removeDuplicates(null));

	}

	@Test
	void removeDuplicates_twoTheSame_Test() {
		int[] testData = new int[] { 2, 2, 2, 2, 2 };
		Node head = null;
		for (int testDatum : testData) {
			head = insert(head, testDatum);
		}

		Node start = removeDuplicates(head);
		assertEquals(2, start.data);
		assertNull(start.next);

	}

	@Test
	void removeDuplicates_one_Test() {
		int[] testData = new int[] { 3 };
		Node head = null;
		for (int testDatum : testData) {
			head = insert(head, testDatum);
		}

		Node start = removeDuplicates(head);
		assertEquals(3, start.data);
		assertNull(start.next);

	}

	@Test
	void removeDuplicates_noDuplication_Test() {
		int[] testData = new int[] { 1, 2, 3, 4 };
		Node head = null;
		for (int testDatum : testData) {
			head = insert(head, testDatum);
		}

		Node start = removeDuplicates(head);
		int i = 1;

		while (start != null) {
			assertEquals(i++, start.data);
			start = start.next;
		}

	}

	@Test
	void removeDuplicatesTest() {
		int[] testData = new int[] { 1, 2, 2, 3, 3, 4 };
		Node head = null;
		for (int testDatum : testData) {
			head = insert(head, testDatum);
		}

		Node start = removeDuplicates(head);
		int i = 1;

		while (start != null) {
			assertEquals(i++, start.data);
			start = start.next;
		}

	}

}
