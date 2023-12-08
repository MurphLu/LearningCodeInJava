package org.algorithm.leetcode.daily;

import org.algorithm.utils.StringToArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 你驾驶出租车行驶在一条有 n 个地点的路上。这 n 个地点从近到远编号为 1 到 n ，你想要从 1 开到 n ，通过接乘客订单盈利。你只能沿着编号递增的方向前进，不能改变方向。
 *
 * 乘客信息用一个下标从 0 开始的二维数组 rides 表示，其中 rides[i] = [starti, endi, tipi] 表示第 i 位乘客需要从地点 starti 前往 endi ，愿意支付 tipi 元的小费。
 *
 * 每一位 你选择接单的乘客 i ，你可以 盈利 endi - starti + tipi 元。你同时 最多 只能接一个订单。
 *
 * 给你 n 和 rides ，请你返回在最优接单方案下，你能盈利 最多 多少元。
 *
 * 注意：你可以在一个地点放下一位乘客，并在同一个地点接上另一位乘客。
 */
public class MaxTaxiEarnings {
    public static void main(String[] args) {
        int[][] rides = StringToArray.generateArray("[[1,6,1],[3,10,2],[10,12,3],[11,12,2],[12,15,2],[13,18,1]]");
        System.out.println(new MaxTaxiEarnings().maxTaxiEarnings(20, rides));
    }
    public long maxTaxiEarnings(int n, int[][] rides) {
        long[] record = new long[n + 1];
        Map<Integer, List<int[]>> ridesMap = new HashMap<>();
        for (int[] ride: rides) {
            int end = ride[1];
            ridesMap.put(end, ridesMap.getOrDefault(end, new ArrayList<>()));
            ridesMap.get(end).add(ride);
        }
        for (int i = 1; i <= n; i++) {
            if (ridesMap.containsKey(i)) {
                List<int[]> rideList = ridesMap.get(i);
                long curEarning = 0;
                for (int[] ride : rideList) {
                    int start = ride[0];
                    int end = ride[1];
                    int tips = ride[2];
                    int earn = end - start + tips;
                    long totalEarn = earn + record[start];
                    curEarning = Math.max(curEarning, Math.max(totalEarn, record[i - 1]));
                }
                record[i] = curEarning;
            } else {
                record[i] = record[i - 1];
            }
        }
        return record[n];
    }
}
