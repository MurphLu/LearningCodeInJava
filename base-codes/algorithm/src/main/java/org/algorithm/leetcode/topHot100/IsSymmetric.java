package org.algorithm.leetcode.topHot100;

public class IsSymmetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)){
            return true;
        }
        return process(root.left, root.right);
    }
    private boolean process(TreeNode a, TreeNode b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || (a.val != b.val)
        ) {
            return false;
        }
        boolean test1 = process(a.left, b.right);
        boolean test2 = process(a.right, b.left);
        return test1 && test2;
    }
}
