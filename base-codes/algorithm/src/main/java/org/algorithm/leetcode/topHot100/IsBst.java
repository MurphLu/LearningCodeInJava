package org.algorithm.leetcode.topHot100;

public class IsBst {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        IsBst is = new IsBst();
        is.isValidBST(node);
    }
    static class Info{
        public Integer max;
        public Integer min;
        public boolean isBST;
        public Info(){}
        public Info(Integer min, Integer max, boolean isBST){
            this.max = max;
            this.min = min;
            this.isBST = isBST;
        }
    }

    public boolean isValidBST(TreeNode root) {
        Info result = process(root);
        return result == null || result.isBST;
    }

    public Info process(TreeNode root) {
        if(root == null){
            return null;
        }
        if (root.left == null && root.right == null) {
            return new Info(root.val, root.val, true);
        }

        Info left = process(root.left);
        Info right = process(root.right);
        int val = root.val;
        int min = left == null ? val : Math.min(left.min, val);
        int max = right == null ? val : Math.max(right.max, val);
        boolean isBST = true;
        if(left != null && ((left.max >= val) || !left.isBST)) {
            isBST = false;
        }
        if(right != null && ((right.min <= val) || !right.isBST)) {
            isBST = false;
        }
        return new Info(min, max, isBST);

    }
}
