package org.algorithm.leetcode.daily.needToAna;

import java.util.*;

// 四数之和
public class FourSum {
    public static void main(String[] args) {
        new FourSum().fourSum(new int[]{1,0,-1,0,-2,2}, 0);
        System.out.println(Integer.MAX_VALUE);
    }
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Map<Long, List<String>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n ; i++) {
            for (int j = i+1; j < n; j++) {
                long sum = nums[i] + nums[j];
                map.put(sum, map.getOrDefault(sum, new ArrayList<>()));
                map.get(sum).add(i+"x"+j);
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        Set<String> resSet = new HashSet<>();
        for(Map.Entry<Long, List<String>> entry: map.entrySet()) {
            Long key = entry.getKey();
            Long nextKey = target - key;
            if (map.containsKey(nextKey)) {
                List<List<Integer>> lists = mergeResult(nums, entry.getValue(), map.get(nextKey), resSet);
                res.addAll(lists);
            }
        }
        return res;
    }

    private int[] minimize(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for(int num:nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) <= 4) {
                list.add(num);
            }
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private List<List<Integer>> mergeResult(int[] nums, List<String> ls1, List<String> ls2, Set<String> resSet) {
        List<List<Integer>> res = new ArrayList<>();
        for (String s1 : ls1) {
            for (String s2 : ls2) {
                String[] sc1 = s1.split("x");
                String[] sc2 = s2.split("x");
                if (!Objects.equals(sc1[0], sc2[0]) && !Objects.equals(sc1[0], sc2[1]) &&
                        !Objects.equals(sc1[1], sc2[0]) && !Objects.equals(sc1[1], sc2[1])) {
                    int[] r = new int[]{
                            nums[Integer.parseInt(sc1[0])],
                            nums[Integer.parseInt(sc2[0])],
                            nums[Integer.parseInt(sc1[1])],
                            nums[Integer.parseInt(sc2[1])]};
                    Arrays.sort(r);
                    if(!resSet.contains(Arrays.toString(r))){
                        List<Integer> list = new ArrayList<>();
                        for(int s:r) {
                            list.add(s);
                        }
                        res.add(list);
                    }
                    resSet.add(Arrays.toString(r));
                }
            }
        }
        return res;
    }
}
