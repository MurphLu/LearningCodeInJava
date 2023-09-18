package org.algorithm.greedy;


import org.algorithm.tree.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入:
 * 正数数组costs正数数组profits
 * 正数k
 * 正数m
 * 含义:
 * costs[i] 表示i号项目的花费
 * profits[i] 表示i号项目在扣除花费之后还能挣到的钱(利润)
 * k表示你只能串行的最多做k个项目
 * m表示你初始的资金
 * 说明:
 * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目
 * 输出:
 * 你最后获得的最大钱数
 *
 * 下一个项目 = 当前资金允许的利润最大的项目
 */
public class MaxProfits {
    static class Project {
        int cost;
        int profits;

        public Project(int cost, int profits) {
            this.cost = cost;
            this.profits = profits;
        }
    }

    public static void main(String[] args) {

    }

    public static int findMaxProfits(int k, int m, int[] profit, int[] cost) {
        PriorityQueue<Project> minCostQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        PriorityQueue<Project> maxProfitQueue = new PriorityQueue<>((o1, o2) -> o2.profits - o1.profits);

        for (int i = 0; i < profit.length; i++) {
            minCostQueue.add(new Project(cost[i], profit[i]));
        }
        for (int i = 0; i < k; i++) {
            while (!minCostQueue.isEmpty() && minCostQueue.peek().cost < m) {
                Project poll = minCostQueue.poll();
                maxProfitQueue.add(poll);
            }
            if(maxProfitQueue.isEmpty()) {
                return m;
            }
            m += maxProfitQueue.poll().profits;
        }
        return m;
    }
}
