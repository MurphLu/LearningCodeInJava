package org.algorithm.recursion;

public class Bags {

    private static int bagLimit = 100;

    public static void main(String[] args) {

    }

    public static void generate(int[] weights, int[] price) {

    }

    public static int process(int[] weights, int[] price, int cur, int totalWeight, int totalPrice) {
        if (totalWeight > bagLimit) {
            System.out.println(totalPrice);
            return 0;
        }

        if (cur == weights.length) {
            System.out.println(totalPrice);
            return totalPrice;
        }

        return Math.max(
                process(weights, price, cur + 1, totalWeight, totalPrice),
                process(weights, price, cur + 1, totalWeight + weights[cur], totalPrice + price[cur]));
    }

    public static int process(int[] weight, int[] price, int i , int currWeight) {
        if (currWeight > bagLimit) {
            return 0;
        }
        if (i == weight.length) {
            return 0;
        }
        return price[i] + Math.max(process(weight, price, i + 1, currWeight), process(weight, price, i + 1, currWeight + weight[i]));
    }
}
