package thirtycodingdays;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        if(head == null){
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
        int[] testData = new int[]{2, 3, 4, 1};
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
}
