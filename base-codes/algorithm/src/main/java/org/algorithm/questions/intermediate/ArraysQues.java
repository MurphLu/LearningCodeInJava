package org.algorithm.questions.intermediate;

import java.util.*;

public class ArraysQues {
    public static void main(String[] args) {

    }

    //给定一个数组，找出两个数差值为 k 的数对
    public static ArrayList<int[]> sub(int[] num, int k) {
        Arrays.sort(num);
        ArrayList<int[]> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
            int need = num[i] - k;
            if (map.containsKey(need) && !map.containsKey(num[i])) {
                result.add(new int[]{num[i], need});
            }
            map.put(num[i], i);
        }
        return result;
    }

    /**
     * 给一个包含n个整数元素的集合a，一个包含m个整数元素的集合b。
     * 定义magic操作为，从一个集合中取出一个元素，放到另一个集合里，且操作过后每个集合的平均值都大大于于操作前。
     * 注意以下两点:
     * 1) 不可以把一个集合的元素取空，这样就没有平均值了
     * 2) 值为x的元素从集合b取出放入集合a，但集合a中已经有值为x的元素，
     *      则a的平均值不变(因为集合元素不会重复)，b的平均值可能会改变 (因为x被取出)
     * 问最多可以进行多少次magic操作?
     */
    public int magic(int[] num1, int[] num2) {
        // 求 num1，num2 的平均值 p1, p2
        // 取出拥有较大平均值中在 两个平均值之间的数
        // 在取出的数中拿出最小的一个放到小平均值的数组（使大平均值有更大的提升，使小平均值有更小的提升）
        // 重复以上步骤，直到平均值相等或者平均值之间的数不存在
        return process(num1, num2);
    }

    public int process(int[] num1, int[] num2) {
        double sum1 = Arrays.stream(num1).sum();
        double sum2 = Arrays.stream(num2).sum();
        double avg1 = avg(sum1, num1.length);
        double avg2 = avg(sum2, num2.length);
        if (avg1 == avg2) {
            return 0;
        }
        int[] arrMore = avg1 > avg2 ? num1 : num2;
        int[] arrLess = avg1 > avg2 ? num2 : num1;
        double sumMore = avg1 > avg2 ? sum1 : sum2;;
        double sumLess = avg1 > avg2 ? sum2 : sum1;;

        Arrays.sort(arrMore);
        int moreSize = arrMore.length;
        int lessSize = arrLess.length;
        Set<Integer> setLess = new HashSet<>();
        for (int less : arrLess) {
            setLess.add(less);
        }
        int ops = 0;
        for (int i = 0; i < arrMore.length; i++) {
            double cur = arrMore[i];
            if (cur < avg(sumMore, moreSize)
                    && cur > avg(sumLess, lessSize)
                    && !setLess.contains(arrMore[i])
            ){
                moreSize--;
                sumMore -= cur;
                lessSize++;
                sumLess += cur;
                setLess.add(arrMore[i]);
                ops++;
            }
        }
        return ops;
    }

    private double avg(double sum, int count) {
        return sum / count;
    }

    // 数组中出现最多的前 k 个
    public List<String> getMostK(String[] strs, int k) {
        // hashMap 统计次数
        // 大根堆遍历完 弹
        // 小根堆 size 为 k，堆满了之后如果有比堆顶大的，那么弹出堆顶，新的进堆，直到完成
        return new ArrayList<>();
    }

}
