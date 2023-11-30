package org.algorithm.leetcode.daily;

public class LongestBalancedSubstring {
    public int findTheLongestBalancedSubstring(String s) {
        int zeroCount = 0;
        int oneCount = 0;
        int max = 0;
        int index = 1;
        while(index < s.length()) {
            char c = s.charAt(index);
            if(c == '0') {
                if (index != 0 && s.charAt(index - 1) == '1'){
                    zeroCount = 0;
                    oneCount = 0;
                }
                zeroCount++;
            } else if(c == '1') {
                if(zeroCount > oneCount && zeroCount != 0) {
                    oneCount++;
                    max = Math.max(oneCount * 2, max);
                }else {
                    zeroCount = 0;
                    oneCount = 0;
                }
            }
            index++;
        }
        return max;
    }
}
