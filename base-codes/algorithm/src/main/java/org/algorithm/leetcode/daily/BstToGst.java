package org.algorithm.leetcode.daily;

import org.algorithm.leetcode.topHot100.TreeNode;

/**
 * 给定一个二叉搜索树 root (BST)，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
 *
 * 提醒一下， 二叉搜索树 满足下列约束条件：
 *
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 */
public class BstToGst {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4,
                new TreeNode(1,
                        new TreeNode(0),
                        new TreeNode(2, null, new TreeNode(3)
                        )
                ),
                new TreeNode(6,
                        new TreeNode(5),
                        new TreeNode(7, null, new TreeNode(8)))
        );
        new BstToGst().bstToGst(root);
    }
    public TreeNode bstToGst(TreeNode root) {
        process(root);
        return root;
    }
    int sum = 0;
    public void process(TreeNode root) {
        if (root == null) {
            return;
        }
        process(root.right);
        sum += root.val;
        root.val = sum;
        System.out.println(root.val);
        process(root.left);
    }
}
