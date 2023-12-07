package org.algorithm.leetcode.daily;

import org.algorithm.utils.StringToArray;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * n 座城市，从 0 到 n-1 编号，其间共有 n-1 条路线。因此，要想在两座不同城市之间旅行只有唯一一条路线可供选择（路线网形成一颗树）。去年，交通运输部决定重新规划路线，以改变交通拥堵的状况。
 *
 * 路线用 connections 表示，其中 connections[i] = [a, b] 表示从城市 a 到 b 的一条有向路线。
 *
 * 今年，城市 0 将会举办一场大型比赛，很多游客都想前往城市 0 。
 *
 * 请你帮助重新规划路线方向，使每个城市都可以访问城市 0 。返回需要变更方向的最小路线数。
 *
 * 题目数据 保证 每个城市在重新规划路线方向后都能到达城市 0 。
 */
public class MinRecorder {

    public static void main(String[] args) {
        MinRecorder mr = new MinRecorder();
        int n = 5;
        int[][] path = StringToArray.generateArray("[[1,0],[1,2],[3,2],[3,4]]");
        mr.minReorder(n, path);
    }
    static class City {
        Set<Integer> from = new HashSet<>();
        Set<Integer> to = new HashSet<>();

        public void addFrom(int value) {
            from.add(value);
        }
        public void addTo(int value) {
            to.add(value);
        }

        public int pathCount() {
            return this.from.size() + this.to.size();
        }

        public int getToCountAndMoveToFromExcept(int value) {
            int count = 0;
            if (to.size() != 0) {
                from.addAll(to);
                count = to.contains(value) ? to.size() - 1 : to.size();

            }

            return count;
        }
    }
    public int minReorder(int n, int[][] connections) {
        City[] paths = new City[n];
        for (int i = 0; i < n; i++) {
            paths[i] = new City();
        }
        for (int[] connection: connections) {
            int from = connection[0];
            int to = connection[1];
            paths[from].addTo(to);
            paths[to].addFrom(from);
        }
        return process(paths, -1, 0);
    }

    private int process(City[] paths, int last, int cur) {
        if (cur!= 0 && paths[cur].pathCount() == 1) {
            return 0;
        }
        City city = paths[cur];
        int count = city.getToCountAndMoveToFromExcept(last);
        for (int i: city.from) {
            count += process(paths, cur, i);
        }
        return count;
    }
}
