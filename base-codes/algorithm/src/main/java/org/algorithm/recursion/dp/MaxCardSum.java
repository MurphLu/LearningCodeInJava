package org.algorithm.recursion.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线。
 * 玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明。
 * 请返回最后获胜者的分数。[举例]
 *
 * arr=[1,2,100,4]。
 * 开始时，玩家A只能拿走1或4。如果开始时玩家A拿走1，则排列变为[2,100,4]，
 * 接下来玩家 B可以拿走2或4，然后继续轮到玩家A...
 * 如果开始时玩家A拿走4，则排列变为[1,2,100]，
 * 接下来玩家B可以拿走1或100，然后继续轮到玩家A...
 * 玩家A作为绝顶聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。所以玩家A会先拿1让排列变为[2,100,4]，
 * 接下来玩家B不管怎么选，100都会被玩家 A拿走。玩家A会获胜,分数为101。所以返回101。
 *
 * arr=[1, 100, 2]。
 * 开始时，玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜分数为100。所以返回100。
 */
public class MaxCardSum {
    public static void main(String[] args) {
        int[] cards = new int[]{1,2,3,4,8};
        System.out.println(win(cards));
        System.out.println(winWithDP(cards));
    }

    public static int win(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return Math.max(first(nums, 0, nums.length - 1), second(nums, 0, nums.length - 1));
    }

    public static int first(int[] cards, int l, int r) {
        if (l == r) {
            return cards[l];
        }
        return Math.max(cards[l] + second(cards, l+1, r), cards[r] + second(cards, l, r - 1));
    }

    public static int second(int[] cards, int l, int r) {
        if (l == r) {
            return 0;
        }
        return Math.min(first(cards, l + 1, r) ,first(cards, l, r - 1));
    }

    public static int winWithDP(int[] cards) {
        int[][] fdp = new int[cards.length][cards.length];
        int[][] sdp = new int[cards.length][cards.length];
        for (int i = 0; i < fdp.length; i++) {
            fdp[i][i] = cards[i];
        }
        int row = 0;
        int col = 1;
        while (col < cards.length) {
            int i = row;
            int j = col;
            while (i < cards.length && j < cards.length) {
                fdp[i][j] = Math.max(cards[i] + sdp[i + 1][j], cards[i] + sdp[i][j - 1]);
                sdp[i][j] = Math.max(fdp[i + 1][j], fdp[i][j - 1]);
                i++;
                j++;
            }
            col ++;
        }
        return Math.max(fdp[0][cards.length - 1], sdp[0][cards.length - 1]);
    }

    private static void f(int[] cards, int gap, int[][] firDP, int[][] secDP) {
        if (gap == cards.length) { return ;}
        /**
         * return Math.max(cards[l] + second(cards, l+1, r), cards[r] + second(cards, l, r - 1));
         */
        for (int i = 0; i + gap < cards.length; i++) {
            firDP[i][i + gap] = Math.max(cards[i] + secDP[i + 1][i + gap], cards[i] + secDP[i][i + gap - 1]);
        }
        s(cards, gap, firDP, secDP);
    }

    private static void s(int[] cards, int gap,  int[][] firDP, int[][] secDP) {
        if (gap == cards.length) { return ;}
        for (int i = 0; i + gap < cards.length; i++) {
            secDP[i][i + gap] = Math.max(firDP[i + 1][i + gap], firDP[i][i + gap - 1]);
        }
        f(cards, gap + 1, firDP, secDP);
    }
}
