package org.algorithm.leetcode.daily.needToAna;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//1,1,1,2,3,2,3

public class FindRotateSteps {
    public static void main(String[] args) {
        System.out.println(new FindRotateSteps().findRotateSteps("ababcab", "acbaacba"));
    }

    Map<Character, List<Integer>> ringMap = new HashMap<>();
    char[] keyChars = null;
    int dp[][] = null;
    int ringLength = 0;

    public int findRotateSteps(String ring, String key) {
        keyChars = key.toCharArray();
        char[] ringChars = ring.toCharArray();
        for(int i = 0; i < ringChars.length; i++ ){
            char c = ringChars[i];
            ringMap.put(c, ringMap.getOrDefault(c, new ArrayList<>()));
            ringMap.get(c).add(i);
        }
        dp = new int[ring.length()][key.length()];
        ringLength = ring.length();
        return process(0, 0) + key.length();
    }

    private int process(int ringIdx, int curKey){
        if (curKey == keyChars.length) {
            return 0;
        }
        if (dp[ringIdx][curKey] != 0) {
            return dp[ringIdx][curKey];
        }
        char key = keyChars[curKey];
        List<Integer> keyOnRingPoses = ringMap.get(key);
        int min = Integer.MAX_VALUE;
        for(int pos:keyOnRingPoses) {
            int a = Math.abs(pos-ringIdx);
            int b = ringLength - a;

            int step = Math.min(a, b);
            min = Math.min(process(pos, curKey + 1) + step, min);
        }
        dp[ringIdx][curKey] = min;
        return min;
    }
}
