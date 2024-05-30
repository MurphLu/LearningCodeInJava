package org.algorithm.leetcode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 *
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 *
 * 返回 你能获得的 最大 利润 。
 */
public class MaxProfit {
    public static void main(String[] args) {
        int res = new MaxProfit().maxProfit(new int[]{
                7,1,5,3,6,4
        });
        System.out.println(res);
    }
    public int maxProfit(int[] prices) {
        LinkedList<Integer> stack = new LinkedList<>();
        int startIdx = 0;
        while (startIdx < prices.length - 1) {
            if(prices[startIdx] >= prices[startIdx+1]) {
                startIdx++;
            } else {
                break;
            }
        }
        int res = 0;
        for (int i = startIdx; i < prices.length; i++) {
            int price = prices[i];
            if (stack.isEmpty()) {
                stack.add(price);
                continue;
            }
            if (stack.getLast() <= price) {
                stack.add(price);
            }
            if (stack.getLast() > price) {
                res += clearStack(stack);
                stack.add(price);
            }
        }
        if (!stack.isEmpty()) {
            res += clearStack(stack);
        }
        return res;
    }

    private int clearStack(LinkedList<Integer> stack) {
        int res = 0;
        while (!stack.isEmpty()) {
            int top = stack.removeLast();
            if (!stack.isEmpty()) {
                res += (top - stack.getLast());
            }
        }
        return res;
    }
}
