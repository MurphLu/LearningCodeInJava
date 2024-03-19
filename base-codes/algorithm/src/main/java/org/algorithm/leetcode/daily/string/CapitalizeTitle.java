package org.algorithm.leetcode.daily.string;

public class CapitalizeTitle {
    public static void main(String[] args) {
        System.out.println(new CapitalizeTitle().capitalizeTitle("aaa Aa aa"));
    }
    public String capitalizeTitle(String title) {
        String[] splitStr = title.split(" ");
        for (int i = 0; i < splitStr.length; i++) {
            String s = splitStr[i];
            char[] charArray = s.toCharArray();
            int dis = 'a' - 'A';
            if (charArray.length <= 2) {
                if (charArray[0] < 'a' ) {
                    charArray[0] += dis;
                }
            } else {
                if (charArray[0] > 'Z' ) {
                    charArray[0] -= dis;
                }
            }
            for (int j = 1; j < charArray.length; j++) {
                if (charArray[j] < 'a' ) {
                    charArray[j] += dis;
                }
            }
            splitStr[i] = new String(charArray);
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < splitStr.length; i++) {
            builder.append(splitStr[i]);
            if (i + 1 < splitStr.length) {
                builder.append(" ");
            }
        }
        return builder.toString();
    }
}
