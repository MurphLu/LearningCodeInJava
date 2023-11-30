package org.algorithm.LinkList.questions;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node rand;

    public Node(int val) {
        this.val = val;
    }
}

public class CopyLinkListWithRandPointer {
    public static void main(String[] args) {

    }

    public static Node copy(Node node) {
        Map<Node, Node> map = new HashMap<>();
        Node head = node;
        while (head != null) {
            map.put(node, new Node(node.val));
            head = head.next;
        }

        map.forEach((k,v)->{
            v.next = map.get(k.next);
            v.rand = map.get(k.rand);
        });
        return map.get(node);
    }


    public static Node copy2(Node node) {
        Node head = node;
        while (head!= null) {
            Node temp = head.next;
            Node copy = new Node(head.val);
            head.next = copy;
            copy.next = temp;
            head = temp;
        }
        head = node;
        while (head != null) {
            head.next.rand = head.rand != null ? head.rand.next : null;
            head = head.next.next;
        }

        Node nh = node.next;
        head = node;
        while (head != null) {
           Node next = head.next.next;
           Node copy = head.next;
           copy.next = next != null ? next.next : null;
           head.next = next;
           head = next;
        }
        return nh;
    }
}
