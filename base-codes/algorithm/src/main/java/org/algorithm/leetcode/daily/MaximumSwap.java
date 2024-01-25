package org.algorithm.leetcode.daily;

import java.util.Stack;

/**
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。
 *
 * 示例 1 :
 *
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 * 示例 2 :
 *
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 * 注意:
 *
 * 给定数字的范围是 [0, 108]
 */
public class MaximumSwap {
    public static void main(String[] args) {
        System.out.println(new MaximumSwap().maximumSwap1(1993));
        System.out.println(new MaximumSwap().maximumSwap1(98368));
    }
    public int maximumSwap(int num) {
        String s = ""+num;
        char[] charArray = s.toCharArray();
        Stack<Integer> lc = new Stack<>();
        lc.push(0);
        Integer high = null;
        Integer low = null;

        for (int i = 1; i < charArray.length; i++) {
            char cur = charArray[i];
            while (!lc.isEmpty() && (cur > charArray[lc.peek()])){
                int temp = lc.pop();
                if (high == null) {
                    high = temp;
                } else {
                    high = Math.min(high, temp);
                }
            }
            lc.push(i);
        }
        if (high == null) {
            return num;
        }
        while (!lc.isEmpty() && lc.peek() > high) {
            int tempLow = lc.pop();
            if (low == null) {
                low = tempLow;
            }
            if (charArray[tempLow] > charArray[low]) {
                low = tempLow;
            }
        }

        swap(high, low, charArray);
        return Integer.parseInt(new String(charArray));
    }
    public int maximumSwap1(int num) {
        String s = ""+num;
        char[] charArray = s.toCharArray();
        int maxIndex = -1;
        int low = -1;
        int tempMaxIndex = charArray.length - 1;
        for (int i = charArray.length - 2; i >= 0; i--) {
            char curr = charArray[i];
            if (charArray[tempMaxIndex] < curr) {
                tempMaxIndex = i;
            } else if(charArray[tempMaxIndex] > curr) {
                low = i;
                maxIndex = tempMaxIndex;
            }
        }
        if (low == -1) {
            return num;
        } else {
            swap(low, maxIndex, charArray);
            return Integer.parseInt(new String(charArray));
        }
    }

    private void swap(int i, int j, char[] arr){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
