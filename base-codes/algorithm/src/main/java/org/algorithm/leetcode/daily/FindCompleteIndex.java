package org.algorithm.leetcode.daily;

import java.util.*;

/**
 * 填涂游戏，
 * 按数组顺序填涂矩阵，
 * 找出数组中第一个使得矩阵中某一行或某一列填满的 index
 * 条件：矩阵及数组中的数不重复
 */
public class FindCompleteIndex {
    public static void main(String[] args) {
        FindCompleteIndex fci = new FindCompleteIndex();
        int[] arr = new int[]{1,4,5,2,6,3};
        int[][] mat = new int[][]{new int[]{4,3,5}, {1,2,6}};
        fci.firstCompleteIndex(arr, mat);
    }
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int height = mat.length;
        int width = mat[0].length;
        int[] recordCol = new int[height];
        int[] recordRow = new int[width];

        Map<Integer, int[]> matMap = new HashMap<>();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                matMap.put(mat[i][j], new int[]{i, j});
            }

        }
        for (int i = 0; i < arr.length; i++) {
            int[] coordinator = matMap.get(arr[i]);
            int col = coordinator[0], row = coordinator[1];
            recordCol[col] ++;
            recordRow[row] ++;
            if (recordRow[row] == height || recordCol[col] == width) {
                return i;
            }
        }
        return -1;
    }
}
