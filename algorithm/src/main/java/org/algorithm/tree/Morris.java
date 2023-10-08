package org.algorithm.tree;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * Morris 遍历
 * 种遍历二叉树的方式，并且时间复杂度O(N)，额外空阅复杂度O(1)
 * 通过利用原树中大量空闲指针的方式，达到节省空间的目的
 */
public class Morris {

    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException {
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        process(node0);
    }

    private static void out(Node head, Node cur) throws IOException, URISyntaxException, InterruptedException {
        Map<Node, String> set = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("digraph{").append("\n");
        builder.append(getGraph(head, null, set, cur));
        builder.append("}");
        System.out.println(builder.toString());
        File file = new File("algorithm/out.dot");
        System.out.println(file.getPath());
        FileWriter writer = new FileWriter(file);
//        FileOutputStream fos = new FileOutputStream();
        writer.write(builder.toString());
//        fos.write(builder.toString().getBytes(StandardCharsets.UTF_8));
        writer.flush();
        writer.close();

    }

    private static String getGraph(Node head, String f, Map<Node, String> map, Node current) {
        if (head == null) {
            return "";
        }
        if (map.containsKey(head)) {
            return f + "->" + map.get(head) + "\n";
        }
        StringBuilder builder = new StringBuilder();
        String cur = "n" + head.val;
        map.put(head, cur);
        builder.append(cur).append(" [shape=circle ").append(head == current ? "color=red" : "").append("]").append("\n");
        if (f!=null) {
            builder.append(f).append("->").append(cur).append("\n");
        }
        builder.append(getGraph(head.left, cur, map, current));
        builder.append(getGraph(head.right, cur, map, current));
        return builder.toString();
    }

    public static void process(Node head) throws IOException, URISyntaxException, InterruptedException {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // 没遍历完，继续走
            out(head, cur);
            mostRight = cur.left; // mostRight是 cur 的左孩子
            if (mostRight != null){ // 如果 cur 的左孩子不为空
                // 那么从 cur 的左孩子开始一直往右找，直到找到节点满足如下条件
                // mostRight.right != null 右侧还有节点，继续向右找
                // 直到 mostRight.right != null（第一次到 cur 节点）
                // 或者 mostRight.right != cur （第二次到 cur 节点）
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 第一次到右节点为空的节点，则将右侧的空指针指向 cur
                // cur = cur.left，对cur 的左节点继续做同样的遍历
                if (mostRight.right == null) { // 第一次来到 cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur 第二次来到 cur将最右节点的右节点置为空，还原
                    mostRight.right = null;
                }
            }
            cur = cur.right; // 当前节点的左节点搞完之后继续搞右节点
        }
    }

    public static void preOrder(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // 没遍历完，继续走
            mostRight = cur.left; // mostRight是 cur 的左孩子
            if (mostRight != null){ // 如果 cur 的左孩子不为空
                // 那么从 cur 的左孩子开始一直往右找，直到找到节点满足如下条件
                // mostRight.right != null 右侧还有节点，继续向右找
                // 直到 mostRight.right != null（第一次到 cur 节点）
                // 或者 mostRight.right != cur （第二次到 cur 节点）
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 第一次到右节点为空的节点，则将右侧的空指针指向 cur
                // cur = cur.left，对cur 的左节点继续做同样的遍历
                if (mostRight.right == null) { // 第一次来到 cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur 第二次来到 cur将最右节点的右节点置为空，还原
                    mostRight.right = null;
                }
            }  else {
                // 先序
                System.out.println(cur.val);
            }
            cur = cur.right; // 当前节点的左节点搞完之后继续搞右节点
        }
    }

    public static void inOrder(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // 没遍历完，继续走
            mostRight = cur.left; // mostRight是 cur 的左孩子
            if (mostRight != null){ // 如果 cur 的左孩子不为空
                // 那么从 cur 的左孩子开始一直往右找，直到找到节点满足如下条件
                // mostRight.right != null 右侧还有节点，继续向右找
                // 直到 mostRight.right != null（第一次到 cur 节点）
                // 或者 mostRight.right != cur （第二次到 cur 节点）
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 第一次到右节点为空的节点，则将右侧的空指针指向 cur
                // cur = cur.left，对cur 的左节点继续做同样的遍历
                if (mostRight.right == null) { // 第一次来到 cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur 第二次来到 cur将最右节点的右节点置为空，还原
                    mostRight.right = null;
                }
            }
            System.out.println(cur.val);
            cur = cur.right; // 当前节点的左节点搞完之后继续搞右节点
        }
    }

    public static void posOrder(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) { // 没遍历完，继续走
            mostRight = cur.left; // mostRight是 cur 的左孩子
            if (mostRight != null){ // 如果 cur 的左孩子不为空
                // 那么从 cur 的左孩子开始一直往右找，直到找到节点满足如下条件
                // mostRight.right != null 右侧还有节点，继续向右找
                // 直到 mostRight.right != null（第一次到 cur 节点）
                // 或者 mostRight.right != cur （第二次到 cur 节点）
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 第一次到右节点为空的节点，则将右侧的空指针指向 cur
                // cur = cur.left，对cur 的左节点继续做同样的遍历
                if (mostRight.right == null) { // 第一次来到 cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur 第二次来到 cur, 逆序打印左树右边界
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            System.out.println(cur.val);
            cur = cur.right; // 当前节点的左节点搞完之后继续搞右节点
        }
        // 逆序打印整棵树的右边界
        printEdge(head);
    }

    private static void printEdge(Node node) {
        Node reverseEdge = reverse(node);
        Node temp = reverseEdge;
        while (temp != null) {
            System.out.println(temp.val);
            temp = temp.right;
        }
        reverse(reverseEdge);
    }

    private static Node reverse(Node node) {
        Node newHead = null;
        Node remain = null;

        while (node != null) {
            remain = node.right;
            node.right = newHead;
            newHead = node;
            node = remain;
        }
        return newHead;
    }

    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        int preVal = Integer.MIN_VALUE;
        while (cur != null) { // 没遍历完，继续走
            mostRight = cur.left; // mostRight是 cur 的左孩子
            if (mostRight != null){ // 如果 cur 的左孩子不为空
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次来到 cur
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // mostRight.right == cur 第二次来到 cur将最右节点的右节点置为空，还原
                    mostRight.right = null;
                }
            }
            if(cur.val <= preVal) {
                return false;

            }
            preVal = cur.val;
            cur = cur.right; // 当前节点的左节点搞完之后继续搞右节点
        }
        return true;
    }
}
