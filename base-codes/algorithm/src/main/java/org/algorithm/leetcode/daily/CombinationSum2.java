package org.algorithm.leetcode.daily;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 *
 * 注意：解集不能包含重复的组合。
 */
public class CombinationSum2 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum2().combinationSum2(
                new int[]{10,1,2,7,6,1,5},
                8
        );
        System.out.println(lists);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        Arrays.sort(candidates);

        for (int val : candidates) {
            if (list.isEmpty() || list.get(list.size() - 1) != val) {
                list.add(val);
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        List<List<Integer>> res = new ArrayList<>();
        process(map, list, 0, "", target, res);
        return res;
    }

    private void process(Map<Integer, Integer> map, List<Integer> list, int currentIdx, String cur, int target, List<List<Integer>> res) {
        if (currentIdx >= list.size()) {
            return;
        }
        int currVal = list.get(currentIdx);
        int count = map.get(currVal);
        if (target < currVal) {
            return;
        }
        for (int i = count; i >= 0 ; i--) {
            StringBuilder builder = new StringBuilder();
            builder.append(cur);
            for (int j = 0; j < i; j++) {
                builder.append(currVal);
                builder.append(",");
            }
            int tempTarget = target - currVal * i;
            if (tempTarget < 0) {
                continue;
            }
            if (tempTarget == 0) {
                String resStr = builder.toString();
                int[] t = Arrays.stream(resStr.substring(0, resStr.length() - 1).split(",")).mapToInt(Integer::parseInt).toArray();
                List<Integer> tt = new ArrayList<>();
                for (int k : t) {
                    tt.add(k);
                }
                res.add(tt);
                continue;
            }
            process(map, list, currentIdx+1, builder.toString(), tempTarget, res);
        }
    }
}
