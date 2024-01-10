package org.algorithm.leetcode.daily;

import org.algorithm.utils.StringToArray;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的欧式距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 * 数组中两个点到其中一个点距离相等的组合，考虑顺序
 */
public class NumberOfBoomerangs {
    public static void main(String[] args) {
        int[][] points = StringToArray.generateArray("[[0,0],[1,0],[-1,0],[0,1],[0,-1]]");
        int i = new NumberOfBoomerangs().numberOfBoomerangs(points);
        System.out.println(i);
    }
    public int numberOfBoomerangs(int[][] points) {
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < points.length ; i++) {
            int[] pointI = points[i];
            for (int j = 0; j < points.length; j++) {
                if (i == j) {continue;}
                int[] pointJ = points[j];
                int x = pointJ[0] - pointI[0];
                int y = pointJ[1] - pointI[1];
                int dis = x*x + y*y;
                map.put(dis, map.getOrDefault(dis, 0)+1);
            }
            System.out.println(map);
            count += generateCount(map);
            map = new HashMap<>();
        }
        return count;
    }

    private int generateCount(Map<Integer, Integer> map) {
        AtomicInteger count = new AtomicInteger();
        map.forEach((k, v)->{
            count.addAndGet(v*(v-1));
        });
        return count.get();
    }
}