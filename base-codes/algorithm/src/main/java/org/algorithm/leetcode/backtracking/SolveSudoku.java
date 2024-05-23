package org.algorithm.leetcode.backtracking;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 *
 * 回溯
 * 回溯，深度优先遍历，回溯时将后边改变的值再重置为原来值，改变前置尝试的值再继续遍历
 * 以前使用的字符串或者其他方式，都会有额外的开销
 */
public class SolveSudoku {
    public static void main(String[] args) {
        char[][] css = new char[][]{
                new char[]{'5','3','.','.','7','.','.','.','.'},
                new char[]{'6','.','.','1','9','5','.','.','.'},
                new char[]{'.','9','8','.','.','.','.','6','.'},
                new char[]{'8','.','.','.','6','.','.','.','3'},
                new char[]{'4','.','.','8','.','3','.','.','1'},
                new char[]{'7','.','.','.','2','.','.','.','6'},
                new char[]{'.','6','.','.','.','.','2','8','.'},
                new char[]{'.','.','.','4','1','9','.','.','5'},
                new char[]{'.','.','.','.','8','.','.','7','9'}};
        new SolveSudoku().solveSudoku(css);
        System.out.print(0 + ", ");
        for (int i = 1; i < 81; i++) {
            System.out.print(i + ", ");
            if (i % 8 == 0) {
                System.out.println();
            }
        }

    }
    public void solveSudoku(char[][] board) {
        process(board, 0);

    }
    char[] cs = new char[]{'1','2', '3', '4', '5', '6', '7', '8', '9'};

    private boolean process(char[][] board, int idx) {
        if (idx == 81) {
            return true;
        }
        int row = idx / 9;
        int col = idx % 9;
        if (board[row][col] != '.') {
            return process(board, idx+1);
        }
        for (int i = 0; i < 9; i++) {
            // 如果该单元格可以放某一个数，那么放入，并继续检查之后的单元格
            // 如果能遍历到最后，说明为有效解，直接 return true 即可，此时数独 board 已经全部填入有效数字
            if (canPut(board, row, col, cs[i])) {
                board[row][col] = cs[i];
                if(process(board, idx + 1)){
                    return true;
                }
            }
        }
        // 如果前面单元格放的数字不对，那么当前 idx 改变的 . 重置，改变之前步骤中所放入的数字继续向下检查
        board[row][col] = '.';
        return false;
    }

    private boolean canPut(char[][] board, int row, int col, char c) {
        // 检查横向纵向是否有重
        for (int i = 0; i < 9; i++) {
            if(board[row][i] == c){
                return false;
            }
            if (board[i][col] == c) {
                return false;
            }
        }

        int rowS = row / 3 * 3;
        int colS = col / 3 * 3;

        // 检查小九宫格是否有重复
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(board[rowS+i][colS+j] == c) {
                    return false;
                }
            }
        }
        return true;
    }
}
