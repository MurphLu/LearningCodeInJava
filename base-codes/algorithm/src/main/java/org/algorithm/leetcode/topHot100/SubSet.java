package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class SubSet {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        int index = 0;
        while (index < nums.length) {
            int curNum = nums[index++];
            List<List<Integer>> tempLists = new ArrayList<>();
            for (List<Integer> list: lists) {
                List<Integer> needToAdd = new ArrayList<>(list);
                needToAdd.add(curNum);
                tempLists.add(needToAdd);
            }
            lists.addAll(tempLists);
        }
        return lists;
    }

}
