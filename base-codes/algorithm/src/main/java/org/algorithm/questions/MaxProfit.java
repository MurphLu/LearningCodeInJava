package org.algorithm.questions;

import com.sun.source.doctree.SeeTree;

import java.util.HashSet;
import java.util.Set;

public class MaxProfit {
    public static void main(String[] args) {
        int[] arr = new int[]{1,4,2};
        int i = new MaxProfit().maxProfit(arr);
        System.out.println(i);
    }
    class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) { return 0;}
        Node node = buildTree(prices, 0, prices.length - 1);
        Info info = process(node);
        System.out.println(info.max);
        System.out.println(info.min);
        return Math.max(info.maxProfit, 0);
    }


    class Info {
        int min;
        int max;
        int maxProfit;

        public Info() {
        }

        public Info(int min, int max, int maxProfit) {
            this.min = min;
            this.max = max;
            this.maxProfit = maxProfit;
        }
    }
    public Info process(Node node) {
        if (node == null) {
            return null;
        }
        Info left = process(node.left);
        Info right = process(node.right);
        if (left == null && right == null) {
            return new Info(node.val, node.val, 0);
        }
        int min = left == null ? node.val : Math.min(left.min, node.val);
        int max = left == null ? node.val : Math.max(left.max, node.val);
        int profit = left == null ? 0 : Math.max(node.val - left.min, left.maxProfit);

        profit = right == null ? profit : Math.max(profit, Math.max(right.max - min, right.maxProfit));
        min = right == null ? min : Math.min(right.min, min);
        max = right == null ? max : Math.max(right.max, max);
        return new Info(min, max, profit);
    }

    public void printNode(Node node) {
        if (node == null) return;
        printNode(node.left);
        System.out.println(node.val);
        printNode(node.right);
    }

    private Node buildTree(int[] prices, int start, int end) {
        if (end < start) { return null;}
        if (end == start) {
            return new Node(prices[start]);
        }
        int mid = start + (end - start) / 2;
        Node midNode = new Node(prices[mid]);
        Node left = buildTree(prices, start, mid - 1);
        Node right = buildTree(prices, mid + 1, end);
        midNode.left = left;
        midNode.right = right;
        return midNode;
    }
}
