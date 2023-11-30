package org.algorithm.ds;

import java.util.ArrayList;
import java.util.List;

public class SkipList {
    public static void main(String[] args) {
        SkipList sl = new SkipList();
        sl.insert(1);
        sl.insert(10);
        sl.insert(5);
        sl.insert(3);
    }

    private void print() {
        System.out.println(head);
    }

    static class Node {
        Node[] nextArr;
        Integer val;

        public Node(Integer val, int nextSize) {
            this.val = val;
            this.nextArr = new Node[nextSize];
        }

        public int getNextSize() {
            return nextArr.length;
        }

        public void updateNextArr(int length) {
            Node[] arr = new Node[length];
            System.arraycopy(nextArr, 0, arr, 0, nextArr.length);
            nextArr = arr;
        }

        public int getNextVal(int index) {
            return nextArr[index] == null ? -1 : nextArr[index].val;
        }

        public Node getNextNode(int index) {
            return nextArr[index];
        }

        public void setNextVal(int index, Node node) {
            nextArr[index] = node;
        }
    }

    Node head;
    int size;

    public SkipList() {
        this.head = new Node(null, 1);
        size = 1;
    }

    public void insert(Integer val) {
        int size = 1;
        while ((int)Math.floor(Math.random() * 2) == 0) {
            size++;
        }
        if (size > head.getNextSize()){
            head.updateNextArr(size);
        }
        Node node = new Node(val, size);
        insert(node);
    }
    private void insert(Node node) {
        Node cur = head;
        int nextIdx = node.getNextSize() - 1;
        while (nextIdx >= 0) {
            Node n = cur.getNextNode(nextIdx);
            if (n == null) {
                cur.setNextVal(nextIdx--, node);
            } else if (n.val < node.val) {
                cur = n;
            } else {
                cur.setNextVal(nextIdx, node);
                node.setNextVal(nextIdx, n);
                nextIdx--;
            }
        }
        size ++;
    }
}
