package org.algorithm.leetcode.daily;

import java.util.*;

/**
 *
 * Alice 和 Bob 轮流玩一个游戏，Alice 先手。
 *
 * 一堆石子里总共有 n 个石子，轮到某个玩家时，他可以 移出 一个石子并得到这个石子的价值。Alice 和 Bob 对石子价值有 不一样的的评判标准 。双方都知道对方的评判标准。
 *
 * 给你两个长度为 n 的整数数组 aliceValues 和 bobValues 。aliceValues[i] 和 bobValues[i] 分别表示 Alice 和 Bob 认为第 i 个石子的价值。
 *
 * 所有石子都被取完后，得分较高的人为胜者。如果两个玩家得分相同，那么为平局。两位玩家都会采用 最优策略 进行游戏。
 *
 * 请你推断游戏的结果，用如下的方式表示：
 *
 * 如果 Alice 赢，返回 1 。
 * 如果 Bob 赢，返回 -1 。
 * 如果游戏平局，返回 0 。
 */

/**
 *
 * 设 Alice 选的数字之和为 AAA，Bob 选的数字之和为 BBB。如果 A−B>0A-B>0A−B>0 那么 Alice 赢，如果 A−B<0A-B<0A−B<0 那么 Bob 赢，如果 A−B=0A-B=0A−B=0 则平局。所以 Alice 需要最大化 A−BA-BA−B，Bob 需要最小化 A−BA-BA−B。
 *
 * 下文把数组 aliceValues\textit{aliceValues}aliceValues 记作 aaa，把数组 bobValues\textit{bobValues}bobValues 记作 bbb。
 *
 * 以 a=[2,4,3,5], b=[1,6,7,1]a=[2,4,3,5],\ b=[1,6,7,1]a=[2,4,3,5], b=[1,6,7,1] 为例说明。
 *
 * 假设 Bob 把所有石子都拿走，则 A=0, B=15, A−B=−15A=0,\ B=15,\ A-B=-15A=0, B=15, A−B=−15。
 *
 * 先来想一想，如果 Alice 只能拿走一颗石子，应该拿走哪颗呢？
 *
 * 拿走第一颗，那么 A=2, B=14, A−B=−12A=2,\ B=14,\ A-B=-12A=2, B=14, A−B=−12。
 * 拿走第二颗，那么 A=4, B=9, A−B=−5A=4,\ B=9,\ A-B=-5A=4, B=9, A−B=−5。
 * 拿走第三颗，那么 A=3, B=8, A−B=−5A=3,\ B=8,\ A-B=-5A=3, B=8, A−B=−5。
 * 拿走第四颗，那么 A=5, B=14, A−B=−9A=5,\ B=14,\ A-B=-9A=5, B=14, A−B=−9。
 * 对比 Bob 把所有石子都拿走的情况，如果 Alice 拿走第二颗或者第三颗，都可以让 −15-15−15 增大为 −5-5−5，增量为 101010。由于 AAA 增加了 a[i]a[i]a[i]，BBB 减少了 b[i]b[i]b[i]，所以 A−BA-BA−B 的增量等于
 *
 * a[i]−(−b[i])=a[i]+b[i]a[i] - (-b[i]) = a[i] + b[i]
 * a[i]−(−b[i])=a[i]+b[i]
 * 所以 Alice 拿走 a[i]+b[i]a[i]+b[i]a[i]+b[i] 最大的石子最优。
 *
 * 回到原题，Alice 可以拿走两颗石子，应该拿走哪两颗呢？
 *
 * 我们从 Bob 把所有石子都拿走的情况出发，也就是在 A=0, B=15A=0,\ B=15A=0, B=15 的基础上思考，Alice 拿走哪两颗石子，可以让 A−BA-BA−B 增加的尽量多？
 *
 * 定义 c[i]=a[i]+b[i]c[i]=a[i]+b[i]c[i]=a[i]+b[i]，那么 c=[3,10,10,6]c=[3,10,10,6]c=[3,10,10,6]。现在问题变成：给定数组 ccc，Alice 每回合拿走一个数，Bob 每回合删除一个数，Alice 拿走的数之和最大是多少？注意 Bob 要让 Alice 拿走的数之和尽量小。
 *
 * 如此转换后，贪心策略就很显然了：Alice 从大到小拿走数字，Bob 也从大到小删除数字。
 *
 * 所以把 ccc 从大到小排序为 [10,10,6,3][10,10,6,3][10,10,6,3]，两人从左往右交替取数，那么 Alice 只能拿走下标为偶数的数字，其余数字被 Bob 删除。所以 A−BA-BA−B 最大可以增加 c[0]+c[2]=10+6=16c[0]+c[2]=10+6=16c[0]+c[2]=10+6=16，增加后 A−B=1>0A-B=1>0A−B=1>0，Alice 险胜！
 *
 * 算法
 * 把数组按照 a[i]+b[i]a[i]+b[i]a[i]+b[i] 从大到小排序。可以创建一个 (a[i],b[i])(a[i],b[i])(a[i],b[i]) 的 pair 数组对其排序，也可以创建一个下标数组排序。
 * 用 diff\textit{diff}diff 表示 A−BA-BA−B，初始化 diff=0\textit{diff}=0diff=0。遍历数组，把偶数下标的 a[i]a[i]a[i] 加到 AAA 中，相当于 diff\textit{diff}diff 增加了 a[i]a[i]a[i]；把奇数下标的 b[i]b[i]b[i] 加到 BBB 中，相当于 diff\textit{diff}diff 减少了 b[i]b[i]b[i]。
 * 循环结束后，如果 diff>0\textit{diff}>0diff>0，返回 111；如果 diff<0\textit{diff}<0diff<0，返回 −1-1−1；如果 diff=0\textit{diff}=0diff=0，返回 000。
 * 答疑
 * 问：从这个思路的本质是什么？为什么这样转换一下，问题就变得简单了许多？
 *
 * 答：转换前，我们需要同时考虑 a[i]a[i]a[i] 和 b[i]b[i]b[i] 这两个变量，不好处理。转换成 Bob 先把所有数字选了，我们就只需要思考 Alice 如何选数字，只有 c[i]c[i]c[i] 这一个变量，更容易处理。从某种程度上来说，这也可以算作一种「正难则反」。
 *
 * 问：有没有其它的思考方式？
 *
 * 答：也可以这样思考：对比两颗石子 (a[i],b[i])(a[i],b[i])(a[i],b[i]) 和 (a[j],b[j])(a[j],b[j])(a[j],b[j])。如果 Alice 选 iii，Bob 选 jjj，那么 A−B=a[i]−b[j]A-B=a[i]-b[j]A−B=a[i]−b[j]；如果 Alice 选 jjj，Bob 选 iii，那么 A−B=a[j]−b[i]A-B=a[j]-b[i]A−B=a[j]−b[i]。如果 Alice 选 iii 更优，则有 a[i]−b[j]>a[j]−b[i]a[i]-b[j] > a[j]-b[i]a[i]−b[j]>a[j]−b[i]，即 a[i]+b[i]>a[j]+b[j]a[i]+b[i] > a[j]+b[j]a[i]+b[i]>a[j]+b[j]，说明 Alice 应当优先选 a[i]+b[i]a[i]+b[i]a[i]+b[i] 更大的石子。
 *
 * 作者：灵茶山艾府
 * 链接：https://leetcode.cn/problems/stone-game-vi/solutions/1/xiang-xi-jie-shi-wei-shi-yao-yao-an-zhao-0zsg/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class StoneGame {
    // 10000, 1100, 100
    // 10000, 1, 1200
    public static void main(String[] args) {
        System.out.println(new StoneGame().stoneGameVI(
                new int[]{2,4,3},
                new int[]{1,6,7}));
    }
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {
        int n = aliceValues.length;
        Queue<Integer> sum = new PriorityQueue<>((a, b) -> (aliceValues[b] + bobValues[b]) - (aliceValues[a] +  bobValues[a]));
        for (int i = 0; i < n; i++) {
            sum.add(i);
        }
        int aRes = 0;
        int bRes = 0;
        while (!sum.isEmpty()) {
            aRes += aliceValues[sum.poll()];
            bRes += sum.isEmpty() ? 0 : bobValues[sum.poll()];
        }
        return Integer.compare(aRes, bRes);
    }
}
