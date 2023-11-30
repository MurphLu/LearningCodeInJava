package org.algorithm.tree.questions;

import org.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 */
public class RightSideView {
    public static void main(String[] args) {

    }

    public List<Integer> rightSideView(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(root==null) return result;
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode cur = root;
        TreeNode nextRight = null;
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null) {
                nextRight = node.left;
                q.add(node.left);
            }
            if (node.right != null) {
                nextRight = node.right;
                q.add(node.right);
            }
            if (node == cur) {
                result.add(cur.val);
                cur = nextRight;
            }
        }
        return result;
    }
}
