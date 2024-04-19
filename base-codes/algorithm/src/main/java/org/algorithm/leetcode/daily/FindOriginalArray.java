package org.algorithm.leetcode.daily;

import java.util.*;
/**
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
 *
 * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
 *
 * 排序
 */
public class FindOriginalArray {
    public static void main(String[] args) {
        int[] originalArray = new FindOriginalArray().findOriginalArray(
                new int[]{6,3,0,1}
        );
        System.out.println(Arrays.toString(originalArray));
    }
    public int[] findOriginalArray(int[] changed) {
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(changed);
        int[] res = new int[changed.length / 2];
        int idx = 0;

        for (int i = changed.length - 1; i >= 0; i--) {
            int val = changed[i];
            int count = map.getOrDefault(val, 0);
            if (count == 0) {
                if (val % 2 != 0) {
                    return new int[0];
                }
                int ori = val / 2;
                map.put(ori, map.getOrDefault(ori, 0)+1);
            } else {
                res[idx++] = val;
                count -= 1;
                if (count == 0) {
                    map.remove(val);
                } else {
                    map.put(val, count);
                }
            }
        }
        if (map.isEmpty()) {
            return res;
        } else {
            return new int[0];
        }
    }
}
