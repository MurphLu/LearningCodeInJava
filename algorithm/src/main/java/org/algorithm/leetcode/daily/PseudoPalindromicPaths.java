package org.algorithm.leetcode.daily;

import org.algorithm.leetcode.topHot100.TreeNode;

import java.util.*;

public class PseudoPalindromicPaths {
    public int pseudoPalindromicPaths (TreeNode root) {
        return 0;
    }

    public int process(TreeNode root, Map<Integer, Integer> map) {
        int key = root.val;
        if (map.containsKey(key) && map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        if (root.left == null && root.right == null) {
            int count = 0;
            for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
                if(entry.getValue() % 2 != 0) {
                    count ++;
                }
            }
            if (count <= 1) {
                return 1;
            }
            return 0;
        }
        int count = 0;
        count += root.left == null ? 0 : process(root.left, new HashMap<>(map));
        count += root.right == null ? 0 : process(root.right, new HashMap<>(map));
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
