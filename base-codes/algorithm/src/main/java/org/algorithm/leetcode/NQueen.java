package org.algorithm.leetcode;

// 0 0 0 0
// 1 0 0 0
// 0 0 1 0 (1 1 0 0)
//

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class NQueen {
    public static void main(String[] args) {
//        solveNQueens(5);
        System.out.println(Math.pow(2.0, -2147483648));
    }



    public static List<List<String>> solveNQueens(int n) {
        process(0, new int[n], n);

        return new LinkedList<>();
    }

    public static void nQueen(int i, int[] record, int n, List<List<String>> results) {
        if (i == n) {
            char[] s = new char[n];
            Arrays.fill(s, '.');
            List<String> result = new LinkedList<>();
            for (int k : record) {
                char[] clone = s.clone();
                clone[k] = 'Q';
                result.add(new String(clone));
            }
            results.add(result);
        }
        for (int j = 0; j < n; j++) {
            if (isValid2(record, i, j)) {
                record[i] = j;
                nQueen(i+1, record, n, results);
            }
        }
    }

    private static boolean isValid2(int[] records, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (
                    Math.abs(records[k] - j) == Math.abs(i - k)
                            ||
                            records[k] == j
            ) {
                return false;
            }
        }
        return true;
    }

    public static int process(int i, int[] record, int n) {
        // n - 1 为最后一行，如果到结尾，则说明有有效排列
        if (i == n) {
            System.out.println(Arrays.toString(record));
            return 1;}
        int res = 0;

        // 当前行放置位置 0 到 n-1
        for (int j = 0; j < n; j++) {
            // 基于当前行之前所放置的位置判断 当前行的当前位置是否有效，如果有效则继续，无效则判断下一个位置
            if (isValid(record, i, j)){
                // 将当前位置放入记录
                record[i] = j;
                // 继续寻找下一行合适位置，直到表尾位置，则说明为有效排列，返回 1，否则返回 0
                res += process(i+1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] records, int i, int j) {
        for (int k = 0; k < i; k++) {

            if (
                    Math.abs(records[k] - j) == Math.abs(i - k)
                            ||
                            records[k] == j
            ) {
                return false;
            }
        }
        return true;
    }
}
