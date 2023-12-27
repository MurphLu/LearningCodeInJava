package org.algorithm.leetcode.daily;

import java.util.HashMap;
import java.util.Map;

public class MaxStudents {
    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
    public int maxStudents(char[][] seats) {
        int row = 0;
        int m=seats.length, n=seats[0].length;
        int mx = 0;
        for (int i = 0; i < 1<<n; i++) {
            mx = Math.max(mx, dp(seats, m - 1, i));
        }
        return mx;
    }

    Map<Integer, Integer> memo = new HashMap<>();

    public int dp(char[][] seats, int row, int status) {
        int n = seats[0].length;
        int key = (row << n) + status;
        if (memo.containsKey(key)) {
            return memo.get(key);
        } else {
            if (!isSingleRowCompliant(seats, status, n, row)) {
                memo.put(key, Integer.MIN_VALUE);
                return Integer.MIN_VALUE;
            }
            int student = Integer.bitCount(status);
            if (row == 0) {
                memo.put(key, student);
                return student;
            }
            int mx = 0;
            for (int upperRowStatus = 0; upperRowStatus < 1 << n; upperRowStatus++) {
                if (isCrossRowCompliant(status, upperRowStatus, n)) {
                    mx = Math.max(mx, dp(seats, row - 1, upperRowStatus));
                }
            }
            memo.put(key, student + mx);
        }
        return memo.get(key);
    }

    private boolean isCrossRowCompliant(int status, int upperRowStatus, int n) {
        int up1 = upperRowStatus << 1;
        int up2 = upperRowStatus >> 1;
        for (int i = 0; i < n; i++) {
            status >>=1;
            up1 >>= 1;
            up2 >>= 1;
            if (((status & 1) == 1) && ((up1 & 1) == 1) || ((up2 & 1) == 1)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSingleRowCompliant(char[][] seats, int status, int n, int row) {
        for (int i = 0; i < n; i++) {
            if (((status >> i) & 1) == 1) {
                if (seats[row][i] == '#') {
                    return false;
                }
                if (i > 0 && ((status >> (i - 1)) & 1) == 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
