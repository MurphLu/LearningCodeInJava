package org.algorithm.recursion;

import com.sun.source.tree.Tree;

public class RobIII {
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3, new TreeNode(2, null, new TreeNode(3)), new TreeNode(3, null, new TreeNode(1)));
        Info process = process(root);
        System.out.println(process);
    }

    public static int rob(TreeNode root) {
        return 0;
    }

    static class Info {
        int childMax;
        int grandChildMax;

        public Info(int childMax, int grandChildMax) {
            this.childMax = childMax;
            this.grandChildMax = grandChildMax;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "childMax=" + childMax +
                    ", grandChildMax=" + grandChildMax +
                    '}';
        }
    }

    private static Info process(TreeNode root) {
        if (root == null) {
            return new Info(0,0);
        }
        Info left = process(root.left);
        Info right = process(root.right);
        return new Info(
                root.val + (left.grandChildMax + right.grandChildMax),
                Math.max(Math.max(left.childMax + right.childMax, left.grandChildMax + right.grandChildMax),
                Math.max(left.childMax + right.grandChildMax, left.grandChildMax + right.childMax)));
    }
}
