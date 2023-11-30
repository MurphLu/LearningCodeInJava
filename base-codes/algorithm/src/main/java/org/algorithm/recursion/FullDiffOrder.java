package org.algorithm.recursion;

import java.util.*;

public class FullDiffOrder {

    public static void main(String[] args) {
        String s = "abbc";
        process(s, new ArrayList<>());
//        System.out.println(s.substring(0, 2) + s.substring(3));
    }

    public static void process(String s, List<Character> res) {
        if (s.isEmpty()) {
            System.out.println(res);
        }
        char[] chars = s.toCharArray();
        // 在当前位置是否出现过，如果未出现过则继续，如果出现过，则直接忽略该结果
        Set<Character> showedInCurrLoc = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            if (!showedInCurrLoc.contains(chars[i])) {
                List<Character> newChars = new ArrayList<>(List.copyOf(res));
                newChars.add(chars[i]);
                String newS = removeChar(s, i);
                showedInCurrLoc.add(chars[i]);
                process(newS, newChars);
            }

        }
    }


    public void process(int[] nums, int end, List<Integer> res, List<List<Integer>> result) {
        if (end == 0) {
            result.add(res);
        }
        Set<Integer> showed = new HashSet<>();
        for (int i = 0; i < end; i++) {
            int cur = nums[i];
            if (!showed.contains(cur)) {
                int[] next = nums.clone();
                List<Integer> resNew = new ArrayList<>(List.copyOf(res));
                resNew.add(cur);
                swap(next, i, end - 1);
                showed.add(cur);
                process(next, end - 1, resNew, result);
            }

        }
    }

    private void swap(int[] arr, int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }

    public static String removeChar(String s, int index) {
        return s.substring(0, index) + s.substring(index + 1);
    }

    public static void fullDiffOrderWithSameChars() {

    }

    public static void process(char[] str, int i, List<Character> res) {

    }
}
