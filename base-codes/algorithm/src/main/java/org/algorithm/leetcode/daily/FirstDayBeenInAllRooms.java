package org.algorithm.leetcode.daily;

/**
 * 你需要访问 n 个房间，房间从 0 到 n - 1 编号。同时，每一天都有一个日期编号，从 0 开始，依天数递增。你每天都会访问一个房间。
 *
 * 最开始的第 0 天，你访问 0 号房间。给你一个长度为 n 且 下标从 0 开始 的数组 nextVisit 。在接下来的几天中，你访问房间的 次序 将根据下面的 规则 决定：
 *
 * 假设某一天，你访问 i 号房间。
 * 如果算上本次访问，访问 i 号房间的次数为 奇数 ，那么 第二天 需要访问 nextVisit[i] 所指定的房间，其中 0 <= nextVisit[i] <= i 。
 * 如果算上本次访问，访问 i 号房间的次数为 偶数 ，那么 第二天 需要访问 (i + 1) mod n 号房间。
 * 请返回你访问完所有房间的第一天的日期编号。题目数据保证总是存在这样的一天。由于答案可能很大，返回对 109 + 7 取余后的结果。
 *
 *
 * n == nextVisit.length
 * 2 <= n <= 105
 * 0 <= nextVisit[i] <= i
 *
 * 由于通过 nextVisit[i] 无法到达 i 之后的房间，
 * 那么只有通过 (i + 1) mod n 的方式来到达 i+1 的房间
 * 也就是只有当 i 的访问次数为偶数是才能到达 i+1 的房间
 * 第一次到达 i+1 的房间，那么 i 房间的访问次数需要为 2
 *
 * 第一次是 i-1 直接跳到 i，那么 跳到 i-1 的天数需要算上，
 * 当 i 的访问次数为 1 时，会跳到 visited[i] 房间，那么下一次就是从 visited[i] 开始跳，
 * 那么第二轮则需要用 跳到 i-1 的天数 减去 visited[i] 之前的天数来计算再次到达 i-1 的天数
 * 最终结果则为第一轮+第二轮+两次跳到 i 的时间和
 * dp 一下
 *
 * 由于计算过程中有 mod，所以在天数想减时需要判断一下，如果被减数小则需要加 mod，直到被减数大时再进行计算
 */
public class FirstDayBeenInAllRooms {
    public static void main(String[] args) {
        int i = new FirstDayBeenInAllRooms().firstDayBeenInAllRooms(new int[]{
                0,1,1,2,2,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,14,14,15,15,16,16,17,17,18,18,19,19,20,20,21,21,22,22,23,23,24,24,25,25,26,26,27,27,28,28,29,29,30,30,31,31,32,32,33,33,34,34,35,35,36,36,37,37,38,38,39,39,40,40,41,41,42,42,43,43,44,44,45,45,46,46,47,47,48 // 0, 2,

        });
        System.out.println(i);
    }
    public int firstDayBeenInAllRooms(int[] nextVisit) {
        int n = nextVisit.length;
        long mod = 1000000007;
        long[] day = new long[n + 1];
        day[0] = 0;
        for (int i = 0; i < n; i++) {
            day[i + 1] = (day[i] + remove(day[i], day[nextVisit[i]], mod) + 2) % mod;
        }
        return (int)day[n - 1];
    }

    private long remove(long a, long b, long mod) {
        while (a < b) {
            a += mod;
        }
        return a-b;
    }
}
