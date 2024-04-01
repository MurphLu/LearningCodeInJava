package org.algorithm.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtils {

    static class Utils <T> {
        public void reverse(List<T> list) {
            int left = 0;
            int right = list.size() - 1;
            while (left < right) {
                T temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                left++;
                right--;
            }
        }

        public List<T> reversed(List<T> list) {
            List<T> rev = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                rev.add(list.get(list.size() - 1 - i));
            }
            return rev;
        }
    }

    public static void reverse(List<Character> list) {
        new Utils<Character>().reverse(list);
    }

    public static List<Character> reversed(List<Character> list) {
        return new Utils<Character>().reversed(list);
    }
}
