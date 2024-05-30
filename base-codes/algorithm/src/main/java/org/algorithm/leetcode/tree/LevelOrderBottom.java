package org.algorithm.leetcode.tree;

import org.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 */
public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode last = root;
        TreeNode nextLast = root.right == null ? root.left : root.right;;
        List<Integer> nums = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            nums.add(node.val);
            if (node.left != null) {
                queue.add(node.left);
                nextLast = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLast = node.right;
            }
            if (node == last) {
                res.addFirst(nums);
                nums = new ArrayList<>();
                last = nextLast;
            }
        }

        return res;
    }
}
