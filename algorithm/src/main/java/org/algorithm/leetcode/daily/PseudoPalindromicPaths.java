package org.algorithm.leetcode.daily;

import org.algorithm.leetcode.topHot100.TreeNode;

import java.util.*;

public class PseudoPalindromicPaths {
    public int pseudoPalindromicPaths (TreeNode root) {
        return 0;
    }

    public int process(TreeNode root, Map<Integer, Integer> map) {
        int key = root.val;
        map.put(key, map.getOrDefault(key, 0) + 1);
        if (root.left == null && root.right == null && check(map)) {
            return 1;
        }
        int count = 0;
        if (root.left != null) {
            count += process(root.left, map);
            map.put(root.left.val, map.get(root.left.val) - 1);
        }
        if (root.right != null) {
            count += process(root.right, map);
            map.put(root.right.val, map.get(root.right.val) - 1);
        }
        return count;
    }

    private boolean check(Map<Integer, Integer> map) {
        int count = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if(entry.getValue() % 2 != 0) {
                count ++;
            }
        }
        return count <= 1;
    }
}
