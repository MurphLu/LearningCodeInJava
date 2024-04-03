package org.algorithm.leetcode.daily.tree;

import org.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class TreeQue {

    /**
     * 给你一个整数 n ，请你找出所有可能含 n 个节点的 真二叉树 ，并以列表形式返回。答案中每棵树的每个节点都必须符合 Node.val == 0 。
     *
     * 答案的每个元素都是一棵真二叉树的根节点。你可以按 任意顺序 返回最终的真二叉树列表。
     *
     * 真二叉树 是一类二叉树，树中每个节点恰好有 0 或 2 个子节点。
     *
     * @param n 总结点个数，如果为偶数，那么必不能生成真二叉树
     * @return 所有真二叉树
     *
     * 保证根节点左侧奇数个节点，右侧也为奇数个节点，递归生成即可
     * 然后左侧生成的 list 和右侧生成的 list 嵌套遍历生成根节点，即得最终结果～～～
     */
    public List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> res = new ArrayList<>();
        if (n % 2 == 0) return res;
        if (n == 1) {
            res.add(new TreeNode(0));
            return res;
        }

        int left = 1;
        int right = n - 1 - left;
        while (right >= 1) {
            List<TreeNode> leftNodes = allPossibleFBT(left);
            List<TreeNode> rightNodes = allPossibleFBT(right);
            for(TreeNode lNode: leftNodes) {
                for(TreeNode rNode : rightNodes) {
                    res.add(new TreeNode(0, lNode, rNode));
                }
            }
            left += 2;
            right -= 2;
        }
        return res;
    }


    /**
     * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
     *
     * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
     *
     * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
     *
     * 注意：你 不能 对两棵二叉树，以及 target 节点进行更改。只能 返回对克隆树 cloned 中已有的节点的引用。
     *
     * @param original 原二叉树
     * @param cloned 克隆二叉树
     * @param target 要从 cloned 中查找的原节点
     * @return cloned 中找到的目标节点
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null) {
            return null;
        }
        if (original == target) {
            return cloned;
        }
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        TreeNode right = getTargetCopy(original.right, cloned.right, target);
        return left == null ? right : left;
    }


}
