package org.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个 无重叠的 ，按照区间起始端点排序的区间列表 intervals，
 * 其中 intervals[i] = [starti, endi] 表示第 i 个区间的开始和结束，并且 intervals 按照 starti 升序排列。同样给定一个区间 newInterval = [start, end] 表示另一个区间的开始和结束。
 *
 * 在 intervals 中插入区间 newInterval，使得 intervals 依然按照 starti 升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。
 *
 * 返回插入之后的 intervals。
 *
 * 注意 你不需要原地修改 intervals。你可以创建一个新数组然后返回它。
 */
public class InsertRange {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        int mergedStart = newInterval[0];
        int mergedEnd = newInterval[1];
        List<int[]> list = new ArrayList<>();

        int state = 0; // 0: before, 1: merging, 2: after
        for (int[] range : intervals) {
            int rangeStart = range[0];
            int rangeEnd = range[1];
            if (rangeStart > mergedEnd) {
                if (state < 2) {
                    list.add(newInterval);
                    state = 2;
                }
                list.add(range);
            } else if (rangeEnd < mergedStart) {
                list.add(range);
            } else {
                if (state == 0) {
                    newInterval[0] = Math.min(mergedStart, rangeStart);
                    mergedStart = newInterval[0];
                    state = 1;
                }
                newInterval[1] = Math.max(mergedEnd, rangeEnd);
                mergedEnd = newInterval[1];
            }
        }
        if (state == 0 || state == 1) {
            list.add(newInterval);
        }
        return list.toArray(new int[0][0]);
    }
}
