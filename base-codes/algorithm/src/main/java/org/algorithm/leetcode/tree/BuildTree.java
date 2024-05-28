package org.algorithm.leetcode.tree;

import com.sun.source.tree.Tree;
import org.algorithm.tree.TreeNode;

import java.util.Arrays;

public class BuildTree {
    // 左中右
    // 左右中
    // inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
    public static void main(String[] args) {
        TreeNode treeNode = new BuildTree().buildTree(
                new int[]{9, 3, 15, 20, 7},
                new int[]{9, 15, 7, 20, 3});
        System.out.println(treeNode);
    }
    /**
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     *
     * 1 <= inorder.length <= 3000
     * postorder.length == inorder.length
     * -3000 <= inorder[i], postorder[i] <= 3000
     * inorder 和 postorder 都由 不同 的值组成
     * postorder 中每一个值都在 inorder 中
     * inorder 保证是树的中序遍历
     * postorder 保证是树的后序遍历
     *
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length, postorder, 0, postorder.length);

    }

    public TreeNode build(int[] inorder, int inFrom, int inSize, int[] postorder, int postFrom, int postSize) {
        if (inSize <= 0 || postSize <= 0) {
            return null;
        }
        int root = postorder[postFrom + postSize - 1];
        TreeNode treeNode = new TreeNode(root);
        if (inSize == 1 && postSize == 1) {
            return treeNode;
        }
        int rootInInOrder = 0;
        for (int i = inFrom; i < inFrom + inSize; i++) {
            if (inorder[i] == root) {
                rootInInOrder = i;
            }
        }
        int leftSize = rootInInOrder - inFrom;
        treeNode.left = build(inorder, inFrom, leftSize, postorder, postFrom, leftSize);
        int rightSize = inSize - leftSize - 1;
        treeNode.right = build(inorder, inFrom+leftSize+1, rightSize, postorder, postFrom + leftSize, rightSize);
        return treeNode;
    }
}
