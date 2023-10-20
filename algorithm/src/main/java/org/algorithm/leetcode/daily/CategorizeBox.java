package org.algorithm.leetcode.daily;

public class CategorizeBox {
    public String categorizeBox(int length, int width, int height, int mass) {
        int volume = (int)Math.pow(10, 9);
        int size = (int)Math.pow(10, 4);
        boolean heavy = mass >= 100;
        boolean bulky = false;
        boolean sizeA = length >= size || width >= size || height >= size;
        volume /= length;
        volume /= width;
        boolean sizeB = volume < height;
        bulky = sizeB || sizeA;
        return bulky && heavy ? "Both" : bulky ? "Bulky" : heavy ? "Heavy" : "Neither";
    }
}
