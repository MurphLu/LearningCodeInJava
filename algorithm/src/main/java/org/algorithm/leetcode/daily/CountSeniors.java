package org.algorithm.leetcode.daily;

public class CountSeniors {
    public static void main(String[] args) {
        String[] details = new String[]{"5612624052M0130","5378802576M6424","5447619845F0171","2941701174O9078"};
        countSeniors(details);
    }
    public static int countSeniors(String[] details) {
        int count = 0;
        for (int i = 0; i < details.length; i++) {
            System.out.println(details[i].substring(11,13));
            if (details[i].substring(11, 12).compareTo("60") > 0) {
                count ++;
            }
        }
        return count;
    }
}
