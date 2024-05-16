package org.algorithm.leetcode.daily.bitNum;

/**
 * 给你 n 个项目，编号从 0 到 n - 1 。同时给你一个整数数组 milestones ，其中每个 milestones[i] 表示第 i 个项目中的阶段任务数量。
 *
 * 你可以按下面两个规则参与项目中的工作：
 *
 * 每周，你将会完成 某一个 项目中的 恰好一个 阶段任务。你每周都 必须 工作。
 * 在 连续的 两周中，你 不能 参与并完成同一个项目中的两个阶段任务。
 * 一旦所有项目中的全部阶段任务都完成，或者仅剩余一个阶段任务都会导致你违反上面的规则，那么你将 停止工作 。
 * 注意，由于这些条件的限制，你可能无法完成所有阶段任务。
 *
 * 返回在不违反上面规则的情况下你 最多 能工作多少周。
 *
 * 只需要检查最大的那个 milestone 即可
 * 最大值与剩余值比较
 * 如果 max - rest <=1，那说明最大值可以完成，rest 也可以完成
 * 如果 max - rest > 1，那么只能完成 rest + rest + 1，
 * max 中剩余的任务则无法避免与 max 中的其他任务相邻
 */
public class NumberOfWeeks {
    public static void main(String[] args) {
        System.out.println(new NumberOfWeeks().numberOfWeeks(
                new int[]{4,2,1}
        ));
    }
    public long numberOfWeeks(int[] milestones) {
        long sum = 0;
        long max = 0;
        for (long n: milestones) {
            sum += n;
            max = Math.max(n, max);
        }
        long rest = sum - max;
        if(max - rest <= 1){
            return sum;
        } else {
            return sum - (sum - rest * 2 - 1);
        }
    }
}
