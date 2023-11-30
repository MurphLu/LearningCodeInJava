package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 给你一个下标从 0 开始大小为 m x n 的二维整数数组 grid ，它表示一个网格图。每个格子为下面 3 个值之一：
 *
 * 0 表示草地。
 * 1 表示着火的格子。
 * 2 表示一座墙，你跟火都不能通过这个格子。
 * 一开始你在最左上角的格子 (0, 0) ，你想要到达最右下角的安全屋格子 (m - 1, n - 1) 。每一分钟，你可以移动到 相邻 的草地格子。每次你移动 之后 ，着火的格子会扩散到所有不是墙的 相邻 格子。
 *
 * 请你返回你在初始位置可以停留的 最多 分钟数，且停留完这段时间后你还能安全到达安全屋。如果无法实现，请你返回 -1 。如果不管你在初始位置停留多久，你 总是 能到达安全屋，请你返回 109 。
 *
 * 注意，如果你到达安全屋后，火马上到了安全屋，这视为你能够安全到达安全屋。
 *
 * 如果两个格子有共同边，那么它们为 相邻 格子。
 */
public class MaximumMinutes {
    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{0,2,0,0,0,0,0},
                new int[]{0,2,0,2,2,1,0},
                new int[]{0,2,0,0,1,2,0},
                new int[]{0,0,2,2,2,0,2},
                new int[]{0,0,0,0,0,0,0}};
        maximumMinutes(grid);
    }
    public static int maximumMinutes(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] fireMap = spreadFire(grid);
        for (int[] arr: fireMap
             ) {
            System.out.println(Arrays.toString(arr));
        }
        int moveMap = moveMap(grid, fireMap);
        System.out.println(moveMap);
        return 0;
    }
    static int[][] spreads = new int[][]{new int[]{-1, 0}, new int[]{1, 0}, new int[]{0, -1}, new int[]{0, 1}};

    // 计算火扩散的过程，扩散到每一个格子需要的最短时间
    public static int[][] spreadFire(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] fireMap = new int[height][width];
        // 首先用 -1 填充
        for (int[] arr: fireMap) {
            Arrays.fill(arr, -1);
        }
        //获取着火格子的初始位置，并将 fireMap 中火的初始位置置 0
        // 并将着火格子初始位置加入list 以便进行后续宽度优先遍历
        LinkedList<int[]> firePoint = new LinkedList<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 1) {
                    firePoint.addLast(new int[]{i, j});
                    fireMap[i][j] = 0;
                }
            }
        }
        int step = 0;
        // 当扩散完之后 firePoint list 会弹出所有节点
        while (firePoint.size() > 0) {
            step++;
            // 上一步中最后一个格子，来中止本层遍历
            int[] last = firePoint.get(firePoint.size() - 1);
            int[] point = firePoint.poll();
            while (point != null) {
                // 获取已经着火的格子坐标
                int y = point[0];
                int x = point[1];
                // 扩散方向，四个上下左右
                for (int[] spread: spreads) {
                    int v = point[0] + spread[0];
                    int h = point[1] + spread[1];
                    // 判断对应方向扩散完之后是否还在棋盘中，如果不在那么直接舍弃这种情况
                    if (v >= 0 && v < height && h >=0 && h < width) {
                        // 拿到扩散目标格子当前的状态
                        int trySpread = fireMap[v][h];
                        // 判断以下条件
                        // 目标格子不是墙 且 目标格子未被扩散到
                        // 此时目标格子入队列，并填充扩散到该位置时需要的时间
                        if (grid[v][h] != 2 && (trySpread == -1 || trySpread > step)) {
                            firePoint.addLast(new int[]{v, h});
                            fireMap[v][h] = step;
                        }
                    }
                }
                // 当上一步最后一个格子弹出时中止遍历，进行下一步遍历
                if (compareArr(point, last)) {
                    break;
                }
                // 拿下一个未进行扩散的格子继续上述步骤
                point = firePoint.poll();
            }
        }
        return fireMap;
    }

    private static boolean compareArr(int[] a, int [] b) {
        for (int i = 0; i < 2; i++) {
            if(a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static int moveMap(int[][] grid, int[][] fireMap) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] moveMap = new int[height][width];

        // 二分逻辑，如果过棋盘格子数的时间还能到达终点，那么火一定被墙围起来了
        int fail = height * width;
        int success = -1;
        int step = (fail) / 2;
        while (success+1 < fail) {
            // 填充初始状态
            for (int[] arr: moveMap) {
                Arrays.fill(arr, -1);
            }
            moveMap[0][0] = 0;
            LinkedList<int[]> cur = new LinkedList<>();
            // 初始位置
            cur.add(new int[]{0, 0});
            // 宽度优先遍历
            while (cur.size() > 0) {
                step++;
                int[] last = cur.get(cur.size() - 1);
                int[] point = cur.poll();
                while (point != null) {
                    // 每次可以走的方向
                    for (int[] moveDirection : spreads) {
                        int v = point[0] + moveDirection[0];
                        int h = point[1] + moveDirection[1];
                        // 移动之后确保在棋盘中
                        if (v >= 0 && v < height && h >= 0 && h < width) {
                            // 判断是否可以走该格子
                            if (
                                    (
                                            (fireMap[v][h] == -1 && grid[v][h] == 0) || // 火无法烧到该格子，且不是墙
                                             fireMap[v][h] > step ||  // 火到达该格子的时间要大于人到达该格子的时间
                                             ((v == height - 1 && h == width - 1) && fireMap[v][h] == step) // 如果是最后一个格子，火和人要同时到达
                                    )
                                    && (moveMap[v][h] == -1) // 未到过该格子
                            ) {
                                // 如队列，并填写时间
                                cur.addLast(new int[]{v, h});
                                moveMap[v][h] = step;
                            }
                        }
                    }
                    // 如果是上一分钟的最后一个那么直接继续下一轮循环
                    if (compareArr(point, last)) {
                        break;
                    }
                    point = cur.poll();
                }
            }
            // 如果最后一个格子为 -1，也就是当前 success 与 fail 的中心点不满足条件那么 fail 置为中心点继续
            if (moveMap[height - 1][width - 1] == -1) {
                fail = (fail + (success == -1 ? 0 : success)) / 2;
            } else {
                // 如果成功了，也就是当前 success 与 fail 的中心点满足条件那么 success 置为中心点继续
                success = (fail + (success == -1 ? 0 : success)) / 2;
            }
            step = (fail + (success == -1 ? 0 : success)) / 2;
        }
        // 最后，如果 success 等待的时间与格子数相同时，那么等多久都可以安全走完
        // 否则的话直接返回 success，恰好能走完要等待的时间或者 -1（无法安全走完）
        return success == width * height - 1 ? (int)Math.pow(10, 9) : success;
    }

}
