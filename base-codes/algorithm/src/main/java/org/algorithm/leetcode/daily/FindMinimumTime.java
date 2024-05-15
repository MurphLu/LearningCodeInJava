package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 你有一台电脑，它可以 同时 运行无数个任务。给你一个二维整数数组 tasks ，
 * 其中 tasks[i] = [starti, endi, durationi] 表示第 i 个任务需要在 闭区间 时间段 [starti, endi]
 * 内运行 durationi 个整数时间点（但不需要连续）。
 *
 * 当电脑需要运行任务时，你可以打开电脑，如果空闲时，你可以将电脑关闭。
 *
 * 请你返回完成所有任务的情况下，电脑最少需要运行多少秒。
 *
 * 1 <= tasks.length <= 2000
 * tasks[i].length == 3
 * 1 <= starti, endi <= 2000
 * 1 <= durationi <= endi - starti + 1
 */
public class FindMinimumTime {


    public int findMinimumTime(int[][] tasks) {
        // 根据结束时间排序
        Arrays.sort(tasks, (a, b) -> a[1] - b[1]);
        int[] vis = new int[2010];
        int ans = 0;
        for (var task : tasks) {
            int start = task[0], end = task[1], duration = task[2];
            // 第一个任务进来的时候由于还没有任何任务在该时间段执行过，所以 duration 不会有变化
            // 但是当之后的任务进来时，之后的任务与之前的任务重叠的部分就会从 duration 种减掉
            for (int i = start; i <= end; ++i) {
                duration -= vis[i];
            }

            // 此时 duration 已经减掉了重叠部分，那么接下来则从后向前执行，有任何任务在某一时间点执行过则忽略，否则 总时间+1，并设置状态，之后任务则放入该时间执行
            for (int i = end; i >= start && duration > 0; --i) {
                if (vis[i] == 0) {
                    --duration;
                    ans += vis[i] = 1;
                }
            }
        }
        return ans;
    }
}
