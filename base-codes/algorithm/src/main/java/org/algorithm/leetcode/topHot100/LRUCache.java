package org.algorithm.leetcode.topHot100;

import org.algorithm.leetcode.ds.ListNode;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1,1);
        cache.put(2,2);
        cache.get(1);
        cache.put(3,3);
        cache.get(2);
    }

    int size = 0;
    int capacity;

    ListNode head = null;
    ListNode tail = null;

    Map<Integer, ListNode> fMap = new HashMap<>();
    Map<Integer, Integer> cache = new HashMap<>();

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if(cache.containsKey(key)) {
            update(key,cache.get(key));
            return cache.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            update(key, value);
        } else {
            addNew(key, value);
        }

    }

    private void update(int key, int value) {
        if (head != tail) {
            if(fMap.get(key) == null) {
                ListNode node = head;
                head = head.next;
                fMap.put(head.val, null);
                fMap.put(node.val, tail);
                tail.next = node;
                tail = tail.next;
            } else if (fMap.get(key).next.next != null ){
                ListNode fNode = fMap.get(key);
                ListNode node = fNode.next;
                if (node.next != null)
                    fMap.put(node.next.val, fNode);
                fMap.put(node.val, tail);
                fNode.next = node.next;
                node.next = null;
                tail.next = node;
                tail = tail.next;
            }
        }
        cache.put(key, value);
    }

    private void addNew(int key, int value) {
        ListNode node = new ListNode(key);
        if (head == null) {
            head = node;
            tail = head;
            fMap.put(key, null);
        } else {
            fMap.put(key, tail);
            tail.next = node;
            tail = tail.next;
        }
        if (size == capacity) {
            cache.remove(head.val);
            fMap.remove(head.val);
            head = head.next;
            if (head != null) {
                fMap.put(head.val, null);
            }
        } else {
            size++;
        }
        cache.put(key, value);
    }
}
