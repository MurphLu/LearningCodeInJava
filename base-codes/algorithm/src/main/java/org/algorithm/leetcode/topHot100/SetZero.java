package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.List;
// 给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。
public class SetZero {
    public void setZeroes(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        boolean topHasZero = false;
        boolean leftHasZero = false;
        for (int[] ints : matrix) {
            if (ints[0] == 0) {
                leftHasZero = true;
                break;
            }
        }
        for (int i = 0; i < width; i++) {
            if (matrix[0][i] == 0) {
                topHasZero = true;
                break;
            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (topHasZero) {
            for (int i = 0; i < width; i++) {
                matrix[0][i] = 0;
            }
        }
        if (leftHasZero) {
            for (int i = 0; i < height; i++) {
                matrix[i][0] = 0;
            }
        }

    }
}
