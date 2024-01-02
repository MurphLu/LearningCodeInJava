package org.algorithm.leetcode.topHot100;

/**
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 *
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 */
public class MaxPathSum {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1, null,
                new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4, null, new TreeNode(5, null,null)))));
        System.out.println(
        new MaxPathSum().maxPathSum(treeNode));
    }
    static class InfoTree{
        InfoTree left;
        InfoTree right;
        int val;
        int singleMax;

        public InfoTree(int val) {
            this.val = val;
        }
    }

    public int maxPathSum(TreeNode root) {
        InfoTree infoTree = build(root);
        process(infoTree);
        return maxVal(infoTree);
    }

    private InfoTree build(TreeNode root) {
        if (root == null) {
            return null;
        }
        InfoTree infoTree = new InfoTree(root.val);
        InfoTree left = build(root.left);
        InfoTree right = build(root.right);
        infoTree.left = left;
        infoTree.right = right;
        return infoTree;
    }

    private void process(InfoTree root) {
        if (root == null) {
            return;
        }
        process(root.left);
        process(root.right);
        if (root.left != null || root.right != null) {
            int leftSingle = 0;
            int rightSingle = 0;
            if(root.left != null && root.left.singleMax > 0) {
                leftSingle = root.left.singleMax;
            }
            if (root.right != null && root.right.singleMax > 0) {
                rightSingle = root.right.singleMax;
            }
            root.singleMax = root.val + Math.max(leftSingle, rightSingle);
            root.val += leftSingle + rightSingle;
        } else {
            root.singleMax = root.val;
        }
    }

    private int maxVal(InfoTree root) {
        if(root == null) {
            return Integer.MIN_VALUE;
        }
        int left = maxVal(root.left);
        int right = maxVal(root.right);
        return Math.max(Math.max(root.val, left), right);
    }
}
