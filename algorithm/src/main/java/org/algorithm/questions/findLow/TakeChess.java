package org.algorithm.questions.findLow;

/**
 * 有 N 个棋子
 * 两个人轮流拿，每个人一次拿 4^i 次方个棋子 1，4，16，64...
 * 两个人都很聪明
 * 最终谁会获胜
 */
public class TakeChess {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(i + "-" + new TakeChess().startGame(i));
        }
    }

    public String startGame(int n) {
        if (n < 5) {
            return n == 0 || n == 2 ? "后" : "先";
        }
        int base = 1;
        while (base < n) {
            if (startGame(n - base).equals("后")) {
                return "先";
            }
            if (base > n / 4){
                break;
            }
            base *= 4;
        }
        return "后";
    }

    public String startGameImprove(int n) {
        return n % 5 == 0 || n % 5 == 2 ? "后" : "先";
    }
}
