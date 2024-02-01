package org.algorithm.leetcode.daily;

import java.util.*;
import java.util.function.LongToIntFunction;

public class NumsGame {
    public static void main(String[] args) {

        System.out.println(Arrays.toString(new NumsGame().numsGame(new int[]{3, 4, 5, 1, 6, 7})));
        // 3,3,3,-2,2,2
    }
    public int[] numsGame(int[] nums) {
        int[] newNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = nums[i] - i;
        }
        Queue<Integer> bigRoot = new PriorityQueue<>((a,b)->b-a);
        long bigSum = 0;
        Queue<Integer> smallRoot = new PriorityQueue<>();
        long smallSum = 0;
        bigRoot.add(newNums[0]);
        bigSum = newNums[0];
        long[] res = new long[newNums.length];
        res[0] = 0;
        for (int i = 1; i < newNums.length; i++) {
            int cur = newNums[i];
            if (cur <= bigRoot.peek()){
                bigRoot.add(cur);
                bigSum += cur;
            } else {
                smallRoot.add(cur);
                smallSum += cur;
            }
            while (bigRoot.size() - smallRoot.size() > 1) {
                int move = bigRoot.poll();
                bigSum -= move;
                smallSum += move;
                smallRoot.add(move);

            }
            while (smallRoot.size() -  bigRoot.size() > 1) {
                int move = smallRoot.poll();
                bigSum += move;
                smallSum -= move;
                bigRoot.add(move);
            }
            long resCur;
            if(smallRoot.size() == bigRoot.size()){
                resCur = smallSum - bigSum;
            } else if (smallRoot.size() > bigRoot.size()) {
                resCur = smallSum - smallRoot.peek() - bigSum;
            } else {
                resCur = smallSum - (bigSum - bigRoot.peek());
            }
            res[i] = resCur;
        }
        return Arrays.stream(res).mapToInt(l -> (int)(l % 1000000007)).toArray();
    }
}
