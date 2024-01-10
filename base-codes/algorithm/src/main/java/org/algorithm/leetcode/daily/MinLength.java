package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinLength {
    public static void main(String[] args) {
        System.out.println(new MinLength().minLength("ABFCACDB"));
    }
    public int minLength(String s) {
        return remove(s).length();
    }

    public String remove(String s) {
        if (s.contains("AB") || s.contains("CD")){
            String nextS  = s;
            if(s.contains("AB")) {
                nextS = s.replace("AB", "");
            }
            if (s.contains("CD")) {
                nextS = s.replace("CD", "");
            }
            return remove(nextS);
        }
        return s;
    }
}
