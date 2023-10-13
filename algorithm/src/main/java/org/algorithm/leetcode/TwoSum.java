package org.algorithm.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// 两数之和，数组中两个数组等于 target，返回 index
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int next = target - nums[i];
            if (map.containsKey(next)) {
                return new int[]{i, map.get(next)};
            }
            map.put(nums[i], i);
        }
//        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()) {
//            int cur = entry.getKey();
//            int val = target - cur;
//
//            if (cur != val && map.containsKey(val)) {
//                return new int[]{entry.getValue().get(0), map.get(val).get(0)};
//            }
//            if (cur == val && entry.getValue().size() > 1) {
//                return new int[]{entry.getValue().get(0), entry.getValue().get(0)};
//            }
//        }
        return new int[]{};
    }
}
