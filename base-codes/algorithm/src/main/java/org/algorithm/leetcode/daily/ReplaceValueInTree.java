package org.algorithm.leetcode.daily;

import org.algorithm.tree.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ReplaceValueInTree {
    public static void main(String[] args) {
        TreeNode t = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3, new TreeNode(6), new TreeNode(7)));
        new ReplaceValueInTree().replaceValueInTree(t);
    }
    public TreeNode replaceValueInTree(TreeNode root) {
        Map<Integer, Integer> levelSum = new HashMap<>();
        travel(root, 0, levelSum);
        replace(root, 0, levelSum);
        return root;
    }

    private void travel(TreeNode root, int level, Map<Integer, Integer> levelSum) {
        if (root == null) {
            return;
        }
        Integer val = levelSum.getOrDefault(level, 0) + root.val;
        levelSum.put(level, val);
        travel(root.left, level + 1, levelSum);
        travel(root.right, level + 1, levelSum);
    }

    private void replace(TreeNode root, int level, Map<Integer, Integer> levelSum) {
        if (root == null) {
            return;
        }
        if (level == 0) {
            root.val = 0;
        }
        int childSum = (root.left == null ? 0 : root.left.val) + (root.right == null ? 0 : root.right.val);
        Integer nextLevelSum = levelSum.get(level + 1);
        if (root.left != null) {
            root.left.val = nextLevelSum - childSum;
        }
        if (root.right != null) {
            root.right.val = nextLevelSum - childSum;
        }
        replace(root.left, level + 1, levelSum);
        replace(root.right, level + 1, levelSum);
    }
}
