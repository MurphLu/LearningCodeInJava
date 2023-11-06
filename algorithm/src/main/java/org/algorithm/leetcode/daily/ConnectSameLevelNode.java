package org.algorithm.leetcode.daily;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class ConnectSameLevelNode {
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        Queue<Node> queue = new LinkedList<>();
        Node curLast = root;
        Node nextLast = null;
        Node last = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left!= null) {
                queue.add(cur.left);
                nextLast = cur.left;
            }
            if (cur.right!= null) {
                queue.add(cur.right);
                nextLast = cur.right;
            }
            if (last != null) {
                last.next = cur;
            }
            if (cur == curLast) {
                curLast = nextLast;
                last = null;
            } else {
                last = cur;
            }
        }
        return root;
    }

}
