package org.algorithm.leetcode.topHot100;

import java.util.*;

// 出现次数最多的前 k 个元素
public class TopKFrequent {
    public static void main(String[] args) {
        int[] arr = new int[]{4,1,-1,2,-1,2,3};

        System.out.println(Arrays.toString(new TopKFrequent().topKFrequent(arr, 2)));
    }
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.containsKey(nums[i])? map.get(nums[i]) + 1 : 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.add(entry);
            } else {
                if (queue.peek().getValue() < entry.getValue()) {
                    queue.poll();
                    queue.add(entry);
                }
            }
        }
        int[] arr = new int[k];
        int idx = 0;
        while (!queue.isEmpty()) {
            arr[idx++] = queue.poll().getKey();
        }
        return arr;
    }
}
