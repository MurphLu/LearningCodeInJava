package org.algorithm.LinkList.questions;

import org.algorithm.LinkList.DuLNode;
import org.algorithm.LinkList.LNode;

import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        reverseDuLNodeList();

    }

    private static void reverseLNodeList() {
        LNode<Integer> lNode = generateL();
        LNode<Integer> reverse = reverse(lNode);
        printNode(reverse);
    }

    private static LNode<Integer> generateL() {
        LNode<Integer> lNode = null;
        LNode<Integer> tail = null;
        for (int i = 0; i < 100; i++) {
            int v = (int) (Math.random() * 100);
            LNode<Integer> cur = new LNode<>(v, null);

            if(lNode == null) {
                lNode = cur;
            } else {
                tail.setNext(cur);
            }
            tail = cur;
        }
        printNode(lNode);
        return lNode;
    }

    private static LNode<Integer> reverse(LNode<Integer> lNode) {
        if(lNode.getNext() == null) {
            return lNode;
        }
        LNode<Integer> head = lNode;
        LNode<Integer> curr = lNode.getNext();
        head.setNext(null);
        while (curr != null) {
            LNode<Integer> temp = curr;
            curr = curr.getNext();
            temp.setNext(head);
            head = temp;
        }
        return head;
    }

    private static void printNode(LNode<Integer> lNode) {
        LNode<Integer> cur = lNode;
        int[] nodeArr = new int[100];
        int index = 0;
        while (cur!=null) {
            System.out.print(cur.getVal() + ", ");
            nodeArr[index++] = cur.getVal();
            cur = cur.getNext();
        }
        System.out.println();
        System.out.println(Arrays.toString(nodeArr));
    }


    private static void reverseDuLNodeList() {
        DuLNode<Integer> duLNode = generateDul();
        printNode(duLNode);
        DuLNode<Integer> reverse = reverse(duLNode);
        printNode(reverse);
    }

    private static DuLNode<Integer> generateDul() {
        DuLNode<Integer> head = null;
        DuLNode<Integer> tail = null;
        for (int i = 0; i < 100; i++) {
            int value = (int)(Math.random() * 100);
            DuLNode<Integer> curr = new DuLNode<>(value, null, null);
            if(head == null) {
                head = curr;
            } else {
                tail.setNext(curr);
                curr.setPre(tail);
            }
            tail = curr;
        }

        return head;
    }
    private static DuLNode<Integer> reverse(DuLNode<Integer> duLNode) {
        if(duLNode.getNext() == null) {
            return duLNode;
        }
        DuLNode<Integer> head = duLNode;
        DuLNode<Integer> curr = duLNode.getNext();
        head.setNext(null);
        while (curr != null) {
            curr.setPre(null);
            DuLNode<Integer> temp = curr;
            curr = curr.getNext();
            temp.setNext(head);
            head.setPre(temp);
            head = temp;

        }
        return head;
    }

    private static void printNode(DuLNode<Integer> duLNode) {
        System.out.println();
        DuLNode<Integer> head = duLNode;
        while (head.getNext() != null) {
            System.out.print(head.getVal() + ", ");
            head = head.getNext();
        }
        System.out.print(head.getVal());
        System.out.println();
        while (head.getPre() != null) {
            System.out.print(head.getVal() + ", ");
            head = head.getPre();
        }
        System.out.print(head.getVal());
    }
}
