package org.algorithm.leetcode.daily;

import java.awt.geom.QuadCurve2D;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MagicTower {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i < 10001) {
            if (i < 5000) {
                list.add(100000);
            } else {
                list.add(-100000);
            }
        }
        new MagicTower().magicTower(new int[]{
                100,100,100,-250,-60,-140,-50,-50,100,150
        });

    }
    public int magicTower(int[] nums) {
        PriorityQueue<Long> queue = new PriorityQueue<>();
        List<Long> tail = new ArrayList<>();
        long remain = 1;
        int i = 0;
        while (i < nums.length) {
            int cur = nums[i];
            remain += cur;
            if (cur < 0) {
                queue.add((long) cur);
            }
            while (!queue.isEmpty() && remain <= 0) {
                long min = queue.poll();
                remain -= min;
                tail.add(min);
            }
            i++;
        }
        int step = tail.size();
        for (Long aLong : tail) {
            remain += aLong;
        }
        return remain < 0 ? -1 : step;
    }
}
