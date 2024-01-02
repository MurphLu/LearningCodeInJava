package org.algorithm.leetcode.daily;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCost {
    public static void main(String[] args) {
        int[] nums = new int[]{733,200,839,515,852,615,8,584,250,337};
        int x = 537;
        long l = new MinCost()
                .minCost(nums, x);
        System.out.println(l);
    }
    public long minCost(int[] nums, int x) {
        int n = nums.length;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(i -> nums[i]));

        long res = -1;
        for (int i = 0; i < nums.length; i++) {
            queue.add(i);
            long sum = nums[queue.peek()];
            int index = 1;
            while (index < n) {
                queue.remove(index - 1);
                queue.add((index + i) % n);
                index ++;
                sum += nums[queue.peek()];
            }
            if (i == 0) {
                res = sum;
            } else {
                res = Math.min(res, sum + (long)x *(long)i);
            }
            queue.remove(index-1);
            queue.add(i);
        }
        return res;
    }


    private long withoutQueue(int[] nums, int x) {
        int n = nums.length;
        int[] minMap = new int[n];
        System.arraycopy(nums, 0, minMap, 0, n);
        long res = getSum(minMap);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                minMap[j] = Math.min(minMap[j], nums[(i + j) % n]);
            }
            res = Math.min(res, getSum(minMap) + (long)i*(long)x);
        }
        return res;
    }

    private long getSum(int[] minMap) {
        long sum = 0;
        for (int num: minMap) {
            sum += num;
        }
        return sum;
    }
//    private long process(int step, int[] nums) {
//        int n = nums.length;
//
//        for (int i = 0; i < step + 1; i++) {
//            queue.add(i);
//        }
//        long sum = nums[queue.peek()];
//        int index = 1;
//        while (index < n) {
//            queue.remove(index - 1);
//            queue.add((index + step) % n);
//            index ++;
//            sum += nums[queue.peek()];
//        }
//        return sum;
//    }
}
