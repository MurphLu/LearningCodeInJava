package org.algorithm.utils;

import java.util.Arrays;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringToArray {
    public static void main(String[] args) {
        generateArray("[[0,1],[1,3],[2,3],[4,0],[4,5]]");
    }
    public static int[][] generateArray(String str) {
        str = str.substring(2, str.length() - 2);
        String[] split = str.split("],\\[");
        int[][] arr = Arrays.stream(split).map( o -> {
            String[] inner = o.split(",");
            return Arrays.stream(inner).mapToInt(Integer::parseInt).toArray();
        }).toArray(int[][]::new);
//        System.out.println(Arrays.deepToString(arr));
        return arr;
    }
}
