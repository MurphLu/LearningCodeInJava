package org.algorithm.leetcode.daily;

public class PunishNumber {
    public static void main(String[] args) {
        for (int i = 0; i <= 99; i++) {
            System.out.println(i + " " + isValid(i*i, i));
        }
    }

    private static boolean isValid(int pow, int i) {
        if (i - pow == 0) { return true;}
        int digits = 10;
        while (pow / digits > 0 && i > pow % digits) {
            boolean isP = isValid(pow / digits, i - pow % digits);
            if (isP) {
                return true;
            }
            digits *= 10;
        }
        return false;
    }
}
