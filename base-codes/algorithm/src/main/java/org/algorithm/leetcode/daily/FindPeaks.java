package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个下标从 0 开始的数组 mountain 。你的任务是找出数组 mountain 中的所有 峰值。
 *
 * 以数组形式返回给定数组中 峰值 的下标，顺序不限 。
 *
 * 注意：
 *
 * 峰值 是指一个严格大于其相邻元素的元素。
 * 数组的第一个和最后一个元素 不 是峰值。
 */
public class FindPeaks {
    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> res = new ArrayList<>();
        for(int i = 1; i < mountain.length - 1; i++) {
            if (mountain[i-1]< mountain[i] && mountain[i+1]< mountain[i]) {
                res.add(i);
            }
        }
        return res;
    }
}
