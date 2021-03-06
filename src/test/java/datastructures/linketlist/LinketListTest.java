package datastructures.linketlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

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
        SinglyLinkedListNode head;
        SinglyLinkedListNode tail;
        private int size = 0;

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

    class DoublyLinkedListNode {
        public int data;
        public DoublyLinkedListNode next;
        public DoublyLinkedListNode prev;

        public DoublyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
            this.prev = null;
        }
    }

    class DoublyLinkedList {
        public DoublyLinkedListNode head;
        public DoublyLinkedListNode tail;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            DoublyLinkedListNode node = new DoublyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
                node.prev = this.tail;
            }

            this.tail = node;
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

        SinglyLinkedListNode newHead = new SinglyLinkedListNode(head.data);
        SinglyLinkedListNode nodeTmp = head.next;

        while (nodeTmp != null) {
            newHead = insertNodeAtHead(newHead, nodeTmp.data);
            nodeTmp = nodeTmp.next;
        }

        //return head
        return newHead;
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

    /*
     * For your reference:
     * https://www.hackerrank.com/challenges/merge-two-sorted-linked-lists/problem
     *
     * Given pointers to the heads of two sorted linked lists, merge them into a single, sorted linked list.
     * Either head pointer may be null meaning that the corresponding list is empty.
     *
     * Example:
     * headA: 1 -> 3 -> 7 -> null
     * headB: 1 -> 2 -> null
     *
     * result: 1 -> 1 -> 2 -> 3 -> 7 -> null
     *
     * SinglyLinkedListNode {
     *     int data;
     *     SinglyLinkedListNode next;
     * }
     *
     */
    SinglyLinkedListNode mergeAndSortLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        return sortList(addToTail(head1, head2));

    }

    /**
     * Immutable method for merge two lists
     *
     * @param head1 head of the first list
     * @param head2 head of the second list
     * @return a new immutable list of items from head1 to head2
     */
    private SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

        if (head1 == null && head2 == null) {
            return null;
        }

        SinglyLinkedList mergedList = new SinglyLinkedList();
        SinglyLinkedListNode nodeTmp = head1;

        while (nodeTmp != null) {
            mergedList.insertNode(nodeTmp.data);
            nodeTmp = nodeTmp.next;
        }

        nodeTmp = head2;

        while (nodeTmp != null) {
            mergedList.insertNode(nodeTmp.data);
            nodeTmp = nodeTmp.next;
        }

        return mergedList.head;
    }

    private SinglyLinkedListNode getTail(SinglyLinkedListNode head) {
        if (head == null) {
            return null;
        }
        SinglyLinkedListNode tail = head;

        //find tail
        while (tail.next != null) {
            tail = tail.next;
        }

        return tail; //tail
    }

    /**
     * Mutable method for merge two linket lists.
     *
     * @param rootHead Head after merging
     * @param newTail  last items in the list
     * @return mutable merged linket list
     */
    private SinglyLinkedListNode addToTail(SinglyLinkedListNode rootHead, SinglyLinkedListNode newTail) {

        if (rootHead == null) {
            return newTail;
        }

        SinglyLinkedListNode tail = getTail(rootHead);

        tail.next = newTail;

        return rootHead;
    }

    private SinglyLinkedListNode sortList(SinglyLinkedListNode head) {

        if (head == null) {
            return null;
        }

        SinglyLinkedList sortedList = new SinglyLinkedList();

        sortedList.insertNode(head.data);

        int min = head.data;
        int max = head.data;

        SinglyLinkedListNode tmpNode = head.next; //init to the next, first is already in the sorted list
        SinglyLinkedListNode sortedNode;

        while (tmpNode != null) {

            if (tmpNode.data <= min) {
                min = tmpNode.data;
                //insert min to the head
                sortedList.head = insertNodeAtHead(sortedList.head, tmpNode.data);
            } else if (tmpNode.data >= max) {
                max = tmpNode.data;
                //insert max to the tail
                sortedList.insertNode(tmpNode.data);
            } else {

                sortedNode = sortedList.head;
                //hledej mezi MIN==head a MAX==tail
                while (sortedNode != null) {

                    //prvni hodnotu v sortovanem listu vynecham, protoze tu porovnavam na MIN value
                    //beru az druhou sortedNode.next ,abych mohl provest vymenu hodnot v sortovanem listu potrebuju referenci na predchazejici node
                    //a diky MAX nenarazim nikdy na sortedNode.next = null
                    if (tmpNode.data <= sortedNode.next.data) {
                        SinglyLinkedListNode newNode = new SinglyLinkedListNode(tmpNode.data);
                        newNode.next = sortedNode.next;
                        sortedNode.next = newNode;
                        break;
                    }

                    sortedNode = sortedNode.next;

                }
            }

            tmpNode = tmpNode.next;

        }

        return sortedList.head;
    }

    @Test
    void addToTailTest_null() {

        assertNull(addToTail(null, null));

        SinglyLinkedList llist1 = new SinglyLinkedList();

        llist1.insertNode(9);
        llist1.insertNode(3);
        llist1.insertNode(7);

        var head = addToTail(llist1.head, null);
        assertEquals(9, head.data);
        assertEquals(3, head.next.data);
        assertEquals(7, head.next.next.data);
        assertNull(head.next.next.next);

        llist1 = new SinglyLinkedList();

        llist1.insertNode(1);
        llist1.insertNode(4);
        llist1.insertNode(2);

        head = addToTail(null, llist1.head);
        assertEquals(1, head.data);
        assertEquals(4, head.next.data);
        assertEquals(2, head.next.next.data);
        assertNull(head.next.next.next);

    }

    @Test
    void addToTailTest() {

        SinglyLinkedList llist1 = new SinglyLinkedList();

        llist1.insertNode(9);
        llist1.insertNode(3);
        llist1.insertNode(7);

        SinglyLinkedList llist2 = new SinglyLinkedList();

        llist1.insertNode(1);
        llist2.insertNode(4);

        var head = addToTail(llist1.head, llist2.head);
        assertEquals(9, head.data);
        assertEquals(3, head.next.data);
        assertEquals(7, head.next.next.data);
        assertEquals(1, head.next.next.next.data);
        assertEquals(4, head.next.next.next.next.data);
        assertNull(head.next.next.next.next.next);

    }

    @Test
    void mergeListsTest_null() {

        assertNull(mergeLists(null, null));

        SinglyLinkedList llist1 = new SinglyLinkedList();

        llist1.insertNode(9);
        llist1.insertNode(3);
        llist1.insertNode(7);

        var head = mergeLists(llist1.head, null);
        assertEquals(9, head.data);
        assertEquals(3, head.next.data);
        assertEquals(7, head.next.next.data);
        assertNull(head.next.next.next);

        llist1 = new SinglyLinkedList();

        llist1.insertNode(1);
        llist1.insertNode(4);
        llist1.insertNode(2);

        head = mergeLists(null, llist1.head);
        assertEquals(1, head.data);
        assertEquals(4, head.next.data);
        assertEquals(2, head.next.next.data);
        assertNull(head.next.next.next);

    }

    @Test
    void mergeListsTest() {

        SinglyLinkedList llist1 = new SinglyLinkedList();

        llist1.insertNode(9);
        llist1.insertNode(3);
        llist1.insertNode(7);

        SinglyLinkedList llist2 = new SinglyLinkedList();

        llist1.insertNode(1);
        llist2.insertNode(4);

        var head = mergeLists(llist1.head, llist2.head);
        assertEquals(9, head.data);
        assertEquals(3, head.next.data);
        assertEquals(7, head.next.next.data);
        assertEquals(1, head.next.next.next.data);
        assertEquals(4, head.next.next.next.next.data);
        assertNull(head.next.next.next.next.next);

    }

    @Test
    void mergeAndSortListsTest_null() {

        assertNull(mergeAndSortLists(null, null));

        SinglyLinkedList llist1 = new SinglyLinkedList();

        llist1.insertNode(9);
        llist1.insertNode(3);
        llist1.insertNode(7);

        var head = mergeAndSortLists(llist1.head, null);
        assertEquals(3, head.data);
        assertEquals(7, head.next.data);
        assertEquals(9, head.next.next.data);
        assertNull(head.next.next.next);

        llist1 = new SinglyLinkedList();

        llist1.insertNode(1);
        llist1.insertNode(4);
        llist1.insertNode(2);

        head = mergeAndSortLists(null, llist1.head);
        assertEquals(1, head.data);
        assertEquals(2, head.next.data);
        assertEquals(4, head.next.next.data);
        assertNull(head.next.next.next);
    }

    @Test
    void mergeAndSortListsTest() {

        SinglyLinkedList llist1 = new SinglyLinkedList();

        llist1.insertNode(9);
        llist1.insertNode(3);
        llist1.insertNode(7);

        SinglyLinkedList llist2 = new SinglyLinkedList();

        llist1.insertNode(1);
        llist2.insertNode(4);

        var head = mergeAndSortLists(llist1.head, llist2.head);
        assertEquals(1, head.data);
        assertEquals(3, head.next.data);
        assertEquals(4, head.next.next.data);
        assertEquals(7, head.next.next.next.data);
        assertEquals(9, head.next.next.next.next.data);
        assertNull(head.next.next.next.next.next);

    }

    /*
    https://www.hackerrank.com/challenges/get-the-value-of-the-node-at-a-specific-position-from-the-tail

    Given a pointer to the head of a linked list and a specific position,
    determine the data value at that position.
    Count backwards from the tail node. The tail is at postion 0, its parent is at 1 and so on.
     */
    int getNodeDataFromTail(SinglyLinkedListNode head, int positionFromTail) {

        if (head == null && positionFromTail >= 0) {
            throw new IllegalArgumentException("Head cannot be null and positionFromTail cannot be negative");
        }

        SinglyLinkedListNode currentNode = reverseLinketList(head);
        int position = 0;

        while (position < positionFromTail && currentNode != null) {
            ++position;
            currentNode = currentNode.next;
        }

        if (currentNode != null) {
            return currentNode.data;
        } else {
            throw new IllegalArgumentException("Parameter 'positionFromTail' is greater than size of the List");
        }
    }

    @Test
    void getNodeDataFromTailTest() {

        assertEquals(1, getNodeDataFromTail(new SinglyLinkedListNode(1), 0));

        SinglyLinkedList lList = new SinglyLinkedList();
        lList.insertNode(3);
        lList.insertNode(10);
        lList.insertNode(1);
        lList.insertNode(5);
        lList.insertNode(6);
        lList.insertNode(26);
        lList.insertNode(8);

        assertEquals(26, getNodeDataFromTail(lList.head, 1));
        assertEquals(8, getNodeDataFromTail(lList.head, 0));
        assertEquals(3, getNodeDataFromTail(lList.head, 6));
        assertEquals(5, getNodeDataFromTail(lList.head, 3));

    }

    /**
     * https://www.hackerrank.com/challenges/delete-duplicate-value-nodes-from-a-sorted-linked-list/problem
     * You are given the pointer to the head node of a sorted linked list,
     * where the data in the nodes is in ascending order.
     * Delete nodes and return a sorted list with each distinct value in the original list.
     * The given head pointer may be null indicating that the list is empty.
     *
     * @param head head of the linked list
     * @return new head without d
     */
    private SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode head) {

        if (head == null) {
            return null;
        }

        SinglyLinkedList lList = new SinglyLinkedList();
        lList.insertNode(head.data);

        SinglyLinkedListNode nodeTmp = head.next;

        while (nodeTmp != null) {

            if (lList.tail.data != nodeTmp.data) {
                lList.insertNode(nodeTmp.data);
            }

            nodeTmp = nodeTmp.next;
        }

        return lList.head;
    }

    @Nested
    @DisplayName("RemoveDuplicatesTestClass")
    class RemoveDuplicatesTestClass {

        @Test
        void removeDuplicatesTest_null() {
            assertNull(removeDuplicates(null));
        }

        @Test
        void removeDuplicatesTest() {

            SinglyLinkedList lList = new SinglyLinkedList();
            lList.insertNode(1);
            lList.insertNode(1);
            lList.insertNode(1);
            lList.insertNode(1);
            lList.insertNode(1);
            lList.insertNode(2);
            lList.insertNode(2);
            lList.insertNode(2);
            lList.insertNode(2);
            lList.insertNode(2);
            lList.insertNode(3);
            lList.insertNode(3);
            lList.insertNode(3);
            lList.insertNode(3);
            lList.insertNode(3);
            lList.insertNode(4);
            lList.insertNode(4);
            lList.insertNode(4);
            lList.insertNode(4);
            lList.insertNode(4);
            lList.insertNode(5);
            lList.insertNode(5);
            lList.insertNode(5);
            lList.insertNode(5);
            lList.insertNode(5);
            lList.insertNode(6);
            lList.insertNode(6);
            lList.insertNode(6);
            lList.insertNode(6);
            lList.insertNode(6);
            lList.insertNode(6);
            lList.insertNode(7);
            lList.insertNode(7);
            lList.insertNode(7);
            lList.insertNode(7);
            lList.insertNode(7);
            lList.insertNode(8);
            lList.insertNode(8);
            lList.insertNode(8);
            lList.insertNode(8);
            lList.insertNode(8);
            lList.insertNode(9);
            lList.insertNode(9);
            lList.insertNode(9);
            lList.insertNode(9);
            lList.insertNode(9);
            lList.insertNode(9);
            lList.insertNode(10);
            lList.insertNode(10);
            lList.insertNode(10);
            lList.insertNode(10);
            lList.insertNode(10);
            lList.insertNode(10);

            SinglyLinkedListNode nodeTmp = removeDuplicates(lList.head);
            int i = 0;

            while (nodeTmp != null) {
                assertEquals(++i, nodeTmp.data);
                nodeTmp = nodeTmp.next;
            }
        }

    }

    @Nested
    @DisplayName("CycleListDetectionTestClass")
    class CycleListDetectionTestClass {

        /**
         * A linked list is said to contain a cycle if any node is visited more than once while traversing the list.
         * Given a pointer to the head of a linked list, determine if it contains a cycle.
         * <p>
         * 1 -> 2 -> 3 -> 1 -> 2 -> 3 ...
         * There is a cycle where node 3 points back to node 1, so return 1.
         * linked list je tak nekonecny, cykluje pres stejne nody
         */
        boolean hasCycle(SinglyLinkedListNode head) {

            if (head == null) {
                return false;
            }

            boolean cycle = false;
            SinglyLinkedListNode nodeTmp = head;

            while (nodeTmp != null) {
                if (nodeTmp.data == -1) {
                    cycle = true;
                    break;
                }
                nodeTmp.data = -1;

                nodeTmp = nodeTmp.next;
            }

            return cycle;
        }

        /**
         * its called Floyd’s Cycle detection algorithm.
         */
        boolean hasCycle_FLoyd(SinglyLinkedListNode head) {

            if (head == null) {
                return false;
            }

            SinglyLinkedListNode slow = head;
            SinglyLinkedListNode fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;

                if (slow == fast) {
                    return true; //fast node catch up the slow node
                }
            }

            // cycle neni tak zkonci loop na podmince fast.next != null

            return false;
        }

        @Test
        void hasCycleTest_false() {

            SinglyLinkedList list = new SinglyLinkedList();
            list.insertNode(2);
            list.insertNode(2);
            list.insertNode(3);
            list.insertNode(10);

            assertFalse(hasCycle(list.head));
        }

        @Test
        void hasCycleTest_floyd_false() {

            SinglyLinkedList list = new SinglyLinkedList();
            list.insertNode(2);
            list.insertNode(2);
            list.insertNode(3);
            list.insertNode(10);

            assertFalse(hasCycle_FLoyd(list.head));
        }

        @Test
        void hasCycleTest() {

            assertTrue(hasCycle(cycleLinketListGenerator(2, 10).head));
        }

        @Test
        void hasCycleTest_FLoyd() {

            assertTrue(hasCycle_FLoyd(cycleLinketListGenerator(2, 10).head));
        }

        private SinglyLinkedList cycleLinketListGenerator(int index, int llistCount) {
            SinglyLinkedList llist = new SinglyLinkedList();

            Random random = new Random();

            for (int i = 0; i < llistCount; i++) {
                int llistItem = random.nextInt(1000);

                llist.insertNode(llistItem);
            }

            //proved zacykleni
            SinglyLinkedListNode extra = new SinglyLinkedListNode(-1);
            SinglyLinkedListNode temp = llist.head;

            for (int i = 0; i < llistCount; i++) {
                if (i == index) {
                    extra = temp;
                }

                if (i != llistCount - 1) {
                    temp = temp.next;
                }
            }

            temp.next = extra;
            return llist;
        }

    }

    /*
        Find Merge Point of Two Lists
        https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/problem

        list1: 1,2,3,4,5
        list2: 4,10,3,4,5
        merge point at index: 2
     */
    @Nested
    @DisplayName("FindMergeNodeTestClass")
    class FindMergeNodeTestClass {

        private int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

            SinglyLinkedListNode pr1 = head1;
            SinglyLinkedListNode pr2 = head2;

            int result = -1;
            boolean found = false;

            while (pr1 != null && !found) {


                while (pr2 != null) {


                    if (pr1 == pr2) {
                        result = pr1.data;
                        found = true;
                        break;
                    }

                    pr2 = pr2.next;
                }
                //start again from the head2
                pr2 = head2;

                pr1 = pr1.next;
            }

            return result;
        }

        @Test
        void findMergeNodeTest() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;
            int mergeIndex2 = 1;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            for (int i = 0; i < mergeIndex2; i++) {
                pr2 = pr2.next;
            }

            pr2.next = pr1;

            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest2() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;
            int mergeIndex2 = 3;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            for (int i = 0; i < mergeIndex2; i++) {
                pr2 = pr2.next;
            }

            pr2.next = pr1;

            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest3() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            pr2.next = pr1;

            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest4() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);

            list1.head.next = list2.head;


            assertEquals(5, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest5() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);

            list2.head.next = list1.head;


            assertEquals(2, findMergeNode(list1.head, list2.head));
        }

        int findMergeNode_v1(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

            //12345
            //1112345
            SinglyLinkedListNode current1 = head1;
            SinglyLinkedListNode current2 = head2;

            //Do till the two nodes are the same
            while(current1 != current2){
                //If you reached the end of one list start at the beginning of the other one
                //currentA
                if(current1.next == null){
                    current1 = head2;
                }else{
                    current1 = current1.next;
                }
                //currentB
                if(current2.next == null){
                    current2 = head1;
                }else{
                    current2 = current2.next;
                }
            }
            return current2.data;

        }

    }

    @Nested
    @DisplayName("FindMergeNode_v1_TestClass")
    class FindMergeNode_v1_TestClass {

        private int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

            SinglyLinkedListNode current1 = head1;
            SinglyLinkedListNode current2 = head2;

            //Do till the two nodes are the same
            while(current1 != current2){
                //If you reached the end of one list start at the beginning of the other one
                //currentA
                if(current1.next == null){
                    current1 = head2;
                } else{
                    current1 = current1.next;
                }
                //currentB
                if(current2.next == null){
                    current2 = head1;
                } else{
                    current2 = current2.next;
                }
            }

            //ListA = 1--2--3--4--5
//            ListB = 1--1--2--2--3--4--5
            //A1B1 ne, A2B1 ne, A3B2 ne, A4B2 ne, A5B3 ne, B1B4 ne, B1B5 ne, B2A1 ne, B2A2 Found

            //ListA = 1--2--3--4--5
//            ListB = 10--4--5

            //a1b10 ne, a2b5 ne, a3b4 ne, a4b4


            //ListA = 1--2--3--4--5
//            ListB = 5

            //a1b5 ne, a2b5 ne, a3b5 ne, a4b5 ne, a5b5

            //ListB = 5
            //ListA = 1--2--3--4--5

            //a5b1 ne, a5b2 ne, a5b3 ne, a5b4 ne, a5b5


            return current2.data;
        }

        @Test
        void findMergeNodeTest() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;
            int mergeIndex2 = 1;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            for (int i = 0; i < mergeIndex2; i++) {
                pr2 = pr2.next;
            }

            pr2.next = pr1;

            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest2() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;
            int mergeIndex2 = 3;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            for (int i = 0; i < mergeIndex2; i++) {
                pr2 = pr2.next;
            }

            pr2.next = pr1;

            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest3() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            pr2.next = pr1;

            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest4() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);

            list1.head.next = list2.head;


            assertEquals(5, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest5() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);

            list2.head.next = list1.head;


            assertEquals(2, findMergeNode(list1.head, list2.head));
        }

    }
    @Nested
    @DisplayName("FindMergeNode_v2_TestClass")
    class FindMergeNode_v2_TestClass {

        private int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {

            //12345
            //1112345
            SinglyLinkedListNode slow = head1;
            SinglyLinkedListNode fast = head2;

            while (true) {

                if(slow == null) {
                    slow = head1;
                }

                if (fast == null) {
                    fast = head2;
                }

                //fast.next == null pokud head2 ma jen jedenu polozzku a next je null
                if (slow == fast || slow == fast.next || (fast.next == null && slow == head2)) {
                    return slow.data; //fast node catch up the slow node
                }

                slow = slow.next;
                fast = fast.next;
            }
        }

        @Test
        void findMergeNodeTest() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 3;
            int mergeIndex2 = 0;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            for (int i = 0; i < mergeIndex2; i++) {
                pr2 = pr2.next;
            }

            pr1.next = pr2;
//            pr2.next = pr1;

            assertEquals(5, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest11() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 0;
            int mergeIndex2 = 3;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            for (int i = 0; i < mergeIndex2; i++) {
                pr2 = pr2.next;
            }

            pr2.next = pr1;

            assertEquals(2, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest2() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;
            int mergeIndex2 = 3;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            for (int i = 0; i < mergeIndex2; i++) {
                pr2 = pr2.next;
            }

            pr2.next = pr1;

            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest3() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);
            list1.insertNode(10);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);
            list2.insertNode(10);
            list2.insertNode(15);
            list2.insertNode(20);

            SinglyLinkedListNode pr1 = list1.head;
            SinglyLinkedListNode pr2 = list2.head;

            int mergeIndex1 = 2;

            for (int i = 0; i < mergeIndex1; i++) {
                pr1 = pr1.next;
            }

            pr2.next = pr1;

            //2 3 4 10
            //5 4 10
            //
            assertEquals(4, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest4() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);

            list1.head.next = list2.head;


            assertEquals(5, findMergeNode(list1.head, list2.head));
        }

        @Test
        void findMergeNodeTest5() {

            SinglyLinkedList list1 = new SinglyLinkedList();
            list1.insertNode(2);

            SinglyLinkedList list2 = new SinglyLinkedList();
            list2.insertNode(5);

            list2.head.next = list1.head;


            assertEquals(2, findMergeNode(list1.head, list2.head));
        }

    }

    DoublyLinkedListNode sortedInsert(DoublyLinkedListNode head, int data) {

        DoublyLinkedListNode sortedNode = head;
        DoublyLinkedListNode lastNode = null;

        while (sortedNode != null) {

            if (sortedNode.data > data) {
                DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
                newNode.next = sortedNode;
                newNode.prev = sortedNode.prev;

                sortedNode.prev = newNode;

                if (lastNode != null) {
                    lastNode.next = newNode;
                } else {
                    head = newNode;
                }

                break;
            }

            lastNode = sortedNode;
            sortedNode = sortedNode.next;
        }

        if(sortedNode == null) {
            //insert at tail
            DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
            newNode.prev = lastNode;

            lastNode.next = newNode;
        }

        return head;

    }

    DoublyLinkedListNode sorteDoubleLinketList(DoublyLinkedListNode head) {


        if (head == null) {
            return null;
        }

        DoublyLinkedList sortedList = new DoublyLinkedList();

        sortedList.insertNode(head.data);

        int min = head.data;
        int max = head.data;

        DoublyLinkedListNode tmpNode = head.next; //init to the next, first is already in the sorted list
        DoublyLinkedListNode sortedNode;

        while (tmpNode != null) {

            if (tmpNode.data <= min) {
                min = tmpNode.data;
                //insert min to the head
                sortedList.head = insertNodeAtHeadToDLL(sortedList.head, tmpNode.data);
            } else if (tmpNode.data >= max) {
                max = tmpNode.data;
                //insert max to the tail
                sortedList.insertNode(tmpNode.data);
            } else {

                sortedNode = sortedList.head;
                //hledej mezi MIN==head a MAX==tail
                while (sortedNode != null) {

                    //prvni hodnotu v sortovanem listu vynecham, protoze tu porovnavam na MIN value
                    //beru az druhou sortedNode.next ,abych mohl provest vymenu hodnot v sortovanem listu potrebuju referenci na predchazejici node
                    //a diky MAX nenarazim nikdy na sortedNode.next = null
                    if (tmpNode.data <= sortedNode.next.data) {
                        DoublyLinkedListNode newNode = new DoublyLinkedListNode(tmpNode.data);
                        newNode.next = sortedNode.next;
                        newNode.prev = sortedNode;
                        sortedNode.next = newNode;
                        break;
                    }

                    sortedNode = sortedNode.next;

                }
            }

            tmpNode = tmpNode.next;

        }

        return sortedList.head;

    }

    private DoublyLinkedListNode insertNodeAtHeadToDLL(DoublyLinkedListNode head, int newData) {
        DoublyLinkedListNode newHead = new DoublyLinkedListNode(newData);
        newHead.next = head;
        head.prev = newHead;

        return newHead;
    }

    @Nested
    @DisplayName("DoublyLinkedListTestClass")
    class DoublyLinkedListTestClass {

        @Test
        void sortedInsertTest1() {

            DoublyLinkedList list1 = new DoublyLinkedList();
            list1.insertNode(10);
            list1.insertNode(8);
            list1.insertNode(5);
            list1.insertNode(3);
            list1.insertNode(1);

            DoublyLinkedListNode sorted = sorteDoubleLinketList(list1.head);

            DoublyLinkedListNode inserted = sortedInsert(sorted, 4);

            assertEquals(1, inserted.data);
            assertEquals(3, inserted.next.data);
            assertEquals(4, inserted.next.next.data);
            assertEquals(5, inserted.next.next.next.data);
            assertEquals(8, inserted.next.next.next.next.data);
            assertEquals(10, inserted.next.next.next.next.next.data);
            assertNull(inserted.next.next.next.next.next.next);
            assertEquals(8, inserted.next.next.next.next.next.prev.data);
            assertEquals(5, inserted.next.next.next.next.prev.data);
            assertEquals(4, inserted.next.next.next.prev.data);
            assertEquals(3, inserted.next.next.prev.data);
            assertEquals(1, inserted.next.prev.data);
        }

        @Test
        void sortedInsertTest2() {

            DoublyLinkedList list1 = new DoublyLinkedList();
            list1.insertNode(1);
            list1.insertNode(3);
            list1.insertNode(5);
            list1.insertNode(8);
            list1.insertNode(10);

            DoublyLinkedListNode sorted = sorteDoubleLinketList(list1.head);

            DoublyLinkedListNode inserted = sortedInsert(sorted, 4);

            assertEquals(1, inserted.data);
            assertEquals(3, inserted.next.data);
            assertEquals(4, inserted.next.next.data);
            assertEquals(5, inserted.next.next.next.data);
            assertEquals(8, inserted.next.next.next.next.data);
            assertEquals(10, inserted.next.next.next.next.next.data);
            assertNull(inserted.next.next.next.next.next.next);
            assertEquals(8, inserted.next.next.next.next.next.prev.data);
            assertEquals(5, inserted.next.next.next.next.prev.data);
            assertEquals(4, inserted.next.next.next.prev.data);
            assertEquals(3, inserted.next.next.prev.data);
            assertEquals(1, inserted.next.prev.data);
        }

        @Test
        void sortedInsertTest3() {

            DoublyLinkedList list1 = new DoublyLinkedList();
            list1.insertNode(1);
            list1.insertNode(5);
            list1.insertNode(10);
            list1.insertNode(8);
            list1.insertNode(2);

            DoublyLinkedListNode sorted = sorteDoubleLinketList(list1.head);

            DoublyLinkedListNode inserted = sortedInsert(sorted, 4);

            assertEquals(1, inserted.data);
            assertEquals(2, inserted.next.data);
            assertEquals(4, inserted.next.next.data);
            assertEquals(5, inserted.next.next.next.data);
            assertEquals(8, inserted.next.next.next.next.data);
            assertEquals(10, inserted.next.next.next.next.next.data);
            assertNull(inserted.next.next.next.next.next.next);
        }

        @Test
        void sortedInsertTest4() {

            DoublyLinkedList list1 = new DoublyLinkedList();
            list1.insertNode(2);
            list1.insertNode(3);
            list1.insertNode(4);


            DoublyLinkedListNode inserted = sortedInsert(list1.head, 1);

            assertNull(inserted.prev);
            assertEquals(1, inserted.data);
            assertEquals(2, inserted.next.data);
            assertEquals(3, inserted.next.next.data);
            assertEquals(4, inserted.next.next.next.data);
        }

        @Test
        void sortedInsertTest5() {

            DoublyLinkedList list1 = new DoublyLinkedList();
            list1.insertNode(1);
            list1.insertNode(2);
            list1.insertNode(3);


            DoublyLinkedListNode inserted = sortedInsert(list1.head, 4);

            assertEquals(1, inserted.data);
            assertEquals(2, inserted.next.data);
            assertEquals(3, inserted.next.next.data);
            assertEquals(4, inserted.next.next.next.data);
            assertEquals(3, inserted.next.next.next.prev.data);
        }

    }

    DoublyLinkedListNode reverseDoublyLinkedList(DoublyLinkedListNode head) {

        if(head == null){
            return null;
        }

        //only one node
        if(head.next == null) {
            return head;
        }

        DoublyLinkedListNode reversed = null;

        DoublyLinkedListNode newNode;
        DoublyLinkedListNode currentNode = head;

        while (currentNode != null) {

            newNode = new DoublyLinkedListNode(currentNode.data);
            newNode.next = reversed;
            newNode.prev = currentNode.next;

            reversed = newNode;

            currentNode = currentNode.next;
        }

        return reversed;
    }

    @Test
    void reverseDoublyLinkedListTest() {

        DoublyLinkedList list1 = new DoublyLinkedList();
        list1.insertNode(1);
        list1.insertNode(2);
        list1.insertNode(3);
        list1.insertNode(4);


        DoublyLinkedListNode reversed = reverseDoublyLinkedList(list1.head);

        assertEquals(4, reversed.data);
        assertEquals(3, reversed.next.data);
        assertEquals(2, reversed.next.next.data);
        assertEquals(1, reversed.next.next.next.data);
    }
}