package org.algorithm.questions;

import java.lang.reflect.Array;
import java.util.*;

public class Multiply {
    public static void main(String[] args) {
        new Multiply().multiply("100", "1000");
    }

    public String multiply(String num1, String num2){
        if (Objects.equals(num1, "0") || Objects.equals(num2, "0")) { return "0";}
        char[] chs1 = num1.toCharArray();
        char[] chs2 = num2.toCharArray();
        Queue<Character> result = new LinkedList<>();

        for (int i = chs1.length - 1; i >= 0; i--) {
            Queue<Character> cur = new LinkedList<>();
            for (int j = chs1.length - 1; j > i; j--) {
                cur.add(intToCharInt(0));
            }

            int v1 = intCharToInt(chs1[i]);
            int upper = 0;
            for (int j = chs2.length - 1; j >= 0; j--) {
                int v2 = intCharToInt(chs2[j]);
                int t = v1 * v2 + upper;
                int val = t % 10;
                upper = t / 10;
                cur.add(intToCharInt(val));
            }
            if (upper != 0) { cur.add(intToCharInt(upper));}
            Queue<Character> temp = new LinkedList<>();
            upper = 0;
            while (!cur.isEmpty() || !result.isEmpty()) {
                int curSum = (cur.isEmpty() ? 0 : intCharToInt(cur.poll())) + (result.isEmpty() ? 0 : intCharToInt(result.poll())) + upper;
                int val = curSum % 10;
                upper = curSum / 10;
                temp.add(intToCharInt(val));
            }
            if (upper != 0) { temp.add(intToCharInt(upper));}

            result = temp;
        }
        StringBuilder s = new StringBuilder();
        while (!result.isEmpty()) {
            s.insert(0, result.poll());
        }

        return s.toString();
    }

    private Character intToCharInt(int val) {
        return (char)(val + '0');
    }

    private int intCharToInt(char c) {
        return c - '0';
    }
}
