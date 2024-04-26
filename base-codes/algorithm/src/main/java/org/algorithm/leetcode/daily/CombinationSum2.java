package org.algorithm.leetcode.daily;

import java.util.*;
import java.util.stream.IntStream;

public class CombinationSum2 {
    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum2().combinationSum2(
                new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
                30
        );
        System.out.println(lists);
    }
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Set<String> record = new HashSet<>();
        Arrays.sort(candidates);
        if (candidates[0] == candidates[candidates.length-1]) {
            int val = candidates[0];
            if(target % val == 0 && target/val <= candidates.length) {
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < target/val; i++) {
                    list.add(val);
                }
                res.add(list);
            }
            return res;
        }
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            } else if (candidates[i] == target) {
                String s = String.format("%d", candidates[i]);
                if (record.add(s)) {
                    res.add(Arrays.asList(candidates[i]));
                }

            }
            String rec = candidates[i]+"";

            process(candidates, i, target - candidates[i], rec, res, record);
        }
        return res;
    }

    private void process(int[] candidates, int idx, int target, String cur, List<List<Integer>> res, Set<String> record) {
        if(target < 0) return;
        for (int i = idx + 1; i < candidates.length; i++) {
            String curStr = cur + "_" + candidates[i];
            int nextTarget = target - candidates[i];
            if (nextTarget == 0) {
                int[] temp = Arrays.stream(curStr.split("_")).mapToInt(Integer::parseInt).toArray();
                Arrays.sort(temp);
                List<Integer> list = new ArrayList<>();
                StringBuilder tempStr = new StringBuilder();
                for (int k : temp) {
                    list.add(k);
                    tempStr.append(k);
                }

                if(record.add(tempStr.toString())){
                    res.add(list);
                }
            } else if (nextTarget > 0) {
                process(candidates, i, nextTarget, curStr, res, record);
            }
        }
    }
}
