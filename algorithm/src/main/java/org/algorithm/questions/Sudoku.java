package org.algorithm.questions;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {
    public static void main(String[] args) {

    }

    public static boolean isValidSudoku(char[][] board) {
        Set<Character> rowSet;
        Set<Character> colSet;
        for (int i = 0; i < 9; i++) {
            rowSet = new HashSet<>();
            colSet = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (isInvalid(board, rowSet, i, j) || isInvalid(board, colSet, j, i)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i+=3) {
            for (int j = 0; j < 9; j+=3) {
                if (!checkBlock(board, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isInvalid(char[][] board, Set<Character> set, int x, int y) {
        char c = board[x][y];
        if (set.contains(c)) {
            return true;
        }
        if (c != '.') {
            set.add(c);
        }
        return false;
    }

    private static boolean checkBlock(char[][] board, int x, int y) {
        Set<Character> set = new HashSet<>();
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {
                char c = board[i][j];
                if (c == '.') { continue;}
                if (set.contains(c)) {
                    return false;
                }
                set.add(c);
            }
        }
        return true;
    }
}
