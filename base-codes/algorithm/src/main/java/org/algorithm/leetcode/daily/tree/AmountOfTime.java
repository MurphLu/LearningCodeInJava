package org.algorithm.leetcode.daily.tree;

import org.algorithm.tree.TreeNode;

/**
 * 给你一棵二叉树的根节点 root ，二叉树中节点的值 互不相同 。另给你一个整数 start 。在第 0 分钟，感染 将会从值为 start 的节点开始爆发。
 *
 * 每分钟，如果节点满足以下全部条件，就会被感染：
 *
 * 节点此前还没有感染。
 * 节点与一个已感染节点相邻。
 * 返回感染整棵树需要的分钟数。
 *
 * 提示：
 *
 * 树中节点的数目在范围 [1, 105] 内
 * 1 <= Node.val <= 105
 * 每个节点的值 互不相同
 * 树中必定存在值为 start 的节点
 *
 *
 * 递归：
 * 如果 start 在节点某一侧，则需要判断 该侧 start 到叶子节点的时间和 （ start 到该节点 + 该节点到其另一侧叶子节点的时间）的最大值
 * 如此递归下去最终获取到 root 节点的 info 内容即得最终结果
 */
public class AmountOfTime {
    public static void main(String[] args) {
        // 1,5,3,null,4,10,6,9,2
        TreeNode root = new TreeNode(1);
        System.out.println(new AmountOfTime().amountOfTime(root, 1));
    }
    static class Info {
        int height;
        boolean hasStart;
        int startToCur = -1;
        int totalTimeCount;
    }
    public int amountOfTime(TreeNode root, int start) {
        Info info = t(root, start);
        return info.totalTimeCount;
    }

    private Info t(TreeNode root, int start) {

        if (root == null) {
            return new Info();
        }

        Info left = t(root.left, start);
        Info right = t(root.right, start);

        Info info  = new Info();
        if (root.val == start) {
            info.hasStart = true;
            info.startToCur = 0;
        }
        info.height = Math.max(left.height, right.height) + 1;
        int maxTimeCount;
        if (left.hasStart || right.hasStart) {
            Info hasStart = left.hasStart ? left : right;
            Info hasNoStart = left.hasStart ? right : left;
            info.hasStart = true;
            info.startToCur = hasStart.startToCur + 1;
            maxTimeCount = Math.max(hasNoStart.height + info.startToCur, hasStart.totalTimeCount);
        } else {
            maxTimeCount = Math.max(left.height, right.height) + (info.hasStart ? 0 : 1);
        }
        info.totalTimeCount = maxTimeCount;
        return info;
    }
}
