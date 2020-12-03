package datastructures.linketlist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinketListTest {

	/*
	 * For your reference:
	 *
	 * https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list/problem
	 *
	 * SinglyLinkedListNode {
	 *     int data;
	 *     SinglyLinkedListNode next;
	 * }
	 *
	 */

	static class SinglyLinkedListNode {
		int data;
		SinglyLinkedListNode next;

		SinglyLinkedListNode(int nodeData) {
			this.data = nodeData;
			this.next = null;
		}
	}

	static class SinglyLinkedList {
		private int size = 0;
		SinglyLinkedListNode head;
		SinglyLinkedListNode tail;

		SinglyLinkedList() {
			this.head = null;
			this.tail = null;
		}

		void insertNode(int nodeData) {
			SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

			//na zacatku se nastavi zacatecni node jak do head tak i do tail, tail a head ma stejnou referenci
			//proto kdyz v druhem kroku a dalsich delam tail.next = node; tak se porad pridava next i v head
			//1loop > h1=t1=n1
			//2loop > h1.next=t2.next=n2
			//3loop > h1.next.next=t3.next=n3
			if (this.head == null) {
				this.head = node;
			} else {
				this.tail.next = node;
			}

			this.tail = node;

			++size;
		}

		int size() {

			return size;
		}
	}

	static void printLinkedList(SinglyLinkedListNode head) {

		if (head == null) {
			return;
		}

		SinglyLinkedListNode node = head;

		do {
			System.out.println(node.data);
			node = node.next;
		} while (node != null);
	}

	@Test
	void insertLinketListTest() {
		SinglyLinkedList llist = new SinglyLinkedList();

		llist.insertNode(16);
		llist.insertNode(13);
		llist.insertNode(14);
		llist.insertNode(25);

		assertEquals(4, llist.size());
		assertEquals(25, llist.tail.data);
		assertNull(llist.tail.next);
		assertEquals(16, llist.head.data);
		assertEquals(13, llist.head.next.data);
		assertEquals(14, llist.head.next.next.data);
		assertEquals(25, llist.head.next.next.next.data);

		printLinkedList(llist.head);
	}

	/**
	 * https://www.hackerrank.com/challenges/insert-a-node-at-the-tail-of-a-linked-list/problem
	 */
	SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {

		if (head == null) {
			//initialization of the linked list
			return new SinglyLinkedListNode(data);
		}
		//find last node and set its next to a new Node
		SinglyLinkedListNode nodeTmp = head;
		while (nodeTmp.next != null) {
			nodeTmp = nodeTmp.next;
		}
		//found last node with next == null
		nodeTmp.next = new SinglyLinkedListNode(data);

		//return head
		return head;

	}

	@Test
	void insertNodeAtTailTest() {
		SinglyLinkedList llist = new SinglyLinkedList();

		llist.head = insertNodeAtTail(llist.head, 141);
		llist.head = insertNodeAtTail(llist.head, 302);
		llist.head = insertNodeAtTail(llist.head, 164);
		llist.head = insertNodeAtTail(llist.head, 530);
		llist.head = insertNodeAtTail(llist.head, 474);

		assertEquals(141, llist.head.data);
		assertEquals(302, llist.head.next.data);
		assertEquals(164, llist.head.next.next.data);
		assertEquals(530, llist.head.next.next.next.data);
		assertEquals(474, llist.head.next.next.next.next.data);

		printLinkedList(llist.head);
	}

	/*
	 * For your reference:
	 * https://www.hackerrank.com/challenges/insert-a-node-at-the-head-of-a-linked-list/problem
	 *
	 * SinglyLinkedListNode {
	 *     int data;
	 *     SinglyLinkedListNode next;
	 * }
	 *
	 */
	SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode head, int data) {

		if (head == null) {
			//initialization of the linked list
			return new SinglyLinkedListNode(data);
		}
		//found last node with next == null
		SinglyLinkedListNode newHead = new SinglyLinkedListNode(data);
		newHead.next = head;

		//return head
		return newHead;
	}

	@Test
	void insertNodeAtHeadTest() {
		SinglyLinkedList llist = new SinglyLinkedList();

		llist.head = insertNodeAtHead(llist.head, 141);
		llist.head = insertNodeAtHead(llist.head, 302);
		llist.head = insertNodeAtHead(llist.head, 164);
		llist.head = insertNodeAtHead(llist.head, 530);
		llist.head = insertNodeAtHead(llist.head, 474);

		assertEquals(474, llist.head.data);
		assertEquals(530, llist.head.next.data);
		assertEquals(164, llist.head.next.next.data);
		assertEquals(302, llist.head.next.next.next.data);
		assertEquals(141, llist.head.next.next.next.next.data);

		printLinkedList(llist.head);
	}

	/* For your reference:
	https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list/problem
	* SinglyLinkedListNode {
	*     int data;
	*     SinglyLinkedListNode next;
	* }
	*
			*/
	SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode head, int data, int position) {

		if (head == null) {
			//initialization of the linked list
			return new SinglyLinkedListNode(data);
		}

		if (position == 0) {
			SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
			newNode.next = head;
			return newNode; //new head
		}

		//find last node and set its next to a new Node
		SinglyLinkedListNode nodeTmp = head;
		int i = 0;
		while (nodeTmp.next != null && i < position - 1) {
			nodeTmp = nodeTmp.next;
			++i;
		}

		//found last node with next == null
		SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
		newNode.next = nodeTmp.next;
		nodeTmp.next = newNode;

		//return head
		return head;
	}

	@Test
	void insertNodeAtPositionTest1() {
		SinglyLinkedList llist = new SinglyLinkedList();

		llist.head = insertNodeAtPosition(llist.head, 1, 0);
		llist.head = insertNodeAtPosition(llist.head, 2, 0);
		llist.head = insertNodeAtPosition(llist.head, 3, 1);
		llist.head = insertNodeAtPosition(llist.head, 4, 1);
		llist.head = insertNodeAtPosition(llist.head, 10, 3);
		llist.head = insertNodeAtPosition(llist.head, 1000,
				200); //index out of range, nemam zadnou kontrolu, nastavi hodnotu nakonec

		assertEquals(2, llist.head.data);
		assertEquals(2, llist.head.data);
		assertEquals(2, llist.head.data);
		assertEquals(2, llist.head.data);
		assertEquals(2, llist.head.data);
		assertEquals(4, llist.head.next.data);
		assertEquals(3, llist.head.next.next.data);
		assertEquals(10, llist.head.next.next.next.data);
		assertEquals(1, llist.head.next.next.next.next.data);
		assertEquals(1000, llist.head.next.next.next.next.next.data); //tail

		printLinkedList(llist.head);
	}

	/*
	 *https://www.hackerrank.com/challenges/delete-a-node-from-a-linked-list/problem
	 */
	SinglyLinkedListNode deleteNode(SinglyLinkedListNode head, int position) {

		if (head == null) {
			return null;
		}

		if (position == 0) {
			return head.next; //new head
		}

		SinglyLinkedListNode nodeTmp = head;
		int i = 0;
		while (nodeTmp.next != null && i < position - 1) {
			nodeTmp = nodeTmp.next;
			++i;
		}

		nodeTmp.next = nodeTmp.next != null ? nodeTmp.next.next : null;

		//return head
		return head;
	}

	@Test
	void deleteNodeTest() {
		SinglyLinkedList llist = new SinglyLinkedList();

		llist.head = deleteNode(llist.head, 0);
		assertNull(llist.head);

		llist.head = insertNodeAtTail(null, 1);
		llist.head = insertNodeAtTail(llist.head, 2);
		llist.head = insertNodeAtTail(llist.head, 3);
		llist.head = insertNodeAtTail(llist.head, 4);
		llist.head = insertNodeAtTail(llist.head, 5);

		llist.head = deleteNode(llist.head, 0);
		assertEquals(2, llist.head.data);
		llist.head = deleteNode(llist.head, 1);
		assertEquals(2, llist.head.data);
		assertEquals(4, llist.head.next.data);
		llist.head = deleteNode(llist.head, 2); //remove tail
		assertEquals(2, llist.head.data);
		assertEquals(4, llist.head.next.data);
		llist.head = deleteNode(llist.head, 100);
		assertEquals(2, llist.head.data);
		assertEquals(4, llist.head.next.data);
		llist.head = deleteNode(llist.head, 1);
		llist.head = deleteNode(llist.head, 0);
		assertNull(llist.head);

	}

	/*
	 *https://www.hackerrank.com/challenges/print-the-elements-of-a-linked-list-in-reverse/problem
	 */
	SinglyLinkedListNode reverseLinketList(SinglyLinkedListNode head) {

		if (head == null) {
			return null;
		}

		SinglyLinkedList llist = new SinglyLinkedList();
		SinglyLinkedListNode nodeTmp = head;
		llist.head = insertNodeAtHead(llist.head, nodeTmp.data);

		while (nodeTmp.next != null) {
			llist.head = insertNodeAtHead(llist.head, nodeTmp.next.data);
			nodeTmp = nodeTmp.next;
		}

		//return head
		return llist.head;
	}

	void reversePrint(SinglyLinkedListNode head) {

		SinglyLinkedListNode revHead = reverseLinketList(head);

		printLinkedList(revHead);
	}

	@Test
	void reverseLinketListTest() {
		SinglyLinkedList llist = new SinglyLinkedList();

		llist.insertNode(9);
		llist.insertNode(3);
		llist.insertNode(7);

		SinglyLinkedListNode reversedNodes = reverseLinketList(llist.head);

		assertEquals(7, reversedNodes.data);
		assertEquals(3, reversedNodes.next.data);
		assertEquals(9, reversedNodes.next.next.data);
	}

	/*
	Compare two linked lists

	https://www.hackerrank.com/challenges/compare-two-linked-lists/problem

You’re given the pointer to the head nodes of two linked lists.
Compare the data in the nodes of the linked lists to check if they are equal.
If all data attributes are equal and the lists are the same length, return 1. Otherwise, return 0.
	 */

	boolean compareTwoLinketLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

		if (head1 == null && head2 == null) {
			return true;
		}

		//alespon jedne list je null
		if (head1 == null || head2 == null) {
			return false;
		}

		SinglyLinkedListNode tmpNode1 = head1;
		SinglyLinkedListNode tmpNode2 = head2;

		while (tmpNode1 != null) {

			if (tmpNode2 == null || tmpNode1.data != tmpNode2.data) {
				return false;
			}

			tmpNode1 = tmpNode1.next;
			tmpNode2 = tmpNode2.next;
		}

		//pokud projdu vsemi nody tak na konci musi mit oba listy null
		return tmpNode2 == null;
	}

	@Test
	void compareTwoLinketListsTest_null() {

		assertTrue(compareTwoLinketLists(null, null));
		assertFalse(compareTwoLinketLists(new SinglyLinkedListNode(1), null));
		assertFalse(compareTwoLinketLists(null, new SinglyLinkedListNode(1)));
	}

	@Test
	void compareTwoLinketListsTest_equal() {

		assertTrue(compareTwoLinketLists(new SinglyLinkedListNode(1), new SinglyLinkedListNode(1)));

		SinglyLinkedList llist1 = new SinglyLinkedList();

		llist1.insertNode(9);
		llist1.insertNode(3);
		llist1.insertNode(7);

		SinglyLinkedList llist2 = new SinglyLinkedList();

		llist2.insertNode(9);
		llist2.insertNode(3);
		llist2.insertNode(7);

		assertTrue(compareTwoLinketLists(llist1.head, llist2.head));
	}

	@Test
	void compareTwoLinketListsTest_NotEqual() {

		assertFalse(compareTwoLinketLists(new SinglyLinkedListNode(1), new SinglyLinkedListNode(2)));

		SinglyLinkedList llist1 = new SinglyLinkedList();

		llist1.insertNode(9);
		llist1.insertNode(3);
		llist1.insertNode(7);

		SinglyLinkedList llist2 = new SinglyLinkedList();

		llist2.insertNode(9);
		llist2.insertNode(3);
		llist2.insertNode(1);

		assertFalse(compareTwoLinketLists(llist1.head, llist2.head));
	}

	@Test
	void compareTwoLinketListsTest_DifferentLenght() {

		SinglyLinkedList llist1 = new SinglyLinkedList();

		llist1.insertNode(1);
		llist1.insertNode(2);
		llist1.insertNode(7);

		SinglyLinkedList llist2 = new SinglyLinkedList();

		llist2.insertNode(1);
		llist2.insertNode(2);
		llist2.insertNode(7);
		llist2.insertNode(10);

		assertFalse(compareTwoLinketLists(llist1.head, llist2.head));
	}

}