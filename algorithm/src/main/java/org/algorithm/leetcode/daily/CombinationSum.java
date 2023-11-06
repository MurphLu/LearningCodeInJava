package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.List;
//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
//
//candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
//
//对于给定的输入，保证和为 target 的不同组合数少于 150 个。
public class CombinationSum {
    public static void main(String[] args) {
        new CombinationSum().combinationSum(new int[]{8,7,4,3}, 11);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        process(candidates, new ArrayList<>(), target, result);

        System.out.println(result);
        return result;
    }


    private void process(int[] candidates, List<Integer> cur, int target, List<List<Integer>> result) {
        if (cur.size() == 1) {
            System.out.println(cur.get(0));
        }
        for (int i: candidates) {
            if (!cur.isEmpty() && cur.get(cur.size() - 1) > i) {
                continue;
            }
            if (target - i < 0) { continue;}
            if (target - i == 0) {
                List<Integer> list = new ArrayList<>(cur);
                list.add(i);
                result.add(list);
                continue;
            }
            List<Integer> list = new ArrayList<>(cur);
            list.add(i);
            process(candidates, list, target - i, result);
        }
    }

}
