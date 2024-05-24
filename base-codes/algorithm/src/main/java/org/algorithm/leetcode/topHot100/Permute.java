package org.algorithm.leetcode.topHot100;

import java.util.*;

public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for(int num: nums) {
            numbers.add(num);
        }
        process(numbers, new ArrayList<>(), result);
        return result;
    }

    public void process(List<Integer> nums, List<Integer> rec, List<List<Integer>> result) {
        if (nums.isEmpty()) {
            result.add(rec);
            return;
        }
        int n = nums.size();

        for (int i = 0; i < n; i++) {
            List<Integer> newRec = new ArrayList<>(rec);
            List<Integer> newNums = new ArrayList<>(nums);
            int num = newNums.remove(i);
            newRec.add(num);
            process(newNums, newRec, result);
        }
    }
}
