package org.algorithm.leetcode.daily;

import org.algorithm.utils.StringToArray;

import java.util.*;

public class MinimumEffortPath {

    static class Edge {
        int weight;
        String[] node;
    }

    public static void main(String[] args) {
        int[][] heights = StringToArray.generateArray("[[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]");
        System.out.println(Arrays.deepToString(heights));
        System.out.println(new MinimumEffortPath().minimumEffortPath(heights));
    }


    public int minimumEffortPath2(int[][] heights) {
        height = heights.length;
        weight = heights[0].length;
        for (int i = 1; i < height; i++) {
            for (int j = 1; j < weight; j++) {

            }
        }
        return 0;
    }

    int height = 0;
    int weight = 0;
    public int minimumEffortPath(int[][] heights) {
        height = heights.length;
        weight = heights[0].length;
        int[][] res = new int[height][weight];
        for (int[] arr: res) {
            Arrays.fill(arr, -1);
        }
//        process(0, 0, heights, res);
        processBFS(heights, res);
        System.out.println(Arrays.deepToString(res));
        return res[height - 1][weight - 1];
    }
    int[][] directions = new int[][]{
            new int[]{1, 0},
            new int[]{-1, 0},
            new int[]{0,1},
            new int[]{0, -1}};
    private void process(int x, int y, int[][] heights, int[][] res){
        int cur = heights[x][y];
        int curCost = res[x][y];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int[] direction: directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (newX < 0 ||
                    newX >= height ||
                    newY < 0 || newY >= weight ||
                    (newX == 0 && newY == 0)) {
                continue;
            }
            int result = Math.abs(cur - heights[newX][newY]);
            result = curCost == -1 ? result : Math.max(result, curCost);
            int cost = res[newX][newY];
            if (cost == -1 || cost > result) {
                queue.add(new int[]{result, newX, newY});
            }
        }
        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            res[next[1]][next[2]] = next[0];
            process(next[1], next[2], heights, res);
        }
    }

    private void processBFS(int[][] heights, int[][] res) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            int cur = heights[x][y];
            int curCost = res[x][y];
            for (int[] direction: directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX < 0 ||
                        newX >= height ||
                        newY < 0 || newY >= weight ||
                        (newX == 0 && newY == 0)) {
                    continue;
                }
                int result = Math.abs(cur - heights[newX][newY]);
                result = curCost == -1 ? result : Math.max(result, curCost);
                int cost = res[newX][newY];
                if (cost == -1 || cost > result) {
                    res[newX][newY] = result;
                    queue.add(new int[]{newX, newY});
                }
            }
        }
    }
}
// [1, 2, 1, 1, 1],   1, 1,
// [1, 2, 1, 2, 1],   0
// [1, 2, 1, 2, 1],
// [1, 2, 1, 2, 1],
// [1, 1, 1, 2, 1]

// [-1, 1, 1, 1, 1],
// [0, 1, 1, 1, 1],
// [0, 1, 1, 1, 1],
// [0, 1, 1, 1, 1],
// [0, 0, 0, 1, 1]
