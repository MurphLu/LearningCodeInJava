package org.algorithm.leetcode.topHot100;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelTravel {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode currentLast = root;
        TreeNode nextLast = null;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> level = new ArrayList<>();
        while (queue.size() > 0) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextLast = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextLast = cur.right;
            }
            level.add(cur.val);
            if (cur == currentLast) {
                currentLast = nextLast;
                res.add(level);
                level = new ArrayList<>();
            }
        }
        return res;
    }
}
