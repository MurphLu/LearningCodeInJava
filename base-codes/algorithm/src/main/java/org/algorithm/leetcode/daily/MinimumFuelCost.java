package org.algorithm.leetcode.daily;


import java.util.*;

/**
 * 给你一棵 n 个节点的树（一个无向、连通、无环图），每个节点表示一个城市，编号从 0 到 n - 1 ，且恰好有 n - 1 条路。0 是首都。给你一个二维整数数组 roads ，其中 roads[i] = [ai, bi] ，表示城市 ai 和 bi 之间有一条 双向路 。
 *
 * 每个城市里有一个代表，他们都要去首都参加一个会议。
 *
 * 每座城市里有一辆车。给你一个整数 seats 表示每辆车里面座位的数目。
 *
 * 城市里的代表可以选择乘坐所在城市的车，或者乘坐其他城市的车。相邻城市之间一辆车的油耗是一升汽油。
 *
 * 请你返回到达首都最少需要多少升汽油。
 *
 * 到达每个节点之后可以重新分配最少的车
 */
public class MinimumFuelCost {
    static class CityPath{
        int code;
        List<Integer> nextCities;

        public CityPath(int code) {
            this.code = code;
            this.nextCities = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        int[][] roads = new int[][]{new int[]{0,1},new int[]{1,2}};
        new MinimumFuelCost().minimumFuelCost(roads, 3);
    }


    public long minimumFuelCost(int[][] roads, int seats) {
        CityPath[] cityPaths = new CityPath[roads.length + 1];
        for (int i = 0; i < cityPaths.length; i++) {
            cityPaths[i] = new CityPath(i);
        }

        for (int[] road: roads) {
            int city1 = road[0];
            int city2 = road[1];
            cityPaths[city1].nextCities.add(city2);
            cityPaths[city2].nextCities.add(city1);
        }
        process(cityPaths, -1, 0, seats);
        return 0L;
    }


    // {cost, people, car}
    private int[] process(CityPath[] cityPaths, int from, int cur, int seats) {
        System.out.println(cur);
        if (cur != 0 && cityPaths[cur].nextCities.size() == 1) {
            return new int[]{1, 1, 1};
        }

        int[] curCity = new int[]{0, 1, 0};
        for (int city: cityPaths[cur].nextCities) {
            if (city == from) {
                continue;
            }
            int[] next = process(cityPaths, cur, city, seats);
            for (int i = 0; i < next.length; i++) {
                curCity[i] += next[i];
            }
        }

        if (cur != 0) {
            curCity[2] = curCity[1] % seats == 0 ? curCity[1] / seats :  curCity[1] / seats + 1;
            curCity[0] += curCity[2];
        }

        System.out.println(Arrays.toString(curCity));
        return curCity;
    }

}
