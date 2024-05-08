package org.algorithm.leetcode;

/**
 * 将非负整数 num 转换为其对应的英文表示。
 *
 * 示例 1：
 *
 * 输入：num = 123
 * 输出："One Hundred Twenty Three"
 * 示例 2：
 *
 * 输入：num = 12345
 * 输出："Twelve Thousand Three Hundred Forty Five"
 * 示例 3：
 *
 * 输入：num = 1234567
 * 输出："One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */
public class NumberToWords {

    public static void main(String[] args) {
        String s = new NumberToWords().numberToWords(20);
        System.out.println(s);
    }

    String[] wordForOneBit = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] wordForTwoBit = new String[]{"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String[] wordForTwoBitStart1 = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] thirdLevel = new String[]{"Thousand", "Million", "Billion"};
    // 个 十 百 千 / 个 十 百 千 个 十

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        String res = "";
        int level = -1;
        int modRes;
        while (num>0) {
            if ((modRes = num % 1000) > 0) {
                String temp = handleLow4(modRes).trim();
                if (!temp.isEmpty()) {
                    res = temp + " " + (level == -1 ? "":thirdLevel[level]) + " " + res;
                }

            }
            level++;
            num /= 1000;
        }
        return res.trim();
    }



    private String handleLow4(int num) {
        int b = num % 1000;
        int a = b % 100;
        String s = handleLow2(a).trim();
        b = num / 100;
        if (b > 0) {
            s = wordForOneBit[b%10-1] + " Hundred " + s;
        }
        return s;
    }


    private String handleLow2(int a) {
        if (a<=0){
            return "";
        }
        if (a < 10) {
            return wordForOneBit[a-1];
        } else if (a < 20) {
            return wordForTwoBitStart1[a-10];
        } else {
            return wordForTwoBit[a/10-2] + " " + handleLow2(a%10-1);
        }
    }
}
