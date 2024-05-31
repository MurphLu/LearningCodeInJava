package org.algorithm.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


public class MaxProfit {
    public static void main(String[] args) {
        int res = new MaxProfit().maxProfit2(new int[]{
                7,6,4,3,1
        });
        System.out.println(res);
    }

    /**
     * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     *
     * 返回 你能获得的 最大 利润 。
     */
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

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）
     * @param prices
     * @return
     *
     * 1,4,2,5
     * 1,2,3,4,5
     * 1,3,  2,4,  0,4,9, 8, 20
     *  2     2      9      12
     */


    public int maxProfit2(int[] prices) {
        // 第一次买入，第一次卖出，第二次买入，第二次卖出
        int f1 = -prices[0], f2 = 0, f3 = -prices[0], f4 = 0;
        for (int i = 1; i < prices.length; ++i) {
            f1 = Math.max(f1, -prices[i]);
            f2 = Math.max(f2, f1 + prices[i]);
            f3 = Math.max(f3, f2 - prices[i]);
            f4 = Math.max(f4, f3 + prices[i]);
        }
        return f4;
    }
}
