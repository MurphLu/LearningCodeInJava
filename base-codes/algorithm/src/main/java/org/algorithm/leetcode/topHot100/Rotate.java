package org.algorithm.leetcode.topHot100;

/**
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 *
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 */
public class Rotate {
    public void rotate(int[][] matrix) {
        int n = matrix.length - 1;
        int start = 0;
        int end = n;
        while (start < end) {
            for (int i = 0; start + i < end; i++) {
                int a = start + i;
                int b = end - i;
                int temp = matrix[start][a];
                matrix[start][a] = matrix[b][start];
                matrix[b][start] = matrix[end][b];
                matrix[end][b] = matrix[a][end];
                matrix[a][end] = temp;
            }
            start++;
            end--;
        }
    }
}
