package org.algorithm.leetcode.daily;

import org.algorithm.utils.StringToArray;

import java.util.Arrays;

public class PossibleToStamp {
    public static void main(String[] args) {
        PossibleToStamp possibleToStamp = new PossibleToStamp();
        int[][] arr = StringToArray.generateArray("[[0,0,0,0,0],[0,0,0,0,0],[0,0,1,0,0],[0,0,0,0,1],[0,0,0,1,1]]");
        possibleToStamp.possibleToStamp(arr, 2, 2);
    }
    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int x = grid.length;
        int y = grid[0].length;
        int[][] sum = new int[x + 1][y + 1];
        // 计算每个格子左上格子的总和
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + grid[i][j];
            }
        }



        int[][] diff = new int[x + 2][y + 2];
        for (int bottom = stampHeight; bottom <= x; bottom++) {
            for (int right = stampWidth; right <= y; right++) {
                int top = bottom - stampHeight + 1;
                int left = right - stampWidth + 1;
                if (sum[bottom][right] - sum[bottom][left-1] - sum[top - 1][right] + sum[top - 1][left - 1] == 0) {
                    diff[top][left]++;
                    diff[top][right]--;
                    diff[bottom + 1][left]--;
                    diff[bottom + 1][right + 1]++;
                }
            }
        }

        for (int[] arr: diff
        ) {
            System.out.println(Arrays.toString(arr));
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                diff[i+1][j + 1] += diff[i + 1][j] + diff[i][j+1] - diff[i][j];

            }
        }
        System.out.println();
        for (int[] arr: diff
        ) {
            System.out.println(Arrays.toString(arr));
        }
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (grid[i][j] == 0 && diff[i+1][j+1] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
// [0,0,0,0,0],    [1,0,0,0,-1],
// [0,0,0,0,0],    [0,-1,0,0,1],
// [0,0,1,0,0],    [-1,1,0,0,0],
// [0,0,0,0,1],    [0,0,0,0,0],
// [0,0,0,1,1]     [0,0,0,0,0],

// [0,  0,  0,  0,  0, 0]
// [0,  0,  0,  1, -1, 0]
// [0,  0,  0,  0,  0, 0]
// [0,  1, -1, -1,  0, 1]
// [0,  0,  0,  0,  0, 0]
// [0, -1,  0,  1,  0, 0]
