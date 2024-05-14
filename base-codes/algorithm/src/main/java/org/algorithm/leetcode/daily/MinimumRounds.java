package org.algorithm.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个下标从 0 开始的整数数组 tasks ，其中 tasks[i] 表示任务的难度级别。
 * 在每一轮中，你可以完成 2 个或者 3 个 相同难度级别 的任务。
 *
 * 返回完成所有任务需要的 最少 轮数，如果无法完成所有任务，返回 -1 。
 *
 * 统计每种任务个数，
 * 大于 1 的话需要：int count = cnt / 3 + (cnt % 3 == 0 ? 0 : 1)
 * 等于 1 那么一定无法完成，直接返回 -1
 */
public class MinimumRounds {
    public int minimumRounds(int[] tasks) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : tasks) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        int round = 0;
        for(int i : map.values()) {
            int temp = (i == 1 ? -1 : (i/3 + (i%3 == 0 ? 0 : 1)));
            if (temp == -1){return -1;}
            round += temp;
        }
        return round;
    }
}
