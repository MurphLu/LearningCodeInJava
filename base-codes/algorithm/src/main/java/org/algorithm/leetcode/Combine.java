package org.algorithm.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 */
public class Combine {
    public static void main(String[] args) {
        System.out.println(new Combine().combine(6,4));
    }
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> rec = new ArrayList<>();
        process(n, 1, k, new ArrayList<>(), rec);
        return rec;
    }

    private void process(int n, int s, int k, List<Integer> res, List<List<Integer>> rec) {
        if (k == 0) {
            rec.add(res);
        }
        if (k < 0) {
            return;
        }
        while (s+k <= n+1) {
            List<Integer> integers = new ArrayList<>(res);
            integers.add(s);
            s++;
            process(n, s, k-1, integers, rec);
        }
    }
}
