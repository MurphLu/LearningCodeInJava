package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 现有一棵无向、无根的树，树中有 n 个节点，按从 0 到 n - 1 编号。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。
 *
 * 每个节点都关联一个价格。给你一个整数数组 price ，其中 price[i] 是第 i 个节点的价格。
 *
 * 给定路径的 价格总和 是该路径上所有节点的价格之和。
 *
 * 另给你一个二维整数数组 trips ，其中 trips[i] = [starti, endi] 表示您从节点 starti 开始第 i 次旅行，并通过任何你喜欢的路径前往节点 endi 。
 *
 * 在执行第一次旅行之前，你可以选择一些 非相邻节点 并将价格减半。
 *
 * 返回执行所有旅行的最小价格总和。
 */

public class MinimumTotalPrice {
    public static void main(String[] args) {
        int n =5;
        int[][] edges = new int[][]{new int[]{1,2}, new int[]{2,0}, new int[]{0,3}, new int[]{3,4}};
        int[] price = new int[]{12,26,22,12,2};
        int[][] trips = new int[][]{new int[]{3,3},new int[]{3,2},new int[]{3,0},new int[]{3,4},new int[]{1,1},new int[]{2,2},new int[]{4,0},new int[]{0,2},new int[]{2,3},new int[]{2,1},new int[]{4,2},new int[]{0,1},new int[]{4,2},new int[]{0,4},new int[]{0,3},new int[]{4,0},new int[]{4,0},new int[]{3,3},new int[]{4,3},new int[]{2,2},new int[]{4,2},new int[]{1,4},new int[]{3,2},new int[]{4,4},new int[]{4,2},new int[]{2,3},new int[]{4,3},new int[]{4,4},new int[]{4,2},new int[]{0,4},new int[]{4,2},new int[]{3,4},new int[]{4,0},new int[]{3,2},new int[]{3,1},new int[]{2,0},new int[]{0,4},new int[]{3,4},new int[]{2,0},new int[]{1,4},new int[]{4,2},new int[]{4,4},new int[]{2,1},new int[]{0,1},new int[]{4,1},new int[]{3,4},new int[]{0,4},new int[]{2,0},new int[]{2,0},new int[]{3,3},new int[]{4,4},new int[]{0,1},new int[]{0,1},new int[]{0,1},new int[]{2,0},new int[]{0,1},new int[]{3,1},new int[]{3,4},new int[]{3,4},new int[]{4,2},new int[]{0,4},new int[]{4,4},new int[]{3,2},new int[]{2,1},new int[]{3,2},new int[]{1,4},new int[]{1,0},new int[]{4,2},new int[]{4,3},new int[]{3,1},new int[]{4,4},new int[]{3,1},new int[]{1,0},new int[]{0,0},new int[]{0,0},new int[]{3,0},new int[]{0,2},new int[]{2,2},new int[]{3,3},new int[]{0,3}};
        new MinimumTotalPrice().minimumTotalPrice(n, edges, price, trips);
    }

    static class CityPath{
        int cost;
        List<Integer> nextCities;

        public CityPath(int code) {
            this.cost = code;
            this.nextCities = new ArrayList<>();
        }

        public void addNext(int val) {
            nextCities.add(val);
        }
    }

    CityPath[] cityPaths = null;



    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
        int sum = 0;
        // 根据 edge 建立树，并记录每个CityPath
        this.cityPaths = buildCityPaths(n, edges, price);
        int[] res = new int[n];
        for (int[] trip : trips) {
            // 规划当前路径，并返回 cost 最小的路径
            Info cur = travel(trip[0], trip[1], new Info[n], new Info());
            // 统计每个节点出现的次数
            count(res, cur.path);
        }
        // 计算每个节点在调整价格之前总花费
        for (int i = 0; i < n; i++) {
            res[i] *= price[i];
        }

        // 随意找一个不为 0 的节点，以该节点为头进行递归调用，树形 dp
        int start;
        for (start = 0; start < n; start++) {
            if(res[start] > 0) {
                break;
            }
        }
        // 最终结果中获取最小值即为结果
        int[] process = process(start, res, new HashSet<>());
        return Math.min(process[0], process[1]);
    }

    // 递归调用，并获取每个节点减半或原价时的花费
    public int[] process(int cur, int[] res,  Set<Integer> passed) {
        List<Integer> nextCities = cityPaths[cur].nextCities;
        if (cityPaths[cur].nextCities.size() == 1 && passed.contains(nextCities.get(0))) {
            return new int[]{res[cur], res[cur] / 2};
        }
        passed.add(cur);
        List<int[]> pathResList = new ArrayList<>();
        for (int city: nextCities) {
            if (passed.contains(city)) {
                continue;
            }
            pathResList.add(process(city, res, passed));
        }
        int fullCur = res[cur];
        for (int[] pathRes: pathResList) {
            fullCur += Math.min(pathRes[0], pathRes[1]);
        }
        int halfCur = res[cur] / 2;
        for (int[] pathRes: pathResList) {
            halfCur += pathRes[0];
        }
        return new int[]{fullCur, halfCur};
    }

    private void count(int[] res, List<Integer> count) {
        for (int i: count) {
            res[i]++;
        }
    }

    static class Info {
        int cost;
        List<Integer> path;

        public Info() {
            this.cost = 0;
            this.path = new ArrayList<>();
        }

        public static Info create(Info info) {
            Info newInfo = new Info();
            newInfo.cost = info.cost;
            newInfo.path.addAll(info.path);
            return newInfo;
        }

        public void add(int value){
            path.add(value);
        }

        @Override
        public String toString() {
            return "Info{" +
                    "cost=" + cost +
                    ", path=" + path +
                    '}';
        }
    }

    private Info travel(int start, int end, Info[] records,  Info record){
        record.cost += cityPaths[start].cost;
        record.add(start);
        if (records[start] == null || records[start].cost > record.cost) {
            records[start] = Info.create(record);
            for (int city: cityPaths[start].nextCities) {
                travel(city, end, records, Info.create(record));
            }
        }
        return records[end];
    }

    private static CityPath[] buildCityPaths(int n, int[][] edges, int[] price) {
        CityPath[] cityPaths = new CityPath[n];
        for (int i = 0; i < n; i++) {
            cityPaths[i] = new CityPath(price[i]);
        }
        for (int[] edge: edges) {
            int pointA = edge[0];
            int pointB = edge[1];
            cityPaths[pointA].addNext(pointB);
            cityPaths[pointB].addNext(pointA);
        }
        return cityPaths;
    }
}
