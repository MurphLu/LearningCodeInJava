package org.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 */
public class GenerateMatrix {
    public static void main(String[] args) {
        new GenerateMatrix().generateMatrix(5);
    }
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int[][] direction = new int[][]{
                new int[]{0, 1},
                new int[]{1, 0},
                new int[]{0, -1},
                new int[]{-1, 0}
        };
        int dirIdx = 0;
        int x = 0;
        int y = 0;
        int i = 1;
        while (i <= n*n) {
            res[x][y] = i;
            if (i == n*n) {
                break;
            }
            int[] dir = direction[dirIdx % 4];
            int xa = dir[0];
            int ya = dir[1];
            int nx = x + xa;
            int ny = y + ya;
            if (nx >=0 && nx < n && ny >=0 && ny < n && res[nx][ny] == 0) {
                x = nx;
                y = ny;
                i++;
            } else {
                dirIdx++;
            }
        }
        for(int[] arr : res) {
            System.out.println(Arrays.toString(arr));
        }
        return res;
    }
}
