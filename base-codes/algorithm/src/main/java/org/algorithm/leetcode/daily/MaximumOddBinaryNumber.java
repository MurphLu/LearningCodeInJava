package org.algorithm.leetcode.daily;

/**
 * 给你一个 二进制 字符串 s ，其中至少包含一个 '1' 。
 *
 * 你必须按某种方式 重新排列 字符串中的位，使得到的二进制数字是可以由该组合生成的 最大二进制奇数 。
 *
 * 以字符串形式，表示并返回可以由给定组合生成的最大二进制奇数。
 *
 * 注意 返回的结果字符串 可以 含前导零。
 */
public class MaximumOddBinaryNumber {
    public static void main(String[] args) {
        String s = new MaximumOddBinaryNumber().maximumOddBinaryNumber("010");
        System.out.println(s);
    }
    public String maximumOddBinaryNumber(String s) {
        char[] charArray = s.toCharArray();
        int firstZeroIdx = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '1') {
                if (firstZeroIdx == i)  {
                    firstZeroIdx++;
                }else {
                    swap(charArray, firstZeroIdx++, i);

                }
            }
        }
        swap(charArray, firstZeroIdx - 1, charArray.length - 1);
        return new String(charArray);
    }
    private void swap(char[] arr, int a, int b){
        char c1 = arr[a];
        arr[a] = arr[b];
        arr[b] = c1;
    }
}
