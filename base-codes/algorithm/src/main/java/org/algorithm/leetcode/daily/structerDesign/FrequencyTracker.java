package org.algorithm.leetcode.daily.structerDesign;

import java.util.*;


/**
 * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
 *
 * 实现 FrequencyTracker 类：
 *
 * FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
 * void add(int number)：添加一个 number 到数据结构中。
 * void deleteOne(int number)：从数据结构中删除一个 number 。数据结构 可能不包含 number ，在这种情况下不删除任何内容。
 * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回 false。
 */

public class FrequencyTracker {

    int[] table;
    Map<Integer, Set<Integer>> freqMap;
    public FrequencyTracker() {
        table = new int[100000];
        freqMap = new HashMap<>();
    }

    public void add(int number) {
        updateFreq(number, true);
    }

    public void deleteOne(int number) {
        updateFreq(number, false);
    }

    private void updateFreq(int number, boolean add) {
        int currFreq = table[number - 1];
        Set<Integer> integers = freqMap.get(currFreq);
        if (integers != null) {
            integers.remove(number);
        }
        if (add){
            currFreq ++;
        } else {
            if(currFreq > 0) currFreq --;
        }
        table[number - 1] = currFreq;
        if (currFreq > 0) {
            Set<Integer> set = freqMap.getOrDefault(currFreq, new HashSet<>());
            set.add(number);
            freqMap.put(currFreq, set);
        }
    }

    public boolean hasFrequency(int frequency) {
        return !freqMap.getOrDefault(frequency, new HashSet<>()).isEmpty();
    }
}
