package org.algorithm.tree;

import com.sun.source.tree.WhileLoopTree;

import java.util.*;

public class TreeTravel {
    public static void main(String[] args) {

    }

    // 递归序
    public static void f(Node head) {
        if(head == null) { return; }
        f(head.left);
        f(head.right);
    }


    // 先序 头 左 右
    public static void preOrder(Node head) {
        if(head == null) { return; }
        System.out.println(head.val);
        preOrder(head.left);
        preOrder(head.right);
    }

    // 中序  左 头 右
    public static void inOrder(Node head) {
        if(head == null) { return; }
        inOrder(head.left);
        System.out.println(head.val);
        inOrder(head.right);
    }

    // 后序  左 右 头
    public static void posOrder(Node head) {
        if(head == null) { return; }
        posOrder(head.left);
        posOrder(head.right);
        System.out.println(head.val);
    }


    public static void preOrderWithStack(Node head) {
        Stack<Node> s = new Stack<>();
        Node h = head;
        s.push(h);
        while (!s.isEmpty()) {
            h = s.pop();
            System.out.println(h.val);
            if(h.right != null) s.push(h.right);
            if(h.left != null) s.push(h.left);
        }
    }

    public static void inOrderWithStack(Node head) {
        Stack<Node> s = new Stack<>();
        while (!s.isEmpty() || head != null) {
            if (head != null) {
                s.push(head);
                head = head.left;
            } else {
                head = s.pop();
                System.out.println(head.val);
                head = head.right;
            }
        }
    }


    public static void posOrderWithStack(Node head) {
        Stack<Node> s = new Stack<>();
        Stack<Node> c = new Stack<>();
        s.push(head);
        while (!s.isEmpty()) {
            Node n = s.pop();
            if (n.left != null) s.push(n.left);
            if(n.right != null) s.push(n.right);
            c.push(n);
        }
        while (!c.isEmpty()) {
            System.out.println(c.pop());
        }
    }

    // 宽度优先遍历
    public static void wideOrder(Node head) {
        Queue<Node> q = new LinkedList<>();
        q.add(head);
        while (!q.isEmpty()) {
            head = q.poll();
            System.out.println(head.val);
            if(head.left != null) q.add(head.left);
            if(head.left != null) q.add(head.right);
        }
    }

    // 最大宽度
    public static int maxWidth(Node head) {
        Queue<Node> q = new LinkedList<>();

        Map<Node, Integer> map =new HashMap<>();
        int curLevel = 1;
        int curLevelNode = 0;
        int maxWidth = -1;

        q.add(head);
        map.put(head, 1);
        while (!q.isEmpty()) {
            head = q.poll();

            int currentLevel = map.get(head);
            if (curLevel == currentLevel) {
                curLevelNode ++;
            } else {
                maxWidth = Math.max(curLevelNode, maxWidth);
                curLevel ++;
                curLevelNode = 1;
            }

            if(head.left != null) {
                q.add(head.left);
                map.put(head.left, map.get(head)+1);
            }
            if(head.left != null) {
                q.add(head.right);
                map.put(head.right, map.get(head)+1);
            }
        }
        return maxWidth;
    }

    public static int maxWidthWithoutMap(Node head) {
        Queue<Node> q = new LinkedList<>();

        q.add(head);

        Node curLevelLastNode = head;
        Node nextLevelLastNode = null;
        int nextMaxLength = 0;
        int max = 1;

        while (!q.isEmpty()){
            head = q.poll();

            if(head.left != null) {
                q.add(head.left);
                nextLevelLastNode = head.left;
                nextMaxLength ++;
            }
            if(head.right != null) {
                q.add(head.right);
                nextLevelLastNode = head.right;
                nextMaxLength ++;
            }
            if(curLevelLastNode == head) {
                curLevelLastNode = nextLevelLastNode;
                max = Math.max(nextMaxLength, max);
                nextMaxLength = 0;
            }
        }
        return max;
    }

    // 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
    public static List<List<Integer>> levelOrder(Node root) {
        if (root == null) { return new ArrayList<>();}
        Queue<Node> q = new LinkedList<>();

        q.add(root);

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        Node curLevelLastNode = root;
        Node nextLevelLastNode = null;

        level.add(root.val);
        result.add(level);
        level = new ArrayList<>();

        while (!q.isEmpty()) {
            root = q.poll();
            if(root.left != null) {
                q.add(root.left);
                nextLevelLastNode = root.left;
                level.add(root.left.val);
            }
            if(root.right != null) {
                q.add(root.right);
                nextLevelLastNode = root.right;
                level.add(root.right.val);
            }
            if(root == curLevelLastNode) {
                if(!level.isEmpty())result.add(level);
                curLevelLastNode = nextLevelLastNode;
                level = new ArrayList<>();
            }
        }
        return result;
    }


}
