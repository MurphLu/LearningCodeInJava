package org.algorithm.leetcode.topHot100;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1,2,3,4},
                new int[]{5,6,7,8},
                new int[]{9,10,11,12},
        };
        new SpiralOrder().spiralOrder(matrix);
    }
    public List<Integer> spiralOrder(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int top = 0;
        int left = 0;
        int right = width - 1;
        int bottom = height - 1;
        List<Integer> res = new ArrayList<>();
        while (true) {
            for (int i = left; i <= right; i++) {
                res.add(matrix[top][i]);
            }
            if (top == bottom) {
                break;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            if (right == left) {
                break;
            }
            right--;
            for (int i = right; i >= left ; i--) {
                res.add(matrix[bottom][i]);
            }
            if (top == bottom) {
                break;
            }
            bottom--;

            for (int i = bottom; i >= top; i--) {
                res.add(matrix[i][left]);
            }
            if (right == left) {
                break;
            }
            left++;
        }
        System.out.println(res);
        return res;
    }
}
