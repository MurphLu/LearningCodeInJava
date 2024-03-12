package org.algorithm.leetcode.daily;

import org.algorithm.tree.TreeNode;

/**
 *给出一个满足下述规则的二叉树：
 *
 * root.val == 0
 * 如果 treeNode.val == x 且 treeNode.left != null，那么 treeNode.left.val == 2 * x + 1
 * 如果 treeNode.val == x 且 treeNode.right != null，那么 treeNode.right.val == 2 * x + 2
 * 现在这个二叉树受到「污染」，所有的 treeNode.val 都变成了 -1。
 *
 * 请你先还原二叉树，然后实现 FindElements 类：
 *
 * FindElements(TreeNode* root) 用受污染的二叉树初始化对象，你需要先把它还原。
 * bool find(int target) 判断目标值 target 是否存在于还原后的二叉树中并返回结果。
 */
public class FindElements {
    public static void main(String[] args) {
        FindElements ele = new FindElements(
                new TreeNode(-1, null, new TreeNode(-1))
        );
        ele.find(1);
        ele.find(2);
    }
    boolean[] record;
    public FindElements(TreeNode root) {
        int level = getLevel(root);
        record = new boolean[(1<<level) - 1];
        record[0] = true;
        travel(root, 0);
    }

    private int getLevel(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getLevel(root.left);
        int right = getLevel(root.right);
        return Math.max(left, right) + 1;
    }

    private void travel(TreeNode root, int index) {
        TreeNode right = root.right;
        TreeNode left = root.left;
        if (left != null) {
            int leftIdx = 2*index+1;
            record[leftIdx] = true;
            travel(left, leftIdx);
        }
        if (right != null) {
            int rightIdx = 2 * index + 2;
            record[rightIdx] = true;
            travel(right, rightIdx);
        }
    }


    public boolean find(int target) {
        return record[target];
    }
}
