package org.algorithm.questions.preHandle;

/**
 * 0 1 1 1 1
 * 0 1 0 0 1
 * 0 1 0 0 1
 * 0 1 1 1 1
 * 0 1 0 1 1
 */
public class MaxSquare {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{0,1,1,1,1},
                new int[]{0,1,0,0,1},
                new int[]{0,1,0,0,1},
                new int[]{0,1,1,1,1},
                new int[]{0,1,0,0,1},
                new int[]{0,1,0,0,1},};
        System.out.println(maxBorderAllOneSquare(matrix));
    }

    public static int maxBorderAllOneSquare(int[][] matrix) {
        int height = matrix.length;
        int weight = matrix[0].length;
        int target = 0;
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                if (matrix[j][i] == 0) { continue; }
                target = Math.max(target, 1);
                int length = 2;
                while (j + length - 1 < height && i + length - 1 < weight) {
                    int tempLength = 0;
                    if (matrix[j + tempLength][i] == 0 || matrix[j][i+tempLength] == 0) {
                        break;
                    }
                    while (++tempLength < length) {
                        boolean result = matrix[j + length - 1][i + tempLength] == 1 &&
                                matrix[j + tempLength][i + length - 1] == 1;
                        if (!result) {
                            tempLength = 0;
                            break;
                        }
                    }
                    if (tempLength == 0) {
                        length ++;
                        continue;
                    }
                    target = Math.max (length * length, target);
                    length ++;
                }
            }
        }
        return target;
    }
}
