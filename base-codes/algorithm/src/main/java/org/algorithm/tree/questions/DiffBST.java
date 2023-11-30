package org.algorithm.tree.questions;

import java.util.Map;

public class DiffBST {

    public static void main(String[] args) {
        System.out.println(count(4));
    }

    public static int count(int n) {
        int[] ic = new int[n+1];
        ic[0] = 0;
        ic[1] = 1;
        for (int i = 2; i <= n; i++) {
            int c = 0;
            for (int j = 0; j < i; j++) {
                int lc = j;
                int rc = i-j-1;
                if(lc == 0 || rc == 0){
                    c += lc == 0 ? ic[rc] : ic[lc];
                }else {
                    c += ic[lc] * ic[rc];
                }

            }
            ic[i] = c;
        }
        return ic[n];
    }
}
