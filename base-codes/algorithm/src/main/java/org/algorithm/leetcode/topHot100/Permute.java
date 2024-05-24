package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        process(nums, nums.length, new ArrayList<>(), result);
        return result;
    }

    public void process(int[] nums, int end, List<Integer> res, List<List<Integer>> result) {
        if (end == 0) {
            result.add(res);
        }
        Set<Integer> showed = new HashSet<>();
        for (int i = 0; i < end; i++) {
            int cur = nums[i];
            if (!showed.contains(cur)) {
                int[] next = nums.clone();
                List<Integer> resNew = new ArrayList<>(List.copyOf(res));
                resNew.add(cur);
                swap(next, i, end - 1);
                showed.add(cur);
                process(next, end - 1, resNew, result);
            }

        }
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
