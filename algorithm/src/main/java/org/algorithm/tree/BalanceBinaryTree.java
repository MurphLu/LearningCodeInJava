package org.algorithm.tree;

/**
 * 平衡二叉树
 * 每一棵子树，左树高度与右树高度差不能超过 1
 */
public class BalanceBinaryTree {
    public static void main(String[] args) {

    }

    static class ReturnType {
        boolean isBalance;
        int height;

        public ReturnType(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    public static boolean isBBT(Node head) {
        return process(head).isBalance;
    }

    public static ReturnType process(Node head) {
        if(head == null) {
            return new ReturnType(true, 0);
        }
        ReturnType l = process(head.left);
        ReturnType r = process(head.right);
        boolean isBBT = l.isBalance && r.isBalance && Math.abs(l.height - r.height) < 2;
        int height = Math.max(l.height, r.height) + 1;
        return new ReturnType(isBBT, height);
    }
}
