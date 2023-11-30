package org.algorithm.questions.intermediate;
// 螺旋打印矩阵
public class MatrixPrint {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                new int[]{1,2,3,4,5},
                new int[]{6,7,8,9,10},
                new int[]{11,12,13,14,15},
                new int[]{16,17,18,19,20},
        };
        new MatrixPrint().print(arr);
    }
    public void print(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int left = 0;
        int top = 0;
        int right = width - 1;
        int bottom = height - 1;
        int direction = 1;
        while (left < right || top < bottom) {
            if (direction == 1) {
                int tl = left;
                while (tl <= right) {
                    System.out.println(matrix[top][tl++]);
                }
                top ++;
                direction = 2;
            } else if (direction == 2) {
                int tt = top;
                while (tt <= bottom) {
                    System.out.println(matrix[tt++][right]);
                }
                right--;
                direction = 3;
            } else if (direction == 3) {
                int tr = right;
                while (tr >= left) {
                    System.out.println(matrix[bottom][tr--]);
                }
                bottom--;
                direction = 4;
            } else {
                int tb = bottom;
                while (tb >= top) {
                    System.out.println(matrix[tb--][left]);
                }
                left ++;
                direction = 1;
            }
        }
    }

}
