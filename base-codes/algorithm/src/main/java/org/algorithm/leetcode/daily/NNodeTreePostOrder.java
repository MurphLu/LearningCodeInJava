package org.algorithm.leetcode.daily;


import org.algorithm.tree.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 *
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）
 */
public class NNodeTreePostOrder {
    static class Node {
        public int val;
        public List<Node> children;
    }

    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        process(root, res);
        return res;
    }

    private void process(Node root, List<Integer> postOrder) {
        for(Node node : root.children) {
            process(node, postOrder);
            postOrder.add(node.val);
        }
    }

    private void process2(Node root, List<Integer> postOrder) {
        Stack<Node> s = new Stack<>();
        Stack<Node> c = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            Node n = s.pop();
            if (!n.children.isEmpty()) {
                for(Node node: n.children) {
                    s.push(node);
                }
            }
            c.push(n);
        }
        while (!c.isEmpty()) {
            postOrder.add(c.pop().val);
        }


    }

}
