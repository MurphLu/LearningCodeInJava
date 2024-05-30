package org.algorithm.leetcode.tree;

import org.algorithm.tree.TreeNode;

import java.util.*;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode last = root;
        TreeNode nextLast = root.right == null ? root.left : root.right;;
        boolean isZag = false;
        LinkedList<Integer> nums = new LinkedList<>();

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(isZag){
                nums.addFirst(node.val);
            } else {
                nums.addLast(node.val);
            }
            if (node.left != null) {
                queue.add(node.left);
                nextLast = node.left;
            }
            if (node.right != null) {
                queue.add(node.right);
                nextLast = node.right;
            }
            if (node == last) {
                isZag = !isZag;
                res.add(nums);
                nums = new LinkedList<>();
                last = nextLast;
            }
        }
        return res;
    }
}
