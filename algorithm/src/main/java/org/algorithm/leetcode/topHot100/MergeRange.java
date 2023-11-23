package org.algorithm.leetcode.topHot100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class MergeRange {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        List<int[]> res = new LinkedList<>();
        int[] current = intervals[0].clone();
        for (int i = 1; i < intervals.length; i++) {
            if(current[1] < intervals[i][0]) {
                res.add(current);
                current = intervals[i].clone();
            } else {
                current[1] = Math.max(intervals[i][1], current[1]);
            }
        }
        res.add(current);
        int[][] resArr = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArr[i] = res.get(i);
        }
        return resArr;

    }

    public int[][] merge2(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        int current = 0;
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[current][1] < intervals[i][0]) {
                current++;
                intervals[current] = intervals[i];
            } else {
                intervals[current][1] = Math.max(intervals[i][1], intervals[current][1]);
            }
        }
        return Arrays.copyOfRange(intervals, 0, current);
    }
}
