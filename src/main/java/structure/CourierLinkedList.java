package structure;

import model.Courier;

public class CourierLinkedList {
    private static class Node {
        Courier data;
        Node next;

        Node(Courier data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public void add(Courier data) {
        Node newNode = new Node(data);
        if (head == null)
            head = newNode;
        else {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        }
    }

    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
    }

    public void clear() {
        head = null;
    }
}
