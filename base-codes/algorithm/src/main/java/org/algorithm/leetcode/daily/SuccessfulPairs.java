package org.algorithm.leetcode.daily;

import java.util.Arrays;

public class SuccessfulPairs {
    public static void main(String[] args) {
        int[] spells = new int[]{27};
        int[] potions = new int[]{30,11,5,20,19,36,39,24,20,37,33,22,32,28,36,24,40,27,36,37,38,23,39,11,40,19,37,32,25,29,28,37,31,36,32,40,38,22,17,38,20,33,29,17,36,33,35,25,28,18,17,19,40,27,40,28,40,40,40,39,17,34,36,11,22,29,22,35,35,22,18,34};
        successfulPairs(spells, potions, 135);
    }
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        int[] result = new int[spells.length];
        Arrays.sort(potions);
        System.out.println(potions.length);
        System.out.println(Arrays.toString(potions));

        for (int i = 0; i < spells.length; i++) {
            int spell = spells[i];
            int count = count(spell, potions, success);
            result[i] = count;
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
    
    private static int count(int spell, int[] potions, long success) {
        if ((long) potions[0] * spell >= success) {
            return potions.length;
        } else if ((long) potions[potions.length - 1] * spell < success) {
            return 0;
        }
        int left = 0;
        int right = potions.length;
        int center = right / 2;
        while (left + 1 < right) {
            if ((long) potions[center] * spell < success) {
                left = center;
            } else {
                right = center;
            }
            center = left + (right - left) / 2;
        }
        return potions.length - right;
    }
}
