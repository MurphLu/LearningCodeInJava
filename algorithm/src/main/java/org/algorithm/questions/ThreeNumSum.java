package org.algorithm.questions;

import java.util.*;

public class ThreeNumSum {
    public static void main(String[] args) {
        int[] arr = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        threeSum(arr);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        // 获取每个数字的个数，并存入 map 中
        for(int num : nums) {
            map.put(num, map.get(num)==null ? 1 : map.get(num) + 1);
        }
        // 将 key 取出并排序，也就是源数组中的剔除重复数字并排序
        Integer[] arr = map.keySet().toArray(new Integer[0]);
        Arrays.sort(arr);
        // 如果都为大于 0 或者都为 小于 0，那么直接返回空
        if (arr[0] > 0 || arr[arr.length - 1] < 0) {
            return result;
        }
        // 如果有 0 且个数大于 3，那么结果集中一定会有一个 [0,0,0]，直接先加入结果集
        if (map.get(0) != null &&  map.get(0)>=3) {
            result.add(Arrays.asList(0,0,0));
        }
        // 如果最小值为 0 或者最大值为 0，直接返回
        if (arr[0] == 0 || arr[arr.length - 1] == 0) {
            return result;
        }

        // 遍历去重并排序后的数组
        for (int i = 0; i < arr.length; i++) {
            // 如果当前值不为 0
            if (arr[i] != 0) {
                int val = arr[i];
                // 如果当前值的个数为 两个或以上，那么结果集中可能会存在 [val, val, ?]
                // 如果 map 中 -val*2 不为空，那么则说明结果存在，加入结果集中
                if ((map.get(val) > 1) && (map.get(-val * 2) != null)) {
                    result.add(Arrays.asList(val, val, -val*2));
                }
                // 获取结果集为 [0, ?, -?] 的情况
                // 只拿小于 0 或者 大于 0 一种情况来判断即可
                // 当前值 小于 0，源数组中有 0，并且源数组中存在 -val 的值，加入结果集
                if (val < 0 && map.get(0) != null && map.get(-val)!= null) {
                    result.add(Arrays.asList(val, 0, -val));
                }

                // 结果集中无 0 则有 [v1, v2, -(v1 + v2)]，三个数各不相同
                // v1, v2 为同符号
                // 去重数组中从当前数下一个遍历，v1, v2同符号，并且 -(v1 + v2) 存在，将 [v1, v2, -(v1 + v2)] 加入结果集
                for (int j = i+1;
                     j < arr.length && arr[j] != 0 &&
                             ((val < 0 && arr[j] < 0) || (val > 0 && arr[j] > 0));
                     j++) {
                    int valJ = arr[j];
                    if (map.get(-(val + valJ)) != null) {
                        result.add(Arrays.asList(val, valJ, -(val + valJ)));
                    }
                }
            }
        }
        // 返回
        return result;
    }
}
