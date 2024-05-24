package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 *
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 *
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 */
public class CombinationSum {
    public static void main(String[] args) {
        new CombinationSum().combinationSum(new int[]{2,3,6,7}, 7);
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        process(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void process(int[] candidates, int idx, int target, List<Integer> rec, List<List<Integer>> result) {
        if (target == 0) {
            result.add(rec);
            return;
        }
        if (idx == candidates.length || target < 0) {
            return;
        }
        int num = candidates[idx];
        int count = target / num;

        while (count >= 0) {
            List<Integer> nextRec = new ArrayList<>(rec);
            int tempN = count;
            while (tempN > 0) {
                nextRec.add(num);
                tempN--;
            }
            process(candidates, idx+1, target - num*count, nextRec, result);
            count--;
        }
        // 不包含当前数
//        process(candidates, idx+1, target, rec, result);
//        List<Integer> nextRoundWithThisNum = new ArrayList<>(rec);
//        nextRoundWithThisNum.add(num);
//        process(candidates, idx, target - num, nextRoundWithThisNum, result);
//        List<Integer> nextRoundWithNextNum = new ArrayList<>(rec);
//        nextRoundWithNextNum.add(num);
//        process(candidates, idx+1, target - num, nextRoundWithNextNum, result);
    }
}
