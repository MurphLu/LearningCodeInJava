package org.algorithm.stringAlgorithm;

/**
 * KMP算法解决的问题
 * 字符串str1和str2，str1是否包含str2，如果包含返回str2在str1中开始的位置
 * 如何做到时间复杂度0(N)完成?

 * Map reduce
 */
public class Kmp {
    public static void main(String[] args) {
        System.out.println(new Kmp().func("aaaaaabcabc", "abcabc"));
    }

    public int func(String str1, String str2) {
        if (str1 == null || str2 == null ||
                str1.isEmpty() || str2.isEmpty() ||
                str1.length() < str2.length()) {
            return -1;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] fixCount = getNextArr(chars2);

        int i1 = 0;
        int i2 = 0;
        while (i1 < chars1.length && i2 < chars2.length) {
            if (chars1[i1] == chars2[i2]) {
                i1 ++;
                i2 ++;
            } else if (fixCount[i2] == -1) {
                i1 ++;
            } else {
                i2 = fixCount[i2];
            }
        }
        return i2 == str2.length() ? i1 - i2 : -1;
    }

    private int[] getNextArr(char[] ms) {
        int[] fixCount = new int[ms.length];
        fixCount[0] = -1;
        fixCount[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < fixCount.length) {
            // cn 为上一字符之前的最大相等前后缀的长度，那么如果 ms[i - 1] 字符与 ms[cn] 位置的字符相等的话，那么当前位置的最长相等前后缀的长度就位前一个字符最长相等前后缀的长度 + 1
            // absabscfdabsabsdce
            // 比如到 s 时 fixCount 为 [-1, 0, 0, 0, 1, 2, 3, 0, 0, 0, 1, 2, 3, 4, 5] absab = absab
            // cn = 5; i = 14
            // 当到下一个字符时 i + 1，i = 15, ms[i - 1] = s, ms[cn] = ms[5] = s 相等，
            // 则 fixCount[15] = 5+1 = 6 absabs = absabs
            // 然后继续 cn = 6; i = 16
            // ms[i - 1] = d, ms[cn] = ms[6] = c
            // 不相等，则 cn = 3，index 为 6 的字符前最大前后缀长度为 3
            // 继续与 3位置字符比较，还不相等，那么 cn = 0
            // 最终 i = 16的最大前后缀长度为 0，它的下一个字符之前字符的前后缀最大长度则从 0 开始计算

            if (ms[i - 1] == ms[cn]) {
                fixCount[i++] = ++cn;
            } else if (cn > 0) {
                cn = fixCount[cn];
            } else {
                fixCount[i ++] = 0;
            }
        }
//        for (int i = 1; i < ms.length; i++) {
//            int length = 1;
//            int maxLength = 0;
//            while (i > length) {
//                String prefix = str2.substring(0, length);
//                String suffix = str2.substring(i-length, i);
//                if (prefix.equals(suffix)) {
//                    maxLength = length;
//                }
//                length ++;
//            }
//            fixCount[i] =  maxLength;
//        }
        return fixCount;
    }
}
