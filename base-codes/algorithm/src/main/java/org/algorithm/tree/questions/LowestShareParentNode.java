package org.algorithm.tree.questions;

import org.algorithm.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LowestShareParentNode {
    public static void main(String[] args) {

    }
    ArrayList<Node> arr = new ArrayList();
    static class Info{
        boolean hasNode;
        Node node;

        public Info(boolean hasNode) {
            this.hasNode = hasNode;
        }

        public Info(boolean hasNode, Node node) {
            this.hasNode = hasNode;
            this.node = node;
        }
    }

    public static Node f(Node head,  Node p, Node q) {
        List<Node> list = new ArrayList<>();
        list.add(p);
        list.add(q);
        return process(head, list).node;
    }

    public static Info process(Node head, List<Node> nodes) {
        if (head == null) {
            return new Info(false);
        }
        Info left = process(head.left, nodes);
        Info right = process(head.right, nodes);

        if(left.hasNode && right.hasNode) {
            return new Info(true, head);
        }

        if (left.node != null || right.node != null) {
            return left.node == null ? right : left;
        }

        if (nodes.contains(head)) {
            if (left.hasNode || right.hasNode) {
                return new Info(true, head);
            } else {
                nodes.remove(head);
                return new Info(true);
            }
        }

        return new Info(left.hasNode || right.hasNode);
    }


    public static Node lowest(Node head, Node o1, Node o2){
        // 如果节点是 null，或者节点等于任一节点，那么直接返回节点或者 null
        if (head == null || head == o1 || head == o2) {
            return head;
        }

        Node l = lowest(head.left, o1, o2);
        Node r = lowest(head.right, o1, o2);

        // 如果左右返回节点都不为 null 时，说明两个节点分别在当前节点的左右子树上，当前节点为最低公共父节点
        if (l != null && r != null) {
            return head;
        }

        // 不都有返回值
        return l != null ? l : r;
    }
}
