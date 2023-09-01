package org.algorithm.questions;


public class FindOutOddTimeNumbers {

    public static void main(String[] args) {
        int[] arr = {1,1,2,2,3,3,3,3,4,5,5,6,6};
        find1OddTimeNumber(arr);
        int[] arr2 = {1,1,2,2,3,3,3,3,4,5,5,6,6,6};
        find2OddTimeNumbers(arr2);
    }

    // 数组中只有一个数字出现奇数次，其他都出现偶数次，请找出这个数
    public static void find1OddTimeNumber(int[] arr){
        int eor = 0;
        for (int j : arr) {
            eor ^= j;
        }
        System.out.println(eor);
    }

    // 数组中有两个数字出现奇数次，其他都出现偶数次，请找出这两个数
    public static void find2OddTimeNumbers(int[] arr){
        int eor = 0;
        // eor 结果为出现奇数次数字的异或结果
        for (int j : arr) {
            eor ^= j;
        }

        // 从异或结果中取出最右侧的 1，两个奇数次出现的数字在此位上必不同，
        // 这样可以通过 rightOne 将数组中的数字分为两组，此位为1的和此位为0的
        // eor  =  011010
        // ~eor =  100101
        // +1   =  100110
        // eor & = 000010
        int rightOne = eor & (~eor + 1);
        int one = 0;
        for (int cur: arr) {
            // 找出rightOne位置上为1的数字，并将他们进行异或运算，这样就能得到该为1且出现次数为奇数的数字
            if((cur & rightOne) == rightOne) {
                one ^= cur;
            }
        }
        // 得到该为1且出现次数为奇数的数字
        int zero = eor ^ one;
        System.out.println(one + " " + zero);
    }
}
