package org.algorithm.leetcode.topHot100;

public class CopyRandomList {
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node copyRandomList(Node head) {
        Node temp = head;
        while (temp != null) {
            Node copy = new Node(temp.val);
            copy.next = temp.next;
            temp.next = copy;
            temp = copy.next;
        }
        temp = head;
        while (temp!= null) {
            temp.next.random = temp.random == null ? null : temp.random.next;
            temp = temp.next.next;
        }

        temp = head;
        Node newHead = null;
        Node tail = null;
        while (temp != null) {
            if (newHead == null) {
                newHead = temp.next;
                tail = temp.next;
            } else {
                tail.next = temp.next;
                tail = temp.next;
            }
            temp.next = temp.next.next;
            temp = temp.next.next;
            tail.next = null;
        }
        return newHead;
    }
}
