package org.algorithm.recursion;

public class Hano {
    public static void main(String[] args) {
        hano(3);
    }

    public static void hano(int n) {
        if (n > 0) func(n, "左", "右", "中");
    }

    private static void func(int i, String start, String end, String other){
        if (i == 1) {
            System.out.println("move 1 from " + start + " to " + end);
        } else {
            func(i - 1, start, other, end);
            System.out.println("move " + i + " from " + start + " to " + end);
            func(i - 1, other, end, start);
        }

    }
}
