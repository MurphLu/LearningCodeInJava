package org.algorithm.questions.intermediate;

import java.util.Arrays;

/**
 * 有n个打包机器从左到右一字排开，上方有一个自动装置会抓取一批放物品到每个打包机上，
 * 放到每个机器上的这些物品数量有多有少，由于物品数量不相同，
 * 需要工人将每个机器上的物品进行移动从而到达物品数量相等才能打包。每个物品重量太大、每次只能搬一个物品进行移动，
 * 为了省力，只在相邻的机器上移动。请计算在搬动最小轮数的前提下，使每个机器上的物品数量相等。
 * 如果不能使每个机器上的物品相同返回-1。
 * 例如[1,0,5]表示有3个机器，
 * 每个机器上分别有1、0、5个物品，
 * 经过这些轮后:第一轮:1 0 <- 5 =>1 1 4 第二轮: 1 <-1<- 4 =>2 1 3 第三轮:2 1 <- 3 => 22移动了3轮，每个机器上的物品相等，所以返回3
 *
 * 例如[2,2,3]表示有3个机器，
 * 每个机器上分别有2、2、3个物品，这些物品不管怎么移动，
 * 都不能使三个机器上物品数量相等，返回-1
 */
public class AvgPackages {
    public static void main(String[] args) {

    }

    public int getSteps(int[] arr) {
        int avg = 0;
        int sum = Arrays.stream(arr).sum();
        if(sum % arr.length != 0) {
            return -1;
        }
        avg = sum / arr.length;
        int leftSum = 0;
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            // 负需要输入，正需要输出
            int leftRes = leftSum - i * avg;
            // 负需要输入，正需要输出
            int rightRest = (sum - leftSum - arr[i]) - (arr.length - i - 1) * avg;
            // 如果两侧都为负，那么则需要从该位置输出 leftRest + rightRest 件
            if (leftRes < 0 && rightRest < 0) {
                ans = Math.max(ans, Math.abs(leftRes) + Math.abs(rightRest));
            } else { // 其他情况，只取绝对值大的那个，
                // 因为各个位置可以同时走，在满足绝对值大的那个条件时，绝对值小的一定已经满足
                ans = Math.max(ans, Math.max(Math.abs(leftRes), Math.abs(rightRest)));
            }
            leftSum += arr[i];
        }
        return ans;
    }
}
