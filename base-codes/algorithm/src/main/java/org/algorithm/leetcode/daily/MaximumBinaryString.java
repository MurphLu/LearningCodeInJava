package org.algorithm.leetcode.daily;
// 00 -> 10
// 10 -> 01
// 00011100
// 00011001
// 001

/**
 * 给你一个二进制字符串 binary ，它仅有 0 或者 1 组成。你可以使用下面的操作任意次对它进行修改：
 *
 * 操作 1 ：如果二进制串包含子字符串 "00" ，你可以用 "10" 将其替换。
 * 比方说， "00010" -> "10010"
 * 操作 2 ：如果二进制串包含子字符串 "10" ，你可以用 "01" 将其替换。
 * 比方说， "00010" -> "00001"
 * 请你返回执行上述操作任意次以后能得到的 最大二进制字符串 。
 * 如果二进制字符串 x 对应的十进制数字大于二进制字符串 y 对应的十进制数字，那么我们称二进制字符串 x 大于二进制字符串 y 。
 *
 * 最高位的连续 1 不动，第一个 0 之后的所有 1 都移到字符串末尾，此时字符串的收尾都是连续的 1，只有中间有 0
 * 中间的 0 只保留最后一位（无法替换为 1），其余的全都替换位 1
 */
public class MaximumBinaryString {
    public static void main(String[] args) {
        String s = maximumBinaryString("111");
        System.out.println(s);
    }
    public static String maximumBinaryString(String binary) {
        char[] chars = binary.toCharArray();
        int safeArea = -1;
        while (safeArea < binary.length() - 1 && chars[safeArea + 1] == '1') {
            safeArea++;
        }
        int zeroIdx = binary.length() - 1;
        while (zeroIdx > safeArea && chars[zeroIdx] == '1') {
            zeroIdx--;
        }
        int oneIdx = zeroIdx;
        while (oneIdx > safeArea) {
            if (chars[oneIdx] != '0') {
                chars[oneIdx] = '0';
                chars[zeroIdx] = '1';
                zeroIdx--;
            }
            oneIdx--;
        }
        for (int i = safeArea + 1; i < zeroIdx; i++) {
            chars[i] = '1';
        }
        return new String(chars);
    }
}
