package org.algorithm.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChildCollections {
    public static void main(String[] args) {
        childCollections("abc");
    }

    public static void childCollections(String s) {
        process(s, 0, new ArrayList<>());
    }

    public static void process(String s, int position, List<Character> res) {
        if (position == s.length()) {
            System.out.println(res);
            return;
        }
        Character c = s.toCharArray()[position];
        List<Character> newListWithCur = new ArrayList<>(List.copyOf(res));
        newListWithCur.add(c);
        // 每一个字符可以分裂出包含该字符及不包含该字符两种情况，递归
        process(s, position + 1, newListWithCur);
        List<Character> newListWithoutCur = new ArrayList<>(List.copyOf(res));
        process(s, position + 1, newListWithoutCur);
    }
}
