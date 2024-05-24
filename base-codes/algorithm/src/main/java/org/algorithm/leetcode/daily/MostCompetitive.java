package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 给你一个整数数组 nums 和一个正整数 k ，返回长度为 k 且最具 竞争力 的 nums 子序列。
 *
 * 数组的子序列是从数组中删除一些元素（可能不删除元素）得到的序列。
 *
 * 在子序列 a 和子序列 b 第一个不相同的位置上，
 * 如果 a 中的数字小于 b 中对应的数字，
 * 那么我们称子序列 a 比子序列 b（相同长度下）更具 竞争力 。
 * 例如，[1,3,4] 比 [1,3,5] 更具竞争力，在第一个不相同的位置，也就是最后一个位置上， 4 小于 5 。
 *
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 1 <= k <= nums.length
 *
 *  3
 *  1,2,3,4,5,6,7
 */
public class MostCompetitive {
    public static void main(String[] args) {
        new MostCompetitive().mostCompetitive(new int[]{71,18,52,29,55,73,24,42,66,8,80,2}, 4);
    }

    public int[] mostCompetitive(int[] nums, int k) {
        LinkedList<Integer> stack = new LinkedList<Integer>();
        int remain = nums.length;
        int n = nums.length;
        while (remain >= 1) {
            int num = nums[n - remain];
            if (stack.isEmpty()) {
                stack.add(num);
                remain--;
                continue;
            }
            while (!stack.isEmpty() && stack.size() + remain > k && stack.getLast() > num) {
                stack.removeLast();
            }
            if (stack.size() < k) {
                stack.add(num);
            }
            remain--;
        }
        int[] res = new int[k];
        for (int i = res.length - 1; i >=0; i--) {
            res[i] = stack.removeLast();
        }
        return res;
    }

}
