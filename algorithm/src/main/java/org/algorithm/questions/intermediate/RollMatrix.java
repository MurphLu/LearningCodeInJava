package org.algorithm.questions.intermediate;


import java.util.Arrays;

// 正方形矩阵顺时针旋转 90 度
public class RollMatrix {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                new int[]{1,2,3,4},
                new int[]{6,7,8,9},
                new int[]{11,12,13,14},
                new int[]{16,17,18,19},};
        roll(arr);
    }

    public static void roll(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int cur = 0;
        while (cur <= width / 2) {
            process(matrix, cur++, height - 1);
        }
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private static void process(int[][] matrix, int cur, int border) {
        int edge = border - cur;
        int b = 0;
        while (b + cur < edge) {
            int temp = matrix[cur][cur + b];
            matrix[cur][cur + b] = matrix[edge - b][cur];
            matrix[edge - b][cur] = matrix[edge][edge - b];
            matrix[edge][edge - b] = matrix[cur + b][edge];
            matrix[cur + b][edge] = temp;
            b++;
        }
    }
}
