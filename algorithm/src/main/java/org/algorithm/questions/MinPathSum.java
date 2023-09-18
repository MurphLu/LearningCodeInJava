package org.algorithm.questions;

import java.util.*;
/**
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：一个机器人每次只能向下或者向右移动一步。
 */
public class MinPathSum {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{5,4,2,9,6,0,3,5,1,4,9,8,4,9,7,5,1},
                new int[]{3,4,9,2,9,9,0,9,7,9,4,7,8,4,4,5,8},
                new int[]{6,1,8,9,8,0,3,7,0,9,8,7,4,9,2,0,1},
                new int[]{4,0,0,5,1,7,4,7,6,4,1,0,1,0,6,2,8},
                new int[]{7,2,0,2,9,3,4,7,0,8,9,5,9,0,1,1,0},
                new int[]{8,2,9,4,9,7,9,3,7,0,3,6,5,3,5,9,6},
                new int[]{8,9,9,2,6,1,2,5,8,3,7,0,4,9,8,8,8},
                new int[]{5,8,5,4,1,5,6,6,3,3,1,8,3,9,6,4,8},
                new int[]{0,2,2,3,0,2,6,7,2,3,7,3,1,5,8,1,3},
                new int[]{4,4,0,2,0,3,8,4,1,3,3,0,7,4,2,9,8},
                new int[]{5,9,0,4,7,5,7,6,0,8,3,0,0,6,6,6,8},
                new int[]{0,7,1,8,3,5,1,8,7,0,2,9,2,2,7,1,5},
                new int[]{1,0,0,0,6,2,0,0,2,2,8,0,9,7,0,8,0},
                new int[]{1,1,7,2,9,6,5,4,8,7,8,5,0,3,8,1,5},
                new int[]{8,9,7,8,1,1,3,0,1,2,9,4,0,1,5,3,1},
                new int[]{9,2,7,4,8,7,3,9,2,4,2,2,7,8,2,6,7},
                new int[]{3,8,1,6,0,4,8,9,8,0,2,5,3,5,5,7,5},
                new int[]{1,8,2,5,7,7,1,9,9,8,9,2,4,9,5,4,0},
                new int[]{3,4,4,1,5,3,3,8,8,6,3,5,3,8,7,1,3}};
        System.out.println(minPathSum(grid));
    }

    public static void printMap(Map<String, Integer> map) {
        ArrayList<String> strings = new ArrayList<>(map.keySet());
        String[] objects = strings.stream().sorted().toArray(String[]::new);
        System.out.println();
        for (String s: objects){

            System.out.print(s + "=" + map.get(s) + ", ");

        }

    }


    public static int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < height - 1; i++) {
            grid[i+1][0] += grid[i][0];
        }
        for (int i = 0; i < width - 1; i++) {
            grid[0][i+1] += grid[0][i];
        }

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                grid[i][j] += Math.min(grid[i-1][j], grid[i][j-1]);
            }
        }
        return grid[height - 1][width - 1];

    }

    // 可以走任何路径
    public static int minPathSum_(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        if (height == 1 || width == 1) {
            int sum = 0;
            for (int[] ints : grid) {
                for (int j = 0; j < width; j++) {
                    sum += ints[j];
                }
            }
            return sum;
        }
        Map<String, Integer> pathSum = new HashMap<>();
        Set<String> locked = new HashSet<>();
        pathSum.put("00-00", grid[0][0]);
        String minPoint = getSumAndUnselectPoint(pathSum, locked);
        while (minPoint != null) {
            String[] sPoint = minPoint.split("-");
            int[] point = Arrays.stream(sPoint).mapToInt(Integer::parseInt).toArray();
            int[][] nexts = new int[4][];
            int[] left = point[1] == 0 ? null : new int[]{point[0], point[1] - 1};
            nexts[0] = left;
            int[] right = point[1] == width - 1 ? null : new int[]{point[0], point[1] + 1};
            nexts[1] = right;
            int[] top = point[0] == 0 ? null : new int[]{point[0] - 1, point[1]};
            nexts[2] = top;
            int[] bottom = point[0] == height - 1 ? null : new int[]{point[0] + 1, point[1]};
            nexts[3] = bottom;

            int minDistance = pathSum.get(minPoint);
            printMap(pathSum);
            for (int[] p : nexts) {
                if (p != null) {
                    int currVal = grid[p[0]][p[1]];
                    String nextPoint = String.format("%02d-%02d", p[0], p[1]);
                    if (!pathSum.containsKey(nextPoint)) {
                        pathSum.put(nextPoint, minDistance + currVal);
                    }
                    pathSum.put(nextPoint, Math.min(pathSum.get(nextPoint), minDistance + currVal));
                }
            }
            locked.add(minPoint);
            minPoint = getSumAndUnselectPoint(pathSum, locked);
        }
        System.out.println(pathSum);
        return pathSum.get(String.format("%02d-%02d", height-1, width-1));
    }

    public static String getSumAndUnselectPoint(Map<String, Integer> pathSum, Set<String> locked) {
        String minPoint = null;
        Integer minPathSum = Integer.MAX_VALUE;
        for (String k : pathSum.keySet()) {
            if (!locked.contains(k)) {
                if (minPathSum > pathSum.get(k)) {
                    minPoint = k;
                    minPathSum = pathSum.get(k);
                }
            }
        }
        locked.add(minPoint);
        return minPoint;
    }

}
