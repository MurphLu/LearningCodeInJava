package org.algorithm.leetcode.topHot100;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> contMap = new HashMap<>();
        for(int num : nums) {
            contMap.put(num, contMap.getOrDefault(num, 0) + 1);
            if (contMap.get(num) > nums.length / 2) {
                return num;
            }
        }
        return 0;
    }
}
