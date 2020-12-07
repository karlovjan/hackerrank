package datastructures.linketlist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

}
