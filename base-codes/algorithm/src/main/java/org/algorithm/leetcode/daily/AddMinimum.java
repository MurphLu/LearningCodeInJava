package org.algorithm.leetcode.daily;

/**
 * 给你一个字符串 word ，你可以向其中任何位置插入 "a"、"b" 或 "c" 任意次，返回使 word 有效 需要插入的最少字母数。
 *
 * 如果字符串可以由 "abc" 串联多次得到，则认为该字符串 有效 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：word = "b"
 * 输出：2
 * 解释：在 "b" 之前插入 "a" ，在 "b" 之后插入 "c" 可以得到有效字符串 "abc" 。
 * 示例 2：
 *
 * 输入：word = "aaa"
 * 输出：6
 * 解释：在每个 "a" 之后依次插入 "b" 和 "c" 可以得到有效字符串 "abcabcabc" 。
 * 示例 3：
 *
 * 输入：word = "abc"
 * 输出：0
 * 解释：word 已经是有效字符串，不需要进行修改。
 *
 *
 * 提示：
 *
 * 1 <= word.length <= 50
 * word 仅由字母 "a"、"b" 和 "c" 组成。
 */
public class AddMinimum {
    public static void main(String[] args) {
        System.out.println(new AddMinimum().addMinimum("abcacac"));
    }
    public int addMinimum(String word) {
        int index = word.length() - 1;
        int count = 0;
        while (index >= 0) {
            int step = 1;
            if (index > 1) {
                if (word.charAt(index) <= word.charAt(index - 1)) {
                    count += 2;
                } else {
                    if (word.charAt(index - 1) <= word.charAt(index - 2)) {
                        count += 1;
                        step = 2;
                    } else {
                        step = 3;
                    }
                }
//                if (word.charAt(index) == 'a') {
//                    count += 2;
//                }else if (word.charAt(index) == 'b') {
//                    count += 1;
//                    if (word.charAt(index - 1) != 'a') {
//                        count +=1;
//                    } else {
//                        step = 2;
//                    }
//                } else if (word.charAt(index) == 'c'){
//                    if (word.charAt(index - 1) == 'c') {
//                        count +=2;
//                    } else if (word.charAt(index - 1) == 'b' && word.charAt(index - 2) == 'a') {
//                        step = 3;
//                    }
//                }
            } else {
                if (index == 0) {
                    count += 2;
                } else {
                    if (word.charAt(index) > word.charAt(0)){
                        count += 1;
                        step = 2;
                    } else {
                        count += 2;
                    }
                }
            }
            index -= step;
        }
        return count;
    }
}
