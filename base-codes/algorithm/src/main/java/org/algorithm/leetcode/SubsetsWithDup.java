package org.algorithm.leetcode;

import java.util.*;

/**
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的 子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
public class SubsetsWithDup {
    public static void main(String[] args) {
        new SubsetsWithDup().subsetsWithDup(new int[]{
           1,2,2,2,4
        });
    }
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i: nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        Integer[] integers = map.keySet().toArray(new Integer[0]);
        List<List<Integer>> rec = new ArrayList<>();
        rec.add(new ArrayList<>());

        process(integers, 0, rec);
        System.out.println(rec);
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            if (entry.getValue() > 1) {
                process(entry.getValue(), entry.getKey(), rec);
            }
        }
        System.out.println(rec);
        return rec;
    }

    private void process(Integer[] integers, int idx, List<List<Integer>> rec) {
        if (idx == integers.length) {
            return;
        }
        List<List<Integer>> newRecs = new ArrayList<>();
        for(List<Integer> record : rec) {
            List<Integer> newList = new ArrayList<>(record);
            newList.add(integers[idx]);
            newRecs.add(newList);
        }
        rec.addAll(newRecs);
        process(integers, idx + 1, rec);
    }

    private void process(int count, int num, List<List<Integer>> rec) {
        List<List<Integer>> newRecs = new ArrayList<>();
        for(List<Integer> record: rec) {

            if(record.contains(num)) {
                int tempCount = count;
                List<Integer> newList = new ArrayList<>(record);
                while (--tempCount > 0) {
                    newList.add(num);
                    newRecs.add(new ArrayList<>(newList));
                }
            }
        }
        rec.addAll(newRecs);
    }

    private List<Integer> copy(List<Integer> list) {
        return new ArrayList<>(list);
    }
}
