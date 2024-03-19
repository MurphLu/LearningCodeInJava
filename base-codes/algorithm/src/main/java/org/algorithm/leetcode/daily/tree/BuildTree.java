package org.algorithm.leetcode.daily.tree;

import org.algorithm.tree.TreeNode;

import java.util.Arrays;

//从前序中序遍历中恢复二叉树
public class BuildTree {

    public static void main(String[] args) {
        TreeNode treeNode = new BuildTree().buildTree(new int[]{3, 9, 10, 20, 15, 7}, new int[]{10, 9, 3, 15, 20, 7});
        System.out.println(treeNode);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length == 0) {
            return null;
        }
        if (preorder.length == 1) {
            return new TreeNode(preorder[0]);
        }
        int val = preorder[0];
        TreeNode root = new TreeNode(val);
        int indexIn = -1;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == val) {
                indexIn = i;
                break;
            }
        }
        int[] inOrderLeft = Arrays.copyOfRange(inorder, 0, indexIn);
        int[] inOrderRight = Arrays.copyOfRange(inorder, indexIn + 1, inorder.length);
        int[] preOrderLeft = Arrays.copyOfRange(preorder, 1, inOrderLeft.length + 1);
        int[] preOrderRight = Arrays.copyOfRange(preorder, preOrderLeft.length + 1, preorder.length);

        TreeNode left = buildLeft(preOrderLeft, inOrderLeft);
        TreeNode right = buildRight(preOrderRight, inOrderRight);
        root.left = left;
        root.right = right;
        return root;
    }

    private TreeNode buildLeft(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder);
    }

    private TreeNode buildRight(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder);
    }

    private void order(TreeNode node) {
        if (node == null) return;
        System.out.println("pre-" + node.val); // 中 左 右
        order(node.left);
        System.out.println("in-" + node.val); // 左 中 右
        order(node.right);
    }
}
