package org.algorithm.recursion.dp;
// 三维
// 马在棋盘上 从 一个点 跳到（日） 另一个点，限制步数为 n，一共有多少种方式
public class Chess {
    public static void main(String[] args) {
        System.out.println(path(12, new int[]{3,3}, 0,0, 4));
        System.out.println(withDP(12, new int[]{3,3}, 0, 0, 4));
    }

    public static int path(int size, int[] target, int x, int y,  int step) {
        return process(size, target, x, y, step);
    }

    private static int process(int size, int[] target, int x, int y,  int step) {
        if (x >= size || y >= size || x < 0 || y < 0 || step < 0) {
            return 0;
        }
        if (x == target[0] && y == target[1] && step == 0) {
            return 1;
        }
        return process(size, target, x + 1, y + 2, step - 1)
                + process(size, target, x + 1, y - 2, step - 1)
                + process(size, target, x - 1, y + 2, step - 1)
                + process(size, target, x - 1, y - 2, step - 1)
                + process(size, target, x + 2, y + 1, step - 1)
                + process(size, target, x + 2, y - 1, step - 1)
                + process(size, target, x - 2, y + 1, step - 1)
                + process(size, target, x - 2, y - 1, step - 1);
    }

    public static int withDP(int size, int[] target, int x, int y,  int step) {
        int dp[][][] = new int[step + 1][size][size];
        dp[step][target[0]][target[1]] = 1;
        while (--step >= 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    dp[step][i][j] = getValue(dp, i + 1, j + 2, step + 1)
                            + getValue(dp, i + 1, j - 2, step + 1)
                            + getValue(dp, i - 1, j + 2, step + 1)
                            + getValue(dp, i - 1, j - 2, step + 1)
                            + getValue(dp, i + 2, j + 1, step + 1)
                            + getValue(dp, i + 2, j - 1, step + 1)
                            + getValue(dp, i - 2, j + 1, step + 1)
                            + getValue(dp, i - 2, j - 1, step + 1);
                }
            }
        }
        return dp[0][x][y];
    }
    private static int getValue(int dp[][][], int x, int y, int curStep) {
        int size = dp[curStep].length;
        if (x >= size || y >= size || x < 0 || y < 0) {
            return 0;
        }
        return dp[curStep][x][y];
    }
}
