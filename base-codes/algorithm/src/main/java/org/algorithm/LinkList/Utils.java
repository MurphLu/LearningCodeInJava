package org.algorithm.LinkList;

import java.util.Arrays;

public class Utils {
    public static LNode<Integer> generate(int length, int min, int max) {
        LNode<Integer> lNode = null;
        LNode<Integer> tail = null;
        for (int i = 0; i < length; i++) {
            int v = min + (int) (Math.random() * max);
            LNode<Integer> cur = new LNode<>(v, null);

            if(lNode == null) {
                lNode = cur;
            } else {
                tail.setNext(cur);
            }
            tail = cur;
        }
        return lNode;
    }

    public static void printNode(LNode<Integer> lNode) {
        LNode<Integer> cur = lNode;

        while (cur!=null) {
            System.out.print(cur.getVal() + ", ");

            cur = cur.getNext();
        }
        System.out.println();
    }
}
