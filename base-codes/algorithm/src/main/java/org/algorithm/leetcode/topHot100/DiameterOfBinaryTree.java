package org.algorithm.leetcode.topHot100;

public class DiameterOfBinaryTree {

    static class Info {
        int diameter;
        int maxLength;

        public Info(int diameter, int maxLength) {
            this.diameter = diameter;
            this.maxLength = maxLength;
        }
    }
    public int diameterOfBinaryTree(TreeNode root) {
        Info info = process(root);
        return info.diameter;
    }

    private Info process(TreeNode root) {
        if (root == null) {
            return new Info(0,0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        int diameter = left.maxLength + right.maxLength + 1;
        diameter = Math.max(Math.max(diameter, left.diameter), right.diameter);
        return new Info(diameter, (Math.max(left.maxLength, right.maxLength)) + 1);
    }
}
