package org.algorithm.questions.preHandle;

import java.util.Arrays;

/**
 * 牛牛有一些排成一行的正方形。
 * 每个正方形已经被染成红色或者绿色。
 * 牛牛现在可以选择任意一个正方形然后用这两种颜色的任意一种进行染色,这个正方形的颜色将会被覆盖。
 * 牛牛的目标是在完成染色之后,每个红色R都比每个绿色G距离最左侧近。
 * 牛牛想知道他最少需要涂染几个正方形。
 *
 * 如样例所示: S = RGRGR
 * 我们涂染之后变成RRRGG满足要求了,涂染的个数为2,没有比这个更好的涂染方案
 */
public class Paint {
    public static void main(String[] args) {
        System.out.println(paint("RGRGRGGR"));
    }

    public static int paint(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        int difLeft = 0;
        int difRight = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] != 'G') {
                difRight ++;
            }
        }
        result = difRight;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'G') {
                difLeft++;
            }
            if (chars[i] == 'R') {
                difRight--;
            }
            result = Math.min(result, difLeft + difRight);
        }
        return result;
    }
}
