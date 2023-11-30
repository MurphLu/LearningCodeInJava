package org.algorithm.hashAndHashTable;

import java.util.HashMap;

/**
 * 设计RandomPool结构
 * [题目]设计一种结构，在该结构中有如下三个功能:
 * insert (key):将某个key加入到该结构，做到不重复加入
 * delete(key):将原本在结构中的某个key移除
 * getRandom0): 等概率随机返回结构中的任何一个key。

 * [要求]
 * Insert、delete和getRandom方法的时间复杂度都是0(1)

 */
public class RandomPool<T> {
    private HashMap<T, Integer> valIdx;
    private HashMap<Integer, T> idxVal;

    private int size;

    public RandomPool() {
        valIdx = new HashMap<>();
        idxVal = new HashMap<>();
        size = 0;
    }

    public void insert(T key) {
        if (!valIdx.containsKey(key)) {
            valIdx.put(key, size);
            idxVal.put(size, key);
            size ++;
        }
    }

    public void delete(T key) {
        if (valIdx.containsKey(key)){
            T lastVal = idxVal.get(size - 1);
            Integer currIdx = valIdx.get(key);

            valIdx.put(lastVal, currIdx);
            idxVal.put(currIdx, lastVal);

            valIdx.remove(lastVal);
            idxVal.remove(--size);
        }
    }

    public T getRandom() {
        if (size > 0) {
            int randomIdx = (int)(Math.random()*this.size);
            return this.idxVal.get(randomIdx);
        }
        return null;
    }

}
