package org.algorithm.recursion.dp;

public class MinPath {
    public static void main(String[] args) {

    }

    public int minPathSum(int[][] grid) {
        int width = grid[0].length;
        int height = grid.length;
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0 ; j--) {
                if (i == height - 1 && j == width - 1){continue;}
                grid[i][j] = Math.min(grid[i][j] + getVal(grid, i+1, j), grid[i][j] + getVal(grid, i, j+1));
            }
        }
        return grid[0][0];
    }


    public int getVal(int[][] grid, int x, int y) {
        int width = grid[0].length;
        int height = grid.length;
        if (x >= height || y >= width) {
            return Integer.MAX_VALUE;
        }
        return grid[x][y];
    }
}
