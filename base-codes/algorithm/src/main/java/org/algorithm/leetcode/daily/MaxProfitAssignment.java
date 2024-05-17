package org.algorithm.leetcode.daily;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 你有 n 个工作和 m 个工人。给定三个数组： difficulty, profit 和 worker ，其中:
 *
 * difficulty[i] 表示第 i 个工作的难度，profit[i] 表示第 i 个工作的收益。
 * worker[i] 是第 i 个工人的能力，即该工人只能完成难度小于等于 worker[i] 的工作。
 * 每个工人 最多 只能安排 一个 工作，但是一个工作可以 完成多次 。
 *
 * 举个例子，如果 3 个工人都尝试完成一份报酬为 $1 的同样工作，那么总收益为 $3 。如果一个工人不能完成任何工作，他的收益为 $0 。
 * 返回 在把工人分配到工作岗位后，我们所能获得的最大利润 。
 *
 * n == difficulty.length
 * n == profit.length
 * m == worker.length
 * 1 <= n, m <= 10^4
 * 1 <= difficulty[i], profit[i], worker[i] <= 10^5
 *
 */
public class MaxProfitAssignment {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int wn = worker.length;
        int[][] info = new int[n][2];
        for (int i = 0; i < n; i++) {
            info[i] = new int[]{difficulty[i], profit[i]};
        }
        Arrays.sort(info, Comparator.comparingInt(a -> a[0]));
        int maxProfit = 0;
        for(int[] in : info) {
            maxProfit = Math.max(in[1], maxProfit);
            in[1] = maxProfit;
        }
        Arrays.sort(worker);
        int count = 0;
        int infoIdx = 0;
        int capIdx = 0;
        while (capIdx < wn) {
            int cap = worker[capIdx];
            while (infoIdx < n && info[infoIdx][0] <= cap) {
                infoIdx++;
            }
            if (infoIdx - 1 >= 0) {
                count += info[infoIdx - 1][1];
            }
            capIdx++;
        }
        return count;
    }
}
