package org.algorithm.tree;

/**
 * 满二叉树
 * 总节点数 = 1<<level - 1
 */
public class FullBinaryTree {
    static class ReturnType {
        int height;
        int nodes;

        public ReturnType(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFBT(Node node) {
        ReturnType info = process(node);
        return info.nodes == (1 << info.height - 1);
    }

    public static ReturnType process(Node head) {
        if (head == null) {
            return new ReturnType(0, 0);
        }

        ReturnType l = process(head.left);
        ReturnType r = process(head.right);
        int nodes = l.nodes + r.nodes + 1;
        int height = Math.max(l.height, r.height) + 1;
        return new ReturnType(height, nodes);
    }

}
