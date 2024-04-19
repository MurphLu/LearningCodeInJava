package org.algorithm.leetcode.daily.needToAna;

import java.util.Arrays;

/**
 * 给你一个整数 hoursBefore ，表示你要前往会议所剩下的可用小时数。要想成功抵达会议现场，你必须途经 n 条道路。道路的长度用一个长度为 n 的整数数组 dist 表示，其中 dist[i] 表示第 i 条道路的长度（单位：千米）。另给你一个整数 speed ，表示你在道路上前进的速度（单位：千米每小时）。
 *
 * 当你通过第 i 条路之后，就必须休息并等待，直到 下一个整数小时 才能开始继续通过下一条道路。注意：你不需要在通过最后一条道路后休息，因为那时你已经抵达会议现场。
 *
 * 例如，如果你通过一条道路用去 1.4 小时，那你必须停下来等待，到 2 小时才可以继续通过下一条道路。如果通过一条道路恰好用去 2 小时，就无需等待，可以直接继续。
 * 然而，为了能准时到达，你可以选择 跳过 一些路的休息时间，这意味着你不必等待下一个整数小时。注意，这意味着与不跳过任何休息时间相比，你可能在不同时刻到达接下来的道路。
 *
 * 例如，假设通过第 1 条道路用去 1.4 小时，且通过第 2 条道路用去 0.6 小时。跳过第 1 条道路的休息时间意味着你将会在恰好 2 小时完成通过第 2 条道路，且你能够立即开始通过第 3 条道路。
 * 返回准时抵达会议现场所需要的 最小跳过次数 ，如果 无法准时参会 ，返回 -1 。
 *
 *
 * 我们用 f[i][j]f[i][j]f[i][j] 表示经过了 dist[0]\textit{dist}[0]dist[0] 到 dist[i−1]\textit{dist}[i-1]dist[i−1] 的 iii 段道路，并且跳过了 jjj 次的最短用时。
 *
 * 在进行状态转移时，我们考虑最后一段道路 dist[i−1]\textit{dist}[i-1]dist[i−1] 是否选择跳过：
 *
 * 如果没有跳过，那么状态转移方程为：
 *
 * f[i][j]=⌈f[i−1][j]+dist[i−1]speed⌉f[i][j] = \left\lceil f[i-1][j] + \frac{\textit{dist}[i-1]}{\textit{speed}} \right\rceil
 * f[i][j]=⌈f[i−1][j]+
 * speed
 * dist[i−1]
 * ​
 *  ⌉
 * 其中 ⌈x⌉\lceil x \rceil⌈x⌉ 表示将 xxx 向上取整。对于最后一段道路，我们无需等待到下一个整数小时，但由于题目中给定的 hoursBefore\textit{hoursBefore}hoursBefore 是一个整数，因此在状态转移方程中向上取整是不会影响结果的。
 *
 * 如果跳过，那么状态转移方程为：
 *
 * f[i][j]=f[i−1][j−1]+dist[i−1]speedf[i][j] = f[i-1][j-1] + \frac{\textit{dist}[i-1]}{\textit{speed}}
 * f[i][j]=f[i−1][j−1]+
 * speed
 * dist[i−1]
 * ​
 *
 * 由于我们到达的时间尽可能早，因此需要选择这两种转移中的较小值，即：
 *
 * f[i][j]=min⁡{⌈f[i−1][j]+dist[i−1]speed⌉,f[i−1][j−1]+dist[i−1]speed}f[i][j] = \min \left\{ \left\lceil f[i-1][j] + \frac{\textit{dist}[i-1]}{\textit{speed}} \right\rceil, f[i-1][j-1] + \frac{\textit{dist}[i-1]}{\textit{speed}}\right\}
 * f[i][j]=min{⌈f[i−1][j]+
 * speed
 * dist[i−1]
 * ​
 *  ⌉,f[i−1][j−1]+
 * speed
 * dist[i−1]
 * ​
 *  }
 * 需要注意的是，当 j=0 时，我们不能通过「跳过」进行转移；
 * 当 j=i 时，我们不能通过「没有跳过」进行转移；
 * 当 j>i 时，我们无法在 i 段道路内跳过超过 i 次，对应的状态不合法。
 *
 * 当我们计算完所有状态的值后，我们只需要找到最小的 j，使得
 * f[n][j]≤hoursBeforef[n][j] \leq \textit{hoursBefore}f[n][j]≤hoursBefore，
 * 这个 j 即为最少需要跳过的次数。如果不存在这样的 j，那么返回 −1。
 *
 * 动态规划的细节
 *
 * 动态规划的边界条件为 f[0][0]=0f[0][0] = 0f[0][0]=0，表示初始（未走过任何道路）时的时间为 000。
 *
 * 由于状态转移方程中的取值为 min⁡\minmin，因此我们可以将除了 f[0][0]f[0][0]f[0][0] 以外所有的状态置为一个极大值 ∞\infty∞，方便进行转移。
 *
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/minimum-skips-to-arrive-at-meeting-on-time/solutions/802990/minimum-skips-to-arrive-at-meeting-on-ti-dp7v/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class MinSkips {
    static final double EPS = 1e-7;
    // 极大值
    static final double INFTY = 1e20;

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        double[][] f = new double[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(f[i], INFTY);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j <= i; ++j) {
                if (j != i) {
                    f[i][j] = Math.min(f[i][j], Math.ceil(f[i - 1][j] + (double) dist[i - 1] / speed - EPS));
                }
                if (j != 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + (double) dist[i - 1] / speed);
                }
            }
        }
        for (int j = 0; j <= n; ++j) {
            if (f[n][j] < hoursBefore + EPS) {
                return j;
            }
        }
        return -1;
    }

    public int minSkips1(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        double[][] f = new double[n + 1][n + 1];
        for (int i = 0; i <= n; ++i) {
            Arrays.fill(f[i], INFTY);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            process(dist, speed, i, 0, f);
        }
        for (int j = 0; j <= n; ++j) {
            if (f[n][j] < hoursBefore + EPS) {
                return j;
            }
        }
        return -1;
    }


    private void process(int[] dist, int speed, int idx, int skip, double[][] dp) {
        if (idx > skip) {
            dp[idx][skip] = Math.min(dp[idx][skip], Math.ceil(dp[idx - 1][skip] + (double) dist[idx - 1]/speed - EPS));
        }
        if (skip != 0) {
            dp[idx][skip] = Math.min(dp[idx][skip], dp[idx - 1][skip - 1] + (double) dist[idx - 1] / speed);
        }
        if (skip >= idx) {return;}
        while (skip < idx) {
            process(dist, speed, idx, ++skip, dp);
        }
    }


}
