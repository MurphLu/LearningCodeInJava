package org.algorithm.leetcode.daily;

import java.util.*;

public class MaximumSumQueries {
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        int[][] zip = new int[n][3];
        for (int i = 0; i < n; i++) {
            zip[i][0] = nums1[i] + nums2[i];
            zip[i][1] = nums1[i];
            zip[i][2] = nums2[i];
        }
        Arrays.sort(zip, (x, y) -> y[0] - x[0]);
        int q = queries.length;
        int[] ans = new int[q];
        Map<String, Integer> dp = new HashMap<>();
        for (int i = 0; i < q; i++) {
            String key = queries[i][0] + " " +  queries[i][1];
            if (dp.containsKey(key)) {
                ans[i] = dp.get(key);
            }
            else {
                ans[i] = -1;
                for (int j = 0; j < n; j++) {
                    if (zip[j][0] < queries[i][0] + queries[i][1]) break;
                    if (zip[j][1] >= queries[i][0] && zip[j][2] >= queries[i][1]) {
                        ans[i] = zip[j][0];
                        break;
                    }
                }
                dp.put(key, ans[i]);
            }
        }
        return ans;

    }
}
