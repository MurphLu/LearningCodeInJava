package org.base.map;

import java.util.HashMap;

public class HashMapTest {
    public static void main(String[] args) {
        String s = "test";
        System.out.println(s.hashCode());
        int h;
        System.out.println((h = s.hashCode()) ^ h >>> 16);
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put(i, i);
        }
    }

}
