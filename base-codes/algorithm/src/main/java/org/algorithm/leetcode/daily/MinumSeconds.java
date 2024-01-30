package org.algorithm.leetcode.daily;

import java.util.*;
// 2,1,1,2,1,2

// 0,1,2,3,4,5
public class MinumSeconds {

    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(5, 5, 5, 5, 5, 5);
        new MinumSeconds().minimumSeconds(list1);
    }
    private static class Info{
        int minPos;
        int maxPos = -1;
        int maxDis = -1;
        int count = 0;
        int size;

        public Info(int minPos, int size) {
            this.minPos = minPos;
            this.size = size;
            count += 1;
        }

        public void setMax(int maxPos){
            this.maxDis = Math.max((this.maxPos == -1 ? maxPos - minPos : maxPos - this.maxPos) - 1, this.maxDis) ;
            this.maxPos = maxPos;
            count += 1;
        }

        public int generateMax(){
            int newMax = size - (maxPos - minPos + 1);
            if (count > 1) {
                maxDis = Math.max(newMax, maxDis);
            } else {
                maxDis = size - 1;
            }
            return maxDis;
        }

        @Override
        public String toString() {
            return "Info{" +
                    "minPos=" + minPos +
                    ", maxPos=" + maxPos +
                    ", maxDis=" + maxDis +
                    '}';
        }
    }
    public int minimumSeconds(List<Integer> nums) {
        Map<Integer, Info> positionMap = new HashMap<>();
        for (int i = 0; i < nums.size(); i++) {
            int cur = nums.get(i);
            if (positionMap.get(cur) == null) {
                positionMap.put(cur, new Info(i, nums.size()));
            } else {
                positionMap.get(cur).setMax(i);
            }
        }
        int minDis = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Info>  entry: positionMap.entrySet()) {
            int i = entry.getValue().generateMax();
            minDis = Math.min(i, minDis);
        }
        return minDis % 2 == 0 ? minDis / 2 : (minDis + 1) / 2;
    }
}
