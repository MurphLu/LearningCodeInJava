package org.algorithm.tree.questions;

import java.util.Arrays;

public class PathSum {
    
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

    }

    public boolean hasPathSum(TreeNode node, int targetSum) {
        if(node == null) {return false;}
        int[] result = process(node);
        for (int i = 0; i < result.length; i++) {
            if (result[i] == targetSum){
                return true;
            }
        }
        return false;
    }

    public int[] process(TreeNode node) {
        if (node == null) {
            return null;
        }

        int[] l = process(node.left);
        int[] r = process(node.right);

        int[] c;
        if (l == null && r == null) {
            c = new int[]{node.val};
        } else if (l != null && r != null) {
            c = new int[l.length + r.length];
            int cur = 0;
            for (int j : l) {
                c[cur++] = j + node.val;
            }
            for (int j : r) {
                c[cur++] = j + node.val;
            }

        }else {
            c = new int[l == null ? r.length : l.length];
            int[] arr = l == null ? r : l;
            for (int i = 0; i < arr.length; i++) {
                c[i] = arr[i] + node.val;
            }
        }
        return c;
    }
}
