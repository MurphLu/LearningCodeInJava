package org.algorithm.leetcode.daily;

import java.util.*;

// 一定有环，可能有多个组
// 环的长度大于 2，那么就取环的长度
// 如果换的长度等于2，则取到环上两个节点最长距离之和
public class MaximumInvitations {
    public static void main(String[] args) {
        System.out.println(maximumInvitations(new int[]{7,0,7,13,11,6,8,5,9,8,9,14,15,7,11,6}));
        // 1,0,0,2,1,4,7,8,9,6,7,  10, 8
        // 0 1 2 3 4 5 6 7 8 9 10 11   12
    }

    public static int maximumInvitations(int[] favorite) {
        Set<Integer> checked = new HashSet<>();
        Map<Integer, Integer> twoPointMap = new HashMap<>();
        int max = 0;
        for (int i = 0; i < favorite.length; i++) {
            int temp = i;
            if (checked.contains(temp)) {
                continue;
            }
            Map<Integer, Integer> map = new HashMap<>();
            int index = 0;
            while (!map.containsKey(temp)) {
                map.put(temp, index++);
                temp = favorite[temp];
            }
            if (index - map.get(temp) == 2) {
                twoPointMap.put(temp, Math.max(twoPointMap.getOrDefault(temp, 0), index));
            } else {
                checked.addAll(map.keySet());
            }
            max = Math.max(index - map.get(temp), max);
        }
        int twoMax = 0;
        for (Map.Entry<Integer,Integer> entry: twoPointMap.entrySet()) {
            twoMax += (entry.getValue() + twoPointMap.get(favorite[entry.getKey()]) - 2);
        }
        twoMax /= 2;
        return Math.max(twoMax, max);
    }
}
