package org.algorithm.leetcode.daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给你一个整数数组 matches 其中 matches[i] = [winneri, loseri] 表示在一场比赛中 winneri 击败了 loseri 。
 *
 * 返回一个长度为 2 的列表 answer ：
 *
 * answer[0] 是所有 没有 输掉任何比赛的玩家列表。
 * answer[1] 是所有恰好输掉 一场 比赛的玩家列表。
 * 两个列表中的值都应该按 递增 顺序返回。
 *
 * 注意：
 *
 * 只考虑那些参与 至少一场 比赛的玩家。
 * 生成的测试用例保证 不存在 两场比赛结果 相同 。
 *
 * 1 <= matches.length <= 10^5
 * matches[i].length == 2
 * 1 <= winneri, loseri <= 10^5
 * winneri != loseri
 * 所有 matches[i] 互不相同
 */
public class FindWinners {
    static class Player {
        int win;
        int lose;
        int take;

        public Player() {
            win = 0;
            lose = 0;
            take = 0;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "win=" + win +
                    ", lose=" + lose +
                    ", take=" + take +
                    '}';
        }
    }

    public List<List<Integer>> findWinners(int[][] matches) {
        Player[] p = new Player[100001];

        int maxPlayer = 0;
        for(int[] match : matches) {
            int winner = match[0];
            int loser = match[1];
            if (p[winner] == null) {
                p[winner] = new Player();
            }
            if (p[loser] == null){
                p[loser] = new Player();
            }
            p[winner].win++;
            p[winner].take++;
            p[loser].lose++;
            p[loser].take++;
            maxPlayer = Math.max(maxPlayer, winner);
            maxPlayer = Math.max(maxPlayer, loser);
        }
        List<Integer> ans1 = new ArrayList<>();
        List<Integer> ans2 = new ArrayList<>();
        for (int i = 1; i <= maxPlayer; i++) {
            Player pl = p[i];
            if (pl == null) {
                continue;
            }
            if (pl.lose == 0) {
                ans1.add(i);
            }
            if (pl.lose == 1) {
                ans2.add(i);
            }
        }

        return Arrays.asList(ans1, ans2);
    }
}
