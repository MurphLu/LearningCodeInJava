package org.concurrent.juc.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CollectTest {
    public static void main(String[] args) {

    }
    private void collect() {
        // 底层 array
        ArrayList<Integer> al = new ArrayList<>();

        // 双向链表
        LinkedList<Integer> ll = new LinkedList<>();

        // jdk 8 以前 数组 + 链表， JDK1.7 头插，死循环
        // jdk 8 之后 数组 + 哈希冲突（链表 + 红黑树 超过 8）查找插入效率的平衡， resize 时小于 6 则退化为链表，尾插
        HashMap<Integer, Integer> hm = new HashMap<>();

        // 并发安全 链表/红黑树 上锁，数组 CAS
        ConcurrentHashMap<Integer, Integer> chm = new ConcurrentHashMap<>();

        // 并发安全，直接在方法上加同步锁，锁过于重，效率低
        Hashtable<Integer, Integer> ht = new Hashtable<>();

        // 底层 hashMap
        HashSet<Integer> hs = new HashSet<>();

    }
}
