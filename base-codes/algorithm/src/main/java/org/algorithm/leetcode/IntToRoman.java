package org.algorithm.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IntToRoman {
    public static void main(String[] args) {
        System.out.println(new IntToRoman().intToRoman(1994));
    }
    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        Integer[] integers = map.keySet().toArray(Integer[]::new);
        Arrays.sort(integers, (o1, o2) -> o2 -o1);
        for (Integer i: integers) {
            while (num >= i && num >=0) {
                builder.append(map.get(i));
                num -= i;
            }
        }

        return builder.toString();
    }
}
